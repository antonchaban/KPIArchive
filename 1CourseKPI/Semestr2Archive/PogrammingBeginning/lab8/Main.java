package ua.kpi.fict.acts.it03.proga.lab8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        commonestWord("C:\\sample.txt");
    }

    public static void commonestWord(String file) {
        //Создали Мап (слова - ключи, количество - значение)
        HashMap<String, Integer> wordCountMap = new HashMap<String, Integer>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            //Баффер ридер
            //Читаем первую линию

            String currentLine = reader.readLine();

            while (currentLine != null) {
                //Делим строку на слова

                String[] words = currentLine.split(" ");

                //Проходимся по каждому слову

                for (String word : words) {
                    //Если слово уже есть +1 к значению в Мапе

                    if (wordCountMap.containsKey(word)) {
                        wordCountMap.put(word, wordCountMap.get(word) + 1);
                    }

                    //Если нет добавляем новую пару, ключ значение
                    else {
                        wordCountMap.put(word, 1);
                    }
                }

                //След линия, бегаем пока readLine() не вернет null

                currentLine = reader.readLine();
            }

            //Получаем самое повторяющееся слово

            String mostRepeatedWord = null;

            int count = 0;

            //entrySet() возвращает набор ключ-значений

            Set<Entry<String, Integer>> entrySet = wordCountMap.entrySet();

            //Итерируем массив entrySet
            for (Entry<String, Integer> entry : entrySet) {
                //Если полученное значение из пары ключ - значение больше переменной count,
                //записываем значение в count и ключ (слово) в mostRepeatedWord
                if (entry.getValue() > count) {
                    mostRepeatedWord = entry.getKey();
                    count = entry.getValue();
                }
            }

            System.out.println("The most repeated word in input file is : " + mostRepeatedWord);

            System.out.println("Number Of Occurrences : " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}