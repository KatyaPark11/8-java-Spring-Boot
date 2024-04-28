package org.example.Modules;

import com.drew.metadata.exif.ExifSubIFDDirectory;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import javax.imageio.ImageIO;

@Component
public class ImageFileModule implements FileModule {
    @Override
    public boolean supportsFormat(String path) {
        return path.endsWith(".jpg");
    }

    @Override
    public void getDescription() {
        System.out.println("Функция номер 1 - вывод размера изображения");
        System.out.println("Функция номер 2 - вывод информации EXIF");
        System.out.println("Функция номер 3 - вывод цифрового увеличения из информации EXIF");
    }

    public void method1(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            int width = image.getWidth();
            int height = image.getHeight();
            System.out.println("Ширина: " + width + " пикселей");
            System.out.println("Высота: " + height + " пикселей");
        } catch (IOException e) {
            System.err.println("Ошибка при чтении изображения: " + e.getMessage());
        }
    }

    public void method2(File file) {
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

            if (directory != null) {
                for (Tag tag : directory.getTags()) {
                    System.out.println(tag.getTagName() + " : " + tag.getDescription());
                }
            } else {
                System.out.println("В данном файле нет EXIF информации");
            }
        } catch (ImageProcessingException | IOException e) {
            System.err.println("Ошибка при обработке изображения: " + e.getMessage());
        }
    }

    public void method3(File file) {
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

            if (directory != null) {
                Date date = directory.getDate(ExifSubIFDDirectory.TAG_DIGITAL_ZOOM_RATIO);
                if (date != null) {
                    System.out.println("Цифровое увеличение: " + date);
                } else {
                    System.out.println("Цифровое увеличение не найдено в метаданных EXIF");
                }
            } else {
                System.out.println("В данном файле нет EXIF информации");
            }
        } catch (ImageProcessingException | IOException e) {
            System.err.println("Ошибка при обработке изображения: " + e.getMessage());
        }
    }
}
