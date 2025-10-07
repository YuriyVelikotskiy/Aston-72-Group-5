package appStarter;


import binarySearch.BinarySearch;
import cashCreator.CashCreator;
import classBuilder.CashedClass;
import config.Config;
import dataProcessor.DataProcessor;
import dataProcessor.FileDataProcessor;
import dataProcessor.MemoryDataProcessor;
import dataProvider.DataProvider;

import java.io.IOException;
import java.util.*;

import static appStarter.Menu.*;
import static appStarter.UseSortStrategy.sortOnlyEvenValues;
import static appStarter.UseSortStrategy.useSortStrategy;


//TODO поиск
//TODO обновление/перезапись

public class MessageHandler {
    private static final Scanner scanner;
    private static List<? extends CashedClass> list;
    private static HashMap<String, MenuManager> menuOptions;
    private static MenuManager currentMenu;
    private static final DataProvider dataProvider;
    private static final CashCreator cashCreator;
    private static final DataProcessor dataProcessor;
    private static int lastFindElement;

    static {
        dataProcessor = new MemoryDataProcessor(new FileDataProcessor(null));
        dataProvider = DataProvider.getInstance();
        scanner = new Scanner(System.in);
        cashCreator = CashCreator.getInstance();
        initializeMenuOptions();
    }

    static List<String> fields = new ArrayList<>();
    static boolean listIsSorted = false;
    static String fieldFromSort;
    @SuppressWarnings("rawtypes")
    static Comparator cmpFromSort;

    private static void initializeMenuOptions() {
        menuOptions = new HashMap<>();
        menuOptions.put("showFullStartupMenu", new MenuManager("showFullStartupMenu", 5));
        menuOptions.put("showShortStartupMenu", new MenuManager("showShortStartupMenu", 2));
        menuOptions.put("showCreationMenu", new MenuManager("showCreationMenu", 4));
        menuOptions.put("showUpdateMenu", new MenuManager("showUpdateMenu", 4));
        menuOptions.put("showSortMenu", new MenuManager("showSortMenu", 3));
        menuOptions.put("showChooseFieldMenu", new MenuManager("showChooseFieldMenu", 3));
        menuOptions.put("showChooseComparatorMenu", new MenuManager("showChooseComparatorMenu", 2));
        menuOptions.put("showSaveSortMenu", new MenuManager("showSaveSortMenu", 2));
        menuOptions.put("showSaveFindMenu", new MenuManager("showSaveFindMenu", 2));
    }


    public static void startMessage() throws IOException {
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

    private static void processInput(int choice) throws IOException {

        switch (currentMenu.getMenuName()) {
            case "showShortStartupMenu":
                processShortStartupMenu(choice);
                break;

            case "showFullStartupMenu":
                processFullStartupMenu(choice);
                break;
            case "showCreationMenu":
                processCreationMethod(choice);
                break;
            case "showUpdateMenu":
                processUpdateMethod(choice);
                break;
            case "showSaveSortMenu":
                processSaveSortMethod(choice);
                break;
            case "showSaveFindMenu":
                processSaveFindMethod(choice);
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
        dataProvider.clear();
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
        try {
            if (currentMenu.getMenuName().equals("showUpdateMenu")) {
                list = Input.createManualObjects();
                cache(() -> CashCreator.getInstance().start(list, Config.getCASHPATH()), list);
                startMessage();
            } else {
                list = Input.createManualObjects();
                cache(() -> CashCreator.getInstance().start(list), list);
                startMessage();
            }
        } catch (Exception e) {
            System.out.println("Некорректный ввод!!!");
            manualInput();
        }
    }


    //TODO закончить метод
    private static void createRandomData() {
        try {
            if (currentMenu.getMenuName().equals("showUpdateMenu")) {
                list = Input.createRandomObjects();
                cache(() -> CashCreator.getInstance().start(list, Config.getCASHPATH(), true), list);
                startMessage();
            } else {
                list = Input.createRandomObjects();
                cache(() -> CashCreator.getInstance().start(list), list);
                startMessage();
            }
        } catch (Exception e) {
            System.out.println("Некорректный ввод!!!");
            createRandomData();
        }
    }

    private static void readFromFile() {
        try {
            if (currentMenu.getMenuName().equals("showUpdateMenu")) {
                list = Input.readFromFile();
                cache(() -> CashCreator.getInstance().start(list, Config.getCASHPATH(), true), list);
                startMessage();
            } else {
                list = Input.readFromFile();
                cache(() -> CashCreator.getInstance().start(list), list);
                startMessage();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            readFromFile();
        }
    }

    private static void processShortStartupMenu(int choice) throws IOException {
        //TODO посмотреть можно ли сделать цепочку обязанностей
        switch (choice) {
            case 1:
                processCreationMenu();
            case 2:
                exit();
        }
    }

    private static void processCreationMenu() throws IOException {
        Menu.showCreationMenu();
        currentMenu = menuOptions.get("showCreationMenu");
        processInput(getAnswer());
    }

    private static void processFullStartupMenu(int choice) throws IOException {
        //TODO поменять на ENUM
        switch (choice) {
            case 1:
                processCreationMenu();
                break;
            case 2:
                processUpdateMenu();
                break;
            case 3:
                showSortMenu();
                break;
            case 4:
                processFindMenu();
                break;
            case 5:
                exit();
                break;
        }
    }


    private static void showSortMenu() throws IOException {
        Menu.showSortMenu();
        currentMenu = menuOptions.get("showSortMenu");
        processSortMenu(getAnswer());
    }

    private static void processSortMenu(int choice) throws IOException {

        switch (choice) {
            case 1:
                fields = showChooseFieldMenu(dataProvider.getClazz());
                currentMenu = menuOptions.get("showChooseFieldMenu");
                int field = processChooseField(getAnswer());
                fieldFromSort = fields.get(field);
                showChooseComparatorMenu();
                currentMenu = menuOptions.get("showChooseComparatorMenu");
                boolean ascending = processChooseComparator(getAnswer());
                cmpFromSort = useSortStrategy(dataProvider.getData(),
                        dataProvider.getClazz(), fields.get(field), ascending);
                listIsSorted = true;
                showResultData();
                processSaveSortMenu();
                restartMenu();
                break;
            case 2:
                sortOnlyEvenValues(dataProvider.getData(), dataProvider.getClazz());
                listIsSorted = false;
                fieldFromSort = null;
                cmpFromSort = null;
                showResultData();
                processSaveSortMenu();
                restartMenu();
                break;
            case 3:
                exit();
                break;
        }
    }

    private static void showResultData() {
        int counter = 1;
        for (Object o : dataProvider.getData()) {
            System.out.println(counter++ + ". " + o);
        }
    }

    private static void restartMenu() {
        try {
            startMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static int processChooseField(int choice) {
        return switch (choice) {
            case 1 -> 0;
            case 2 -> 1;
            case 3 -> 2;
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        };
    }

    private static boolean processChooseComparator(int choice) {
        currentMenu = menuOptions.get("showChooseComparatorMenu");
        return switch (choice) {
            case 1 -> true;
            case 2 -> false;
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        };

    }

    private static void processFindMenu() {
        try {
            if (listIsSorted && fieldFromSort != null && cmpFromSort != null) {
                System.out.printf("Введите значение %s объекта, который хотите найти\n", fieldFromSort); //TODO вынести отсюда
                scanner.nextLine();
                String value = scanner.nextLine();
                if (value.isEmpty()) System.out.println("Введите еще раз");
                int idx = BinarySearch.search(dataProvider.getData(),
                        cmpFromSort, fieldFromSort, value);
                if (idx >= 0) {
                    System.out.println(idx + 1 + ". " + dataProvider.getData().get(idx));
                    lastFindElement = idx;
                    processSaveFindMenu();
                } else {
                    System.out.println("Нет такого элемента");
                }

                try {
                    startMessage();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Бинарный поиск возможен только для отсортированных данных!\n");
                processSortMenu(1);
            }
        } catch (Exception e) {
            System.out.println("Некорректный ввод!");
            processFindMenu();
        }
    }

    //TODO закончить метод
    private static void processUpdateMenu() throws IOException {
        Menu.showUpdateMenu();
        currentMenu = menuOptions.get("showUpdateMenu");
        processInput(getAnswer());
    }

    private static void exit() {
        return;
    }

    private static int getAnswer() {
        int choice;
        try {
            int tmp_choice;
            tmp_choice = scanner.nextInt();
            if (isInputValid(tmp_choice)) {
                choice = tmp_choice;
            } else {
                System.out.println("Некорректный ввод!");
                scanner.nextLine();
                choice = getAnswer();
            }
        } catch (InputMismatchException e) {
            System.out.println("Некорректный ввод!");
            scanner.nextLine();
            choice = getAnswer();
        }
        return choice;
    }

    private static void showInputData() {
        System.out.println("Ваши введенные данные:");
        int counter = 1;
        for (Object item : list) {
            System.out.println(counter++ + ". " + item);
        }
    }

    private static boolean isInputValid(int n) {
        return currentMenu.isValidInput(n);
    }

    //вспомогательный метод отображения, создания кэша и добавления данных в DataProvider
    private static void cache(Runnable runnable, List<? extends CashedClass> list) {
        dataProvider.addAll(list);
        showResultData();
        runnable.run();

    }

    private static void processSaveSortMenu() throws IOException {
        Menu.showSaveMenu();
        currentMenu = menuOptions.get("showSaveSortMenu");
        processInput(getAnswer());
    }
    private static void processSaveSortMethod(int choice){
        if (choice == 1) {
            CashCreator instance = CashCreator.getInstance();
            instance.start(dataProvider.getData(),Config.getRESULT(),true);
            System.out.println("Файлы будет сохранены в " + Config.getRESULT());
        }
    }

    private static void processSaveFindMenu() throws IOException {
        Menu.showSaveMenu();
        currentMenu = menuOptions.get("showSaveFindMenu");
        processInput(getAnswer());
    }

    private static void processSaveFindMethod(int choice){
        if (choice == 1) {
            CashCreator instance = CashCreator.getInstance();
            instance.start(Collections.singletonList(dataProvider.getData().get(lastFindElement)),Config.getRESULTFIND(),true);
            System.out.println("Файлы будет сохранены в " + Config.getRESULTFIND());
        }
    }
}
