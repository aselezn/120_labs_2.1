package dictonary;

import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class WordFrequencyCalculator {

    private static final String WORD_REGEX = "[а-яА-ЯёЁ-]+";
    private final Map<String, Integer> wordCountMap = new HashMap<>();

    public Map<String, Integer> getWordCountMap() {
        return new HashMap<>(wordCountMap);
    }

    public void calculateWordFrequencies(String[] fileNames) {
        for (String fileName : fileNames) {
            processFile(fileName);
        }
    }

    private void processFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("[^а-яА-ЯёЁ-]+");
                for (String word : words) {
                    if (word.matches(WORD_REGEX)) {
                        String normalizedWord = word.toLowerCase();
                        wordCountMap.put(normalizedWord, wordCountMap.getOrDefault(normalizedWord, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error processing file " + fileName + ": " + e.getMessage());
        }
    }

    public void writeReports() {
        writeReportByAlphabeticalOrder();
        writeReportByReverseAlphabeticalOrder();
        writeReportByFrequency();
    }

    private void writeReportByAlphabeticalOrder() {
        Map<String, Integer> sortedMap = wordCountMap.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        writeReportToFile("report-by-alph.txt", sortedMap);
    }

    private void writeReportByReverseAlphabeticalOrder() {
        Map<String, Integer> sortedMap = wordCountMap.entrySet().stream()
                .sorted(Comparator.comparing((Map.Entry<String, Integer> e) -> new StringBuilder(e.getKey()).reverse().toString()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        writeReportToFile("report-by-alph-rev.txt", sortedMap);
    }

    private void writeReportByFrequency() {
        Map<String, Integer> sortedMap = wordCountMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed().thenComparing(Map.Entry::getKey))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        writeReportToFile("report-by-freq.txt", sortedMap);
    }

    private void writeReportToFile(String fileName, Map<String, Integer> wordCountMap) {
        try (FileWriter writer = new FileWriter(new File(fileName))) {
            for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing report to file " + fileName + ": " + e.getMessage());
        }
    }
}

