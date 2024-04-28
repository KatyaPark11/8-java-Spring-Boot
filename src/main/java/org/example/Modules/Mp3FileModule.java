package org.example.Modules;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class Mp3FileModule implements FileModule {
    @Override
    public boolean supportsFormat(String path) {
        return path.endsWith(".mp3");
    }

    @Override
    public void getDescription() {
        System.out.println("Функция номер 1 - вывод названия трека из тегов");
        System.out.println("Функция номер 2 - вывод длительности в секундах");
        System.out.println("Функция номер 3 - вывод жанра трека из тегов");
    }

    public void method1(File file) {
        try {
            AudioFile audioFile = AudioFileIO.read(file);
            if (audioFile != null && audioFile.getTag() != null) {
                String title = audioFile.getTag().getFirst(FieldKey.TITLE);
                if (title != null && !title.isEmpty()) {
                    System.out.println("Название трека: " + title);
                } else {
                    System.out.println("Информация о названии трека отсутствует в метаданных");
                }
            } else {
                System.out.println("Не удалось прочитать метаданные аудиофайла");
            }
        } catch (Exception e) {
            System.err.println("Ошибка при чтении аудиофайла: " + e.getMessage());
        }
    }

    public void method2(File file) {
        try {
            AudioFile audioFile = AudioFileIO.read(file);
            if (audioFile != null && audioFile.getAudioHeader() != null) {
                int durationSeconds = audioFile.getAudioHeader().getTrackLength();
                System.out.println("Длительность трека в секундах: " + durationSeconds);
            } else {
                System.out.println("Не удалось получить информацию о длительности трека");
            }
        } catch (Exception e) {
            System.err.println("Ошибка при чтении аудиофайла: " + e.getMessage());
        }
    }

    public void method3(File file) {
        try {
            AudioFile audioFile = AudioFileIO.read(file);
            if (audioFile != null && audioFile.getTag() != null) {
                String genre = audioFile.getTag().getFirst(FieldKey.GENRE);
                if (genre != null && !genre.isEmpty()) {
                    System.out.println("Жанр: " + genre);
                } else {
                    System.out.println("Информация о жанре отсутствует в метаданных");
                }
            } else {
                System.out.println("Не удалось прочитать метаданные аудиофайла");
            }
        } catch (Exception e) {
            System.err.println("Ошибка при чтении аудиофайла: " + e.getMessage());
        }
    }
}
