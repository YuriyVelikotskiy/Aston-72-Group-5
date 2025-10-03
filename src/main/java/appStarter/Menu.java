package appStarter;

import appConfiguration.MessageConfigurator;

public class Menu {

    public static void showFullStartupMenu() {
        System.out.println(
                "1."+ MessageConfigurator.CREATE+"\n"+
                "2."+MessageConfigurator.UPDATE+"\n"+
                "3."+MessageConfigurator.FIND+"\n"+
                "4."+MessageConfigurator.EXIT
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


    public static void showFindMenu() {
        System.out.println("Введите элемент по которому искать");
    }

    public static void showUpdateMenu() {
        System.out.println("Введите данные на обновление");
    }
}

