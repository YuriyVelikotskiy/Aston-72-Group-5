package appStarter;


import cashCreator.CashCreator;
import classBuilder.CashedClass;
import dataProvider.DataProvider;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;


//TODO поиск
//TODO обновление/перезапись

public class MessageHandler {
    private static Scanner scanner;
    private static List<? extends CashedClass> list;
    private static HashMap<String, MenuManager> menuOptions;
    private static MenuManager currentMenu;
    private static DataProvider dataProvider;
    private static CashCreator cashCreator;

    static {
        dataProvider = DataProvider.getInstance();
        scanner = new Scanner(System.in);
        cashCreator = CashCreator.getInstance();
        initializeMenuOptions();
    }

    private static void initializeMenuOptions(){
        menuOptions = new HashMap<>();
        menuOptions.put("showFullStartupMenu", new MenuManager("showFullStartupMenu", 4));
        menuOptions.put("showShortStartupMenu", new MenuManager("showShortStartupMenu", 2));
        menuOptions.put("showCreationMenu", new MenuManager("showCreationMenu", 4));
    }

    public static void startMessage() throws IOException {
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
