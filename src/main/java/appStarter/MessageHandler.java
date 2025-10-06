package appStarter;


import binarySearch.BinarySearch;
import cashCreator.CashCreator;
import classBuilder.CashedClass;
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

    static {
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

    private static void initializeMenuOptions(){
        menuOptions = new HashMap<>();
        menuOptions.put("showFullStartupMenu", new MenuManager("showFullStartupMenu", 5));
        menuOptions.put("showShortStartupMenu", new MenuManager("showShortStartupMenu", 2));
        menuOptions.put("showCreationMenu", new MenuManager("showCreationMenu", 4));
        menuOptions.put("showSortMenu", new MenuManager("showSortMenu", 3));
        menuOptions.put("showChooseFieldMenu", new MenuManager("showChooseFieldMenu", 3));
        menuOptions.put("showChooseComparatorMenu", new MenuManager("showChooseComparatorMenu", 2));
    }

    public static void startMessage() throws IOException {
        if (!dataProvider.isEmpty()) {
            Menu.showFullStartupMenu();
            currentMenu=menuOptions.get("showFullStartupMenu");
            processInput(getAnswer());
        }else {
            Menu.showShortStartupMenu();
            currentMenu=menuOptions.get("showShortStartupMenu");
            processInput(getAnswer());
        }

    }

    private static void processInput(int choise) throws IOException {

        switch (currentMenu.getMenuName()){
            case "showShortStartupMenu":
                processShortStartupMenu(choise);
                break;
            case "showFullStartupMenu":
                processFullStartupMenu(choise);
                break;
            case "showCreationMenu":
                processCreationMethod(choise);
                break;
        }
    }

    private static void processCreationMethod(int choice){
        switch (choice){
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

    private static void manualInput(){
        try {
            list=Input.createManualObjects();
            cache(list);
            showInputData();
            startMessage();
        }catch (Exception e){
            System.out.println(e.getMessage());
            manualInput();
        }
    }

    private static void createRandomData() {
        try {
            list=Input.createRandomObjects();
            cache(list);
            showInputData();
            startMessage();
        }catch (Exception e){
            System.out.println(e.getMessage());
            createRandomData();
        }
    }

    private static void readFromFile(){
        try {
            list = Input.readFromFile();
            cache(list);
            showInputData();
            startMessage();
        }catch (Exception e){
            System.out.println(e.getMessage());
            readFromFile();
        }
    }

    private static void processShortStartupMenu(int choise) throws IOException {
        //TODO посмотреть можно ли сделать цепочку обязанностей
        switch (choise){
            case 1:
                processCreationMenu();
            case 2:
                exit();
        }
    }

    private static void processCreationMenu() throws IOException {
        Menu.showCreationMenu();
        currentMenu=menuOptions.get("showCreationMenu");
        processInput(getAnswer());
    }

    private static void processFullStartupMenu(int choise) throws IOException {
        //TODO поменять на ENUM
        switch (choise){
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



    private static void showSortMenu() {
        Menu.showSortMenu();
        currentMenu=menuOptions.get("showSortMenu");
        processSortMenu(getAnswer());
    }

    private static void processSortMenu(int choise) {

        switch (choise) {
            case 1:
                fields = showChooseFieldMenu(dataProvider.getClazz());
                currentMenu=menuOptions.get("showChooseFieldMenu");
                int field = processChooseField(getAnswer());
                fieldFromSort = fields.get(field);
                showChooseComparatorMenu();
                currentMenu=menuOptions.get("showChooseComparatorMenu");
                boolean ascending = processChooseComparator(getAnswer());
                cmpFromSort = useSortStrategy(dataProvider.getData(),
                        dataProvider.getClazz(), fields.get(field), ascending);
                listIsSorted = true;
                showResultData();
                restartMenu();
                break;
            case 2:
                sortOnlyEvenValues(dataProvider.getData(), dataProvider.getClazz());
                listIsSorted = false;
                fieldFromSort = null;
                cmpFromSort = null;

                showResultData();
                restartMenu();
                break;
            case 3:
                exit();
                break;
        }
    }

    private static void showResultData() {
        int counter = 1;
        for(Object o : dataProvider.getData()){
            System.out.println(counter++ + ". " + o);
        }
    }

    private static void restartMenu(){
        try {
            startMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static int processChooseField(int choise) {
        return switch (choise) {
            case 1 -> 0;
            case 2 -> 1;
            case 3 -> 2;
            default -> throw new IllegalStateException("Unexpected value: " + choise);
        };
    }

    private static boolean processChooseComparator(int choise) {
        currentMenu=menuOptions.get("showChooseComparatorMenu");
        return switch (choise) {
            case 1 -> true;
            case 2 -> false;
            default -> throw new IllegalStateException("Unexpected value: " + choise);
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
    private static void processUpdateMenu() {

    }

    private static void exit(){
        return;
    }

    private static int getAnswer(){
        int choise;
        try {
            int tmp_choise;
            tmp_choise=scanner.nextInt();
            if (isInputValid(tmp_choise)){
                choise=tmp_choise;
            }else{
                System.out.println("Неккоретный ввод!");
                scanner.nextLine();
                choise=getAnswer();
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Неккоректный ввод!");
            scanner.nextLine();
            choise=getAnswer();
        }
        return choise;
    }

    private static void showInputData() {
        System.out.println("Ваши введенные данные:");
        int counter = 1;
        for (Object item : list) {
            System.out.println(counter++ + ". " + item);
        }
    }

    private static boolean isInputValid(int n){
        return currentMenu.isValidInput(n);
    }

    private static void cache(List<?extends CashedClass> list){
        cashCreator.start(list);
        dataProvider.addAll(list);
    }
}
