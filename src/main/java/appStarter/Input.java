package appStarter;

import classBuilder.*;
import fileReader.FileReader;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Input {

    private static String className;
    private static int listSize;
    private static String fileName;
    private static Path filepath;
    private static Scanner scanner=new Scanner(System.in);

    private static void getListSizeAndClassFromUser(boolean listSizeFlag){
        System.out.println("Укажите класс объекта");
        className=scanner.nextLine();
        if (listSizeFlag){
            System.out.println("Укажите количество объектов, которые хотите создать");
            listSize=scanner.nextInt();
            scanner.nextLine();
        }
    }

    public static List<? extends CashedClass> createRandomObjects() {
        getListSizeAndClassFromUser(true);
        return switch (className) {
            case "Author" -> Randomization.getRandomAuthors(listSize);
            case "Book" -> Randomization.getRandomBooks(listSize);
            case "Publisher" -> Randomization.getRandomPublishers(listSize);
            default -> throw new IllegalArgumentException("Неизвестный класс: " + className);
        };
    }

    public static List<? extends CashedClass> createManualObjects() {
        getListSizeAndClassFromUser(true);
        return switch (className) {
            case "Author" -> ManualInput.processAuthorCreation(listSize);
            case "Book" -> ManualInput.processBookCreation(listSize);
            case "Publisher" -> ManualInput.processPublisherCreation(listSize);
            default -> throw new IllegalArgumentException("Неизвестный класс: " + className);
        };
    }

    public static List<? extends CashedClass> readFromFile() throws IOException {
        System.out.println("Введите название файла:");
        fileName=scanner.nextLine();
        //TODO мейби путь в конфиг закинуть
        filepath = Paths.get(System.getProperty("user.dir") + "\\objectFiles" + "\\" + fileName);

        getListSizeAndClassFromUser(false);
        return switch (className){
            case "Author" -> FileReader.readFile(filepath, Author.class);
            case "Book" -> FileReader.readFile(filepath, Book.class);
            case "Publisher" -> FileReader.readFile(filepath, Publisher.class);
            default -> throw new IllegalArgumentException("Неизвестный класс: " + className);
        };
    }
}