package org.example.Modules;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DirectoryModule implements FileModule {
    String basePath = "..\\RandomFiles";

    @Override
    public boolean supportsFormat(String path) {
        File fileSystemElement = new File(basePath + "\\" + path);
        return fileSystemElement.isDirectory();
    }

    @Override
    public void getDescription() {
        System.out.println("Функция номер 1 - вывод списка файлов в каталоге");
        System.out.println("Функция номер 2 - вывод суммарного размера всех файлов в каталоге");
        System.out.println("Функция номер 3 - вывод количества файлов и папок в каталоге");
    }

    public void method1(File directory) {
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if(file.isFile()) {
                    System.out.println(file.getName());
                }
            }
        }
    }

    public void method2(File directory) {
        File[] files = directory.listFiles();

        long size = 0;
        if (files != null) {
            for (File file : files) {
                if(file.isFile()) {
                    size += file.length();
                }
            }
        }
        System.out.printf("%d байт", size);
    }

    public void method3(File directory) {
        File[] files = directory.listFiles();
        int fileCount = 0;
        int dirCount = 0;
        if (files != null) {
            for (File file : files) {
                if(file.isFile()) {
                    fileCount++;
                } else {
                    dirCount++;
                }
            }
        }
        System.out.printf("%d папок%n", dirCount);
        System.out.printf("%d файлов%n", fileCount);
    }
}
