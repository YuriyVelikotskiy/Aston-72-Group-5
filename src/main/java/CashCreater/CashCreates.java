package CashCreater;

import classBuilder.CashedClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class CashCreates implements Runnable {
    private static final Path CASHPATH = Paths.get(System.getProperty("user.dir") + "\\cash");
    private static CashCreates instance;
    private Thread cashThread;
    private ArrayList<CashedClass> pendingCacheItems;


    private CashCreates() {
        if (instance != null) {
            throw new IllegalStateException("CashCreater Already exist");
        }
    }

    public static synchronized CashCreates getInstance() {
        if (instance == null) {
            return new CashCreates();
        }
        return instance;
    }


    @Override
    public void run() {
        StringBuffer cash = new StringBuffer();
        pendingCacheItems.forEach(item ->
                cash.append(item.toJSON()).append(","));
        cash.deleteCharAt(cash.length()-1);
        try {
            Files.writeString(CASHPATH,cash.toString(),StandardOpenOption.TRUNCATE_EXISTING,StandardOpenOption.CREATE );
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    public void start(ArrayList<CashedClass> pendingCacheItems) {
        if (cashThread == null || !cashThread.isAlive()) {
            this.pendingCacheItems = pendingCacheItems;
            cashThread = new Thread(this, "Cash-Thread");
            cashThread.start();
        }
    }
}
