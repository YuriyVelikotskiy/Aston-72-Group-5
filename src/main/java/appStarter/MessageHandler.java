package appStarter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MessageHandler {
    private static Scanner scanner;
    private static ArrayList<String> list;
    private static HashMap<String, MenuManager> menuOptions;
    private static MenuManager currentMenu;
    private static int choise;

    static {
        scanner = new Scanner(System.in);
        list = new ArrayList<>();
        initializeMenuOptions();
    }

    private static void initializeMenuOptions(){
        menuOptions = new HashMap<>();
        menuOptions.put("showFullStartupMenu", new MenuManager(1, 4));
        menuOptions.put("showShortStartupMenu", new MenuManager(1, 2));
        menuOptions.put("showCreationMenu", new MenuManager(1, 4));
    }


    public static void startMessage(){
        boolean tmp_validation = true;
        if (tmp_validation){
            Menu.showFullStartupMenu();
            currentMenu=menuOptions.get("showFullStartupMenu");
        }else {
            Menu.showShortStartupMenu();
            currentMenu=menuOptions.get("showShortStartupMenu");
        }
        getAnswer();
        processInput(choise);
    }
    private static void processInput(int choise){

    }


    private static void exit(){
        return;
    }

    private static void getAnswer(){
        int tmp_choice;
        try {
            tmp_choice=scanner.nextInt();
            if (isInputValid(tmp_choice)){
                choise=tmp_choice;
            }else{
                System.out.println("Неккоретный ввод!");
                startMessage();
            }
        } catch (InputMismatchException e) {
            System.out.println("Неккоректный ввод!");
            scanner.nextLine();
            startMessage();
        }
    }

    private static boolean isInputValid(int n){
        return currentMenu.isValidInput(n);
    }

    public static void getMessage(){

    }

}
