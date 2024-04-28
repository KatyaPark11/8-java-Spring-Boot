package org.example;

import org.example.Modules.FileModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication(scanBasePackages = "org.example")
public class App 
{
    private static List<FileModule> fileModules;
    private static String basePath = "..\\RandomFiles";

    @Autowired
    public App(List<FileModule> fileModules){
        App.fileModules = fileModules;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Я помогу вам обработать файл! Введите его имя:");

            FileModule curFileModule = null;

            String fileName = scanner.nextLine();
            File file = new File(basePath + "\\" + fileName);

            if (!file.exists() || !file.isFile()) {
                System.err.println("Файл не существует или это не файл.");
                return;
            }

            for (FileModule fileModule : fileModules) {
                if (fileModule.supportsFormat(fileName)) {
                    curFileModule = fileModule;
                    break;
                }
            }

            if (curFileModule == null) {
                System.out.println("К сожалению, данный формат файла не поддерживается");
            } else {
                System.out.println("Выберите какую из предложенных функций вы бы хотели использовать:");
                curFileModule.getDescription();
                String methodNum = scanner.nextLine();
                try {
                    Method method = curFileModule.getClass().getMethod("method" + methodNum, File.class);
                    method.invoke(curFileModule, file);
                } catch (NoSuchMethodException e) {
                    System.out.println("Введённой функции не существует");
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.out.println("Ошибка при вызове метода: " + e.getMessage());
                }
            }

            System.out.println("Желаете завершить работу? (YES/ДА или NO/НЕТ)");
            String answer = scanner.nextLine();
            if (answer.equals("YES") || answer.equals("ДА")) {
                return;
            }
        }
    }
}
