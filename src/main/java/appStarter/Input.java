package appStarter;

import classBuilder.*;
import java.util.List;
import java.util.Scanner;

public class Input {

    private static String className;
    private static int listSize;
    private static Scanner scanner=new Scanner(System.in);;

    private static void getListSizeAndClassFromUser(){
        System.out.println("Укажите класс объекта");
        className=scanner.nextLine();
        System.out.println("Укажите количество объектов, которые хотите создать");
        listSize=scanner.nextInt();
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