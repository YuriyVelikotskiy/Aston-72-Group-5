package appStarter;

public class MenuManager {
    private int minButtons;
    private int maxButtons;

    public MenuManager(int minButtons, int maxButtons) {
        this.minButtons = minButtons;
        this.maxButtons = maxButtons;
    }

    public boolean isValidInput(int option){
        return option>=minButtons && option<=maxButtons;
    }
}
