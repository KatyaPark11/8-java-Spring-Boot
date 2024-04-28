package org.example.Modules;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class TextFileModule implements FileModule {
    @Override
    public boolean supportsFormat(String path) {
        return path.endsWith(".txt");
    }

    @Override
    public void getDescription() {
        System.out.println("Функция номер 1 - вывод количества строк");
        System.out.println("Функция номер 2 - вывод частоты вхождения каждого символа");
        System.out.println("Функция номер 3 - вывод количества символов");
    }

    public void method1(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            long count = reader.lines().count();
            System.out.println("Количество строк в файле: " + count);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    public void method2(File file) {
        Map<Character, Integer> mapLetterToCount = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (char letter : line.toCharArray()){
                    mapLetterToCount.put(letter, mapLetterToCount.getOrDefault(letter, 0)+1);
                }
            }
            for (Map.Entry<Character, Integer> entry : mapLetterToCount.entrySet()) {
                System.out.printf("%c встречается - %d раз%n", entry.getKey(), entry.getValue());
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    public void method3(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            long count = 0;
            while (reader.read() != -1) {
                count++;
            }
            System.out.printf("В файле %d символов%n", count);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}
