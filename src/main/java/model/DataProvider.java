package model;

import classBuilder.CashedClass;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DataProvider {
    private static final DataProvider INSTANCE = new DataProvider();
    private final CopyOnWriteArrayList<CashedClass> data = new CopyOnWriteArrayList<>();
    private final Lock lock = new ReentrantLock(true);
    private Class<? extends CashedClass> clazz;

    private DataProvider() {}

    public static DataProvider getInstance() {
        return INSTANCE;
    }

    public ArrayList<CashedClass> getData() {
        return new ArrayList<>(data);
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public Class<? extends CashedClass> getClazz() {
        return clazz;
    }

    public void clear() {
        tryUpdate(() -> {
            data.clear();
            clazz = null;
        });
    }

    public void add(CashedClass inPut) {
        tryUpdate(() -> {
            Class<? extends CashedClass> inPutClass = inPut.getClass();
            if (data.isEmpty()) {
                clazz = inPut.getClass();
                data.add(inPut);
            } else if (inPutClass.equals(clazz)) {
                data.add(inPut);
            } else
                throw new IllegalArgumentException(String.format("Input isn't valid (expected %s, received %s)", clazz, inPutClass));
        });
    }

    public void addAll(ArrayList<CashedClass> inPutList) {
        tryUpdate(() -> {
            Class<? extends CashedClass> inPutClass = inPutList.get(0).getClass();
            listIsValid(inPutList, inPutClass);
            if (data.isEmpty()) {
                clazz = inPutClass;
                data.addAll(inPutList);
            } else if (inPutClass.equals(clazz)) {
                data.addAll(inPutList);
            } else {
                throw new IllegalArgumentException(String.format("Input isn't valid (expected %s, received %s)", clazz, inPutClass));
            }
        });
    }

    private void listIsValid(ArrayList<CashedClass> inPutList, Class<? extends CashedClass> inPutClass) {
        AtomicInteger atomicInteger = new AtomicInteger();
        Optional<? extends Class<? extends CashedClass>> unValidClass = inPutList.stream()
                .map(CashedClass::getClass)
                .peek(i -> atomicInteger.getAndIncrement())
                .filter(i -> !i.equals(inPutClass))
                .findFirst();
        if (unValidClass.isPresent()) {
            throw new IllegalArgumentException(String.format("File have mistake (expected %s, received %s, position - %d)", inPutClass, unValidClass.get(), atomicInteger.intValue()));
        }
    }

    private void tryUpdate(Runnable runnable) {
        if (lock.tryLock()) {
            try {
                runnable.run();
            } finally {
                lock.unlock();
            }
        } else throw new RuntimeException(" ");
    }
}
