package Model;

import classBuilder.CashedClass;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class DataProvider {
    private static DataProvider INSTANCE;
    private static final ArrayList<CashedClass> data = new ArrayList<>();
    private static Class<? extends CashedClass> clazz;
    private static AtomicInteger atomicInteger;

    private DataProvider() {
    }

    public static synchronized DataProvider getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataProvider();
        }
        return INSTANCE;
    }

    public static ArrayList<CashedClass> getData() {
        return data;
    }

    public static boolean isEmpty() {
        return data.isEmpty();
    }

    public static Class<? extends CashedClass> getClazz() {
        return clazz;
    }

    public static void clear(){
        data.clear();
        clazz = null;
    }

    public static void add(CashedClass inPut) {
        Class<? extends CashedClass> inPutClass = inPut.getClass();
        if (data.isEmpty()) {
            clazz = inPut.getClass();
            data.add(inPut);
        } else if (inPutClass.equals(clazz)) {
            data.add(inPut);
        } else
            throw new IllegalArgumentException(String.format("Input isn't valid (expected %s, received %s)", clazz, inPutClass));
    }

    public static void addAll(ArrayList<CashedClass> inPutList) {
        Class<? extends CashedClass> inPutClass = inPutList.get(0).getClass();
        Optional<? extends Class<? extends CashedClass>> unValidClass = listIsValid(inPutList, inPutClass);
        if (unValidClass.isPresent()) {
            throw new IllegalArgumentException(String.format("File have mistake (expected %s, received %s, position - %d)", inPutClass, unValidClass.get(), atomicInteger.intValue()));
        }
        if (data.isEmpty()) {
            clazz = inPutClass;
            data.addAll(inPutList);
        } else if (inPutClass.equals(clazz)) {
            data.addAll(inPutList);
        } else {
            throw new IllegalArgumentException(String.format("Input isn't valid (expected %s, received %s)", clazz, inPutClass));
        }
    }

    private static Optional<? extends Class<? extends CashedClass>> listIsValid(ArrayList<CashedClass> inPutList, Class<? extends CashedClass> inPutClass) {
        atomicInteger = new AtomicInteger();
        return inPutList.stream()
                .map(CashedClass::getClass)
                .peek(i -> atomicInteger.getAndIncrement())
                .filter(i -> !i.equals(inPutClass))
                .findFirst();
    }
}
