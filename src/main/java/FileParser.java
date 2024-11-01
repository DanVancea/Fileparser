import java.io.*;

import java.nio.file.Files;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import static java.nio.file.Path.of;

public class FileParser {

    public static void main(String[] args) throws IOException {

        int mostFrequentWordsnumber = 10;

        String wordsList = null;
        try {
            wordsList = Files.readString(of("src/resources/shakespeare_historical_plays.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map<String, Integer> wordsMap = new TreeMap<>();
        String[] words = wordsList.split("\\s+");

        for (String word : words) {
            if (!wordsMap.containsKey(word)) {
                wordsMap.put(word, 1);
            } else {
                wordsMap.put(word, wordsMap.get(word) + 1);
            }
        }

        Map<String, Integer> sortedWordsMap = wordsMap.entrySet()
                .stream()
                .sorted(Entry.comparingByValue(Comparator.reverseOrder())).limit(mostFrequentWordsnumber)
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        System.out.println("top ten most common words by order of occurance: " + sortedWordsMap);
    }
}

