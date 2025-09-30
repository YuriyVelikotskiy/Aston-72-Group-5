package cashCreator;

import classBuilder.CashedClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/// Запись осуществляется по средством вызова метода start(список), в этом случае берется путь по умолчанию из CASHPATH.
/// Или start(список, путь). Тогда файл создастся по указанному пути.
/// Файлы перезаписываются заново каждый раз.
/// Метод start(список, путь, true) использует запись с добавлением в конец.
/// В случае если файл не существует, то создастся новый файл
public class CashCreates {
    private static final Path CASHPATH = Paths.get(System.getProperty("user.dir") + "\\cash");
    private static CashCreates instance;
    private Thread cashThread;
    private final BlockingQueue<CashTask> queue = new LinkedBlockingQueue<>();

    /// Метод получения Instance с защитой от рефлексии
    private CashCreates() {
        if (instance != null) {
            throw new IllegalStateException("CashCreater Already exist");
        }
    }

    /// Ленивая инициализация
    public static synchronized CashCreates getInstance() {
        if (instance == null) {
            return new CashCreates();
        }
        return instance;
    }

    /// Метод создания кеша по умолчанию (берет путь из CASHPATH)
    public void start(ArrayList<CashedClass> list) {
        start(new CashTask(list,CASHPATH));
    }
    /// Метод создания кеша с указанием пути
    public void start(ArrayList<CashedClass> list, Path path) {
        start(new CashTask(list,path));
    }

    ///Метод с указанием пути и режимом добавления данных
    public void start(ArrayList<CashedClass> list, Path path, boolean addMode) {
        start(new CashTask(list,path).setAddMode(addMode));
    }

    private void start(CashTask task){
        //поток создается только по необходимости
        if (cashThread == null || !cashThread.isAlive()) {
            cashThread = new Thread(this::createCash, "Cash-Thread");
            cashThread.start();
        }

        // Кладем список в очередь для выполнения
        try {
            queue.put(task);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /// Создание строки под запись кеша
    private void createCash() {
        while (!queue.isEmpty()) {
            try {
                CashTask task= queue.poll(100, TimeUnit.MILLISECONDS);
                if (task != null) {
                    addCash(task);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

        }
    }

    /// Непосредственная запись в файл
    private void addCash(CashTask task) {
        StringBuffer cash = new StringBuffer();
        task.getToCash().forEach(item ->
                cash.append(item.toJSON()).append(","));
        cash.deleteCharAt(cash.length() - 1);
        try {
            if (task.getAddMode()) {
                Files.writeString(task.getPath(), cash.toString(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            } else {
                Files.writeString(task.getPath(), cash.toString(), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /// Задача под запись
    private static class CashTask{
        private final ArrayList<CashedClass> toCash;
        private boolean addMode = false;
        private final Path path;

        public CashTask(ArrayList<CashedClass> toCash,Path path){
            this.toCash = toCash;
            this.path = path;
        }



        public CashTask setAddMode(boolean addMode) {
            this.addMode = addMode;
            return this;
        }

        public Path getPath() {
            return path;
        }

        public boolean getAddMode() {
            return addMode;
        }

        public ArrayList<CashedClass> getToCash() {
            return toCash;
        }
    }
}
