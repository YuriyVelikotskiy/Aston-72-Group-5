package appStarter;

import classBuilder.*;
import dataProvider.DataProvider;

import java.util.List;
import java.util.Scanner;

public class Input {

    private static String className;
    private static int listSize;
    private static Scanner scanner = new Scanner(System.in);

    //Берет тип класса из DataProvider, если не нашел, то просит пользователя вписать его в консоль
    private static void getListSizeAndClassFromUser() {
        if (DataProvider.getInstance().getClazz() != null) {
            className = DataProvider.getInstance().getClazz().getSimpleName();
        } else {
            System.out.println("Укажите класс объекта");
            className = scanner.nextLine();
        }
        System.out.println("Укажите количество объектов, которые хотите создать");
        listSize = scanner.nextInt();
        scanner.nextLine();
    }

    public static List<? extends CashedClass> createRandomObjects() {
        getListSizeAndClassFromUser();
        return switch (className) {
            case "Author" -> Randomization.getRandomAuthors(listSize);
            case "Book" -> Randomization.getRandomBooks(listSize);
            case "Publisher" -> Randomization.getRandomPublishers(listSize);
            default -> throw new IllegalArgumentException("Неизвестный класс: " + className);
        };
    }

    public static List<? extends CashedClass> createManualObjects() {
        getListSizeAndClassFromUser();
        return switch (className) {
            case "Author" -> ManualInput.processAuthorCreation(listSize);
            case "Book" -> ManualInput.processBookCreation(listSize);
            case "Publisher" -> ManualInput.processPublisherCreation(listSize);
            default -> throw new IllegalArgumentException("Неизвестный класс: " + className);
        };
    }

//    public static List<? extends CashedClass> readFromFile(){
//
//    }

}