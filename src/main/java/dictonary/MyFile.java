package dictonary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.LinkedHashSet;

public class MyFile {

    private String fileText; //переменная хранящая текст из файла

    //конструктор
    public MyFile(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
                line = reader.readLine();
            }
            reader.close();
            fileText = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            fileText = null;
        }
    }

    //метод разделяющий текст на слова, а так же приводящий к нижнему регистру
    public List<String> splitText(){
        List<String> words = new ArrayList<>();
        String[] split = fileText.split("\\P{L}+"); //делим строку по любому количеству пробелов и/или табуляций.

        for (String s : split) {
            if (s.matches("[а-яА-ЯёЁ-]+")) {
                words.add(s.toLowerCase());
            }
        }

        return words;
    }

    //метод, убирающий дубли
    public static List<String> getSetWord(String fileText) {
            MyFile myFile = new MyFile(new File(fileText));
            List<String> words = myFile.splitText();
            Set<String> uniqueWords = new LinkedHashSet<>(words);
            return new ArrayList<>(uniqueWords);
    }

    //счетчик частоты использования слов, возвращающий словарь с частотами слов
    public Map<String, Integer> countWordFrequency() {
            List<String> words = splitText();
            Map<String, Integer> wordFrequency = new HashMap<>();

            for (String word : words) {
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }

            return wordFrequency;
        }


}
