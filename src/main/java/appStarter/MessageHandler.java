package appStarter;


import cashCreator.CashCreator;
import classBuilder.CashedClass;
import config.Config;
import dataProcessor.DataProcessor;
import dataProcessor.FileDataProcessor;
import dataProcessor.MemoryDataProcessor;
import dataProvider.DataProvider;

import java.util.List;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

//TODO оборачивать исключения
//TODO кеш файл если не был создан, создается только после выхода из программы
//TODO отображение
//TODO поиск


public class MessageHandler {
    private static Scanner scanner;
    private static List<? extends CashedClass> list;
    private static HashMap<String, MenuManager> menuOptions;
    private static MenuManager currentMenu;
    private static DataProvider dataProvider;
    private static DataProcessor dataProcessor;

    static {
        dataProcessor = new MemoryDataProcessor(new FileDataProcessor(null));
        dataProvider = DataProvider.getInstance();
        scanner = new Scanner(System.in);
        initializeMenuOptions();
    }

    private static void initializeMenuOptions() {
        menuOptions = new HashMap<>();
        menuOptions.put("showFullStartupMenu", new MenuManager("showFullStartupMenu", 4));
        menuOptions.put("showShortStartupMenu", new MenuManager("showShortStartupMenu", 2));
        menuOptions.put("showCreationMenu", new MenuManager("showCreationMenu", 4));
        menuOptions.put("showUpdateMenu", new MenuManager("showUpdateMenu", 4));
    }


    public static void startMessage() {
        boolean tmp_validation = false;
        dataProcessor.hasData();
        if (!dataProvider.isEmpty()) {
            Menu.showFullStartupMenu();
            currentMenu = menuOptions.get("showFullStartupMenu");
            processInput(getAnswer());
        } else {
            Menu.showShortStartupMenu();
            currentMenu = menuOptions.get("showShortStartupMenu");
            processInput(getAnswer());
        }

    }

    private static void processInput(int choise) {

        switch (currentMenu.getMenuName()) {
            case "showShortStartupMenu":
                processShortStartupMenu(choise);
                break;
            case "showFullStartupMenu":
                processFullStartupMenu(choise);
                break;
            case "showCreationMenu":
                processCreationMethod(choise);
                break;
            case "showUpdateMenu":
                processUpdateMethod(choise);
                break;
        }
    }

    private static void processUpdateMethod(int choice) {
        switch (choice) {
            case 1:
                readFromFile();
                break;
            case 2:
                createRandomData();
                break;
            case 3:
                manualInput();
        }
    }

    private static void processCreationMethod(int choice) {
        switch (choice) {
            case 1:
                readFromFile();
                break;
            case 2:
                createRandomData();
                break;
            case 3:
                manualInput();
        }
    }

    //TODO закончить метод
    private static void manualInput() {
        if(currentMenu.getMenuName().equals("showUpdateMenu")){
            list = Input.createManualObjects();
            cache(()->CashCreator.getInstance().start(list,Config.getCASHPATH()),list);
            startMessage();
        }else {
            list = Input.createManualObjects();
            cache(()->CashCreator.getInstance().start(list),list);
            startMessage();
        }
    }

    //TODO закончить метод
    private static void createRandomData() {
        if(currentMenu.getMenuName().equals("showUpdateMenu")){
            list = Input.createRandomObjects();
            cache(()->CashCreator.getInstance().start(list,Config.getCASHPATH(), true),list);
            startMessage();
        }else {
            list = Input.createRandomObjects();
            cache(()->CashCreator.getInstance().start(list),list);
            startMessage();
        }
    }

    //TODO закончить метод
    private static void readFromFile() {
        System.out.println("какая - то логика");
        startMessage();
    }

    private static void processShortStartupMenu(int choise) {
        //TODO посмотреть можно ли сделать цепочку обязанностей
        switch (choise) {
            case 1:
                processCreationMenu();
            case 2:
                exit();
        }
    }

    private static void processCreationMenu() {
        Menu.showCreationMenu();
        DataProvider.getInstance().clear();
        currentMenu = menuOptions.get("showCreationMenu");
        processInput(getAnswer());
    }

    private static void processFullStartupMenu(int choise) {
        //TODO поменять на ENUM
        switch (choise) {
            case 1:
                processCreationMenu();
                break;
            case 2:
                processUpdateMenu();
                break;
            case 3:
                processFindMenu();
                break;
            case 4:
                exit();
                break;
        }
    }


    //TODO закончить метод
    private static void processFindMenu() {
    }

    //TODO закончить метод
    private static void processUpdateMenu() {
        Menu.showUpdateMenu();
        currentMenu = menuOptions.get("showUpdateMenu");
        processInput(getAnswer());
    }

    private static void exit() {
        return;
    }

    private static int getAnswer() {
        int choise;
        try {
            int tmp_choise;
            tmp_choise = scanner.nextInt();
            if (isInputValid(tmp_choise)) {
                choise = tmp_choise;
            } else {
                System.out.println("Неккоретный ввод!");
                scanner.nextLine();
                choise = getAnswer();
            }
        } catch (InputMismatchException e) {
            System.out.println("Неккоректный ввод!");
            scanner.nextLine();
            choise = getAnswer();
        }
        return choise;
    }


    private static boolean isInputValid(int n) {
        return currentMenu.isValidInput(n);
    }

    //вспомогательный метод отображения, создания кэша и добавления данных в DataProvider
    private static void cache(Runnable runnable, List<? extends CashedClass> list) {
        System.out.println(list);
        dataProvider.addAll(list);
        runnable.run();
    }
}
