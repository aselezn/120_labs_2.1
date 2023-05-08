package dictonary;

import java.io.File;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Создание объекта MyFile с указанием пути к текстовому файлу
        MyFile myFile = new MyFile(new File("/Users/as.selezneva/repos/untitled2/src/main/java/dictonary/j120_lab2_text.txt"));

        // Получение списка слов без дубликатов
        List<String> uniqueWords = MyFile.getSetWord("/Users/as.selezneva/repos/untitled2/src/main/java/dictonary/j120_lab2_text.txt");
        System.out.println("Уникальные слова: " + uniqueWords);

        // Получение словаря с частотами слов
        Map<String, Integer> wordFrequency = myFile.countWordFrequency();
        System.out.println("Частоты слов: " + wordFrequency);
    }
}