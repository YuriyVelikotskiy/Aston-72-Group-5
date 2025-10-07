package appStarter;

import appConfiguration.MessageConfigurator;
import classBuilder.CashedClass;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Menu {

    public static void showFullStartupMenu() {
        System.out.println(
                "1."+ MessageConfigurator.CREATE+"\n"+
                "2."+MessageConfigurator.UPDATE+"\n"+
                "3."+MessageConfigurator.SORT+"\n"+
                "4."+MessageConfigurator.FIND+"\n"+
                "5."+MessageConfigurator.EXIT
        );
    }


    public static void showShortStartupMenu() {
        System.out.println(
                "1."+MessageConfigurator.CREATE+"\n"+
                "2."+MessageConfigurator.EXIT
        );
    }

    public static void showCreationMenu(){
        System.out.println(
                MessageConfigurator.CHOOSE_METHOD+"\n"+
                        "1."+MessageConfigurator.FILE_METHOD+"\n" +
                        "2."+MessageConfigurator.RANDOM_METHOD+"\n"+
                        "3."+MessageConfigurator.MANUAL_METHOD+"\n"+
                        "4."+MessageConfigurator.EXIT
        );
    }

    public static void showSortMenu(){
        System.out.println(
                MessageConfigurator.CHOOSE_SORT_METHOD+"\n"+
                        "1."+MessageConfigurator.SORT_BY_FIELD+"\n"+
                        "2."+MessageConfigurator.EXTRA_TASK1+"\n"+
                        "3."+MessageConfigurator.EXIT
        );
    }

    public static List<String> showChooseFieldMenu(Class<? extends CashedClass> type) {
        Field[] fields = type.getDeclaredFields();

        System.out.println("Выберите поле для сортировки " + type.getSimpleName() + "s:");
        int i = 1;
        for (Field f : fields) {
            System.out.println(i++ + "." + f.getName());
        }

        List<String> names = new ArrayList<>();
        for (Field f : fields) {
            names.add(f.getName());
        }
        return names;
    }

    public static void showChooseComparatorMenu() {
        System.out.println("В каком порядке будут отсортированы данные"+"\n"+
                "1."+MessageConfigurator.NATURAL_ORDER+"\n" +
                "2."+MessageConfigurator.REVERSE_ORDER);
    }

    public static void showFindMenu() {System.out.println("Введите элемент который будем искать");}

    public static void showUpdateMenu() {
        System.out.println(
                MessageConfigurator.CHOOSE_UPDATE_METHOD+"\n"+
                        "1."+MessageConfigurator.FILE_UPDATE_METHOD+"\n" +
                        "2."+MessageConfigurator.RANDOM_UPDATE_METHOD+"\n"+
                        "3."+MessageConfigurator.MANUAL_UPDATE_METHOD+"\n"+
                        "4."+MessageConfigurator.EXIT
        );
    }

    public static void showSaveMenu(){
        System.out.println(
            MessageConfigurator.CHOOSE_SAVE +"\n"+
                    "1. Да"+"\n"+
                    "2. Нет"
        );
    }
}

