package dictonary;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Reports {

    // Генерирует отчет, сортируя слова по алфавиту
    public static void generateAlphabeticalReport(Map<String, Integer> wordFrequency, String outputFile) {
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(wordFrequency.entrySet()); //Map.Entry — это интерфейс, используемый для представления пары "ключ-значение" в Map
        sortedEntries.sort(Map.Entry.comparingByKey());
        writeReportToFile(sortedEntries, outputFile);
    }

    // Генерирует отчет, сортируя слова по обратному алфавиту
    public static void generateReverseAlphabeticalReport(Map<String, Integer> wordFrequency, String outputFile) {
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(wordFrequency.entrySet());
        sortedEntries.sort((o1, o2) -> {
            String s1 = o1.getKey();
            String s2 = o2.getKey();
            int len1 = s1.length();
            int len2 = s2.length();
            for (int i = 1; i <= Math.min(len1, len2); i++) {
                char c1 = s1.charAt(len1 - i);
                char c2 = s2.charAt(len2 - i);
                if (c1 != c2) {
                    return Character.compare(c1, c2);
                }
            }
            return Integer.compare(len1, len2);
        });

        writeReportToFile(sortedEntries, outputFile);
    }

    // Генерирует отчет, сортируя слова по убыванию частоты
    public static void generateFrequencyReport(Map<String, Integer> wordFrequency, String outputFile) {
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(wordFrequency.entrySet());
        sortedEntries.sort((o1, o2) -> {
            int cmp = o2.getValue().compareTo(o1.getValue());
            if (cmp != 0) {
                return cmp;
            }
            return o1.getKey().compareTo(o2.getKey());
        });

        writeReportToFile(sortedEntries, outputFile);
    }

    // метод записывающий файл
    private static void writeReportToFile(List<Map.Entry<String, Integer>> entries, String outputFile) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            for (Map.Entry<String, Integer> entry : entries) {
                writer.write(String.format("%s %d%n", entry.getKey(), entry.getValue()));
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
