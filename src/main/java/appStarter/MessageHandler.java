package appStarter;


import cashCreator.CashCreator;
import classBuilder.CashedClass;
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

    static {
        dataProvider = DataProvider.getInstance();
        scanner = new Scanner(System.in);
        initializeMenuOptions();
    }

    private static void initializeMenuOptions(){
        menuOptions = new HashMap<>();
        menuOptions.put("showFullStartupMenu", new MenuManager("showFullStartupMenu", 4));
        menuOptions.put("showShortStartupMenu", new MenuManager("showShortStartupMenu", 2));
        menuOptions.put("showCreationMenu", new MenuManager("showCreationMenu", 4));
    }


    public static void startMessage(){
        boolean tmp_validation = false;
        if (!dataProvider.isEmpty()){
            Menu.showFullStartupMenu();
            currentMenu=menuOptions.get("showFullStartupMenu");
            processInput(getAnswer());
        }else {
            Menu.showShortStartupMenu();
            currentMenu=menuOptions.get("showShortStartupMenu");
            processInput(getAnswer());
        }

    }
    private static void processInput(int choise){

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

    private static void processCreationMethod(int choice) {
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

    //TODO закончить метод
    private static void manualInput() {

        list=Input.createManualObjects();
        cache(list);
        startMessage();

    }
    //TODO закончить метод
    private static void createRandomData() {

        list=Input.createRandomObjects();
        System.out.println(list);
        cache(list);
        startMessage();
    }
    //TODO закончить метод
    private static void readFromFile() {
        System.out.println("какая - то логика");
        startMessage();
    }

    private static void processShortStartupMenu(int choise) {
        //TODO посмотреть можно ли сделать цепочку обязанностей
        switch (choise){
            case 1:
                processCreationMenu();
            case 2:
                exit();
        }
    }

    private static void processCreationMenu() {
        Menu.showCreationMenu();
        currentMenu=menuOptions.get("showCreationMenu");
        processInput(getAnswer());
    }

    private static void processFullStartupMenu(int choise) {
       //TODO поменять на ENUM
        switch (choise){
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



    private static boolean isInputValid(int n){
        return currentMenu.isValidInput(n);
    }

    private static void cache(List<?extends CashedClass> list){
        System.out.println(list);

        CashCreator cashCreator = CashCreator.getInstance();
        dataProvider.addAll(list);
        cashCreator.start(list);

    }
}
