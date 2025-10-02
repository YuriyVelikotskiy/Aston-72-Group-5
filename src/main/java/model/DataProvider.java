package model;

import classBuilder.CashedClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Хранит массив значений
 * Методы интерфейса:<p>{@link #getInstance() getInstance}</p>
 * <p>{@link #getData() getData}</p>
 * <p>{@link #getClazz() getClazz} </p>
 * <p>{@link #isEmpty() isEmpty}</p>
 * <p>{@link #add(CashedClass) add}</p>
 * <p>{@link #addAll(ArrayList) addAll}</p>
 */
public class DataProvider {
    private static final DataProvider INSTANCE = new DataProvider();
    private final List<CashedClass> data = new ArrayList<>();
    private Class<? extends CashedClass> clazz;

    private DataProvider() {
    }

    /**
     * Получение Instance
     */
    public static DataProvider getInstance() {
        return INSTANCE;
    }

    /**
     * Метод для получения записанного массива.
     */
    public List<CashedClass> getData() {
        return data;
    }

    /**
     * Метод возвращает true если в {@link DataProvider DataProvider} что-то записано
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * Метод возвращает тип объектов если в {@link DataProvider DataProvider} что-то записано и Null, если он пуст
     */
    public Class<? extends CashedClass> getClazz() {
        return clazz;
    }

    /**
     * Метод очищает {@link DataProvider DataProvider} и сбрасывает тип записанных объектов
     */
    public void clear() {
        data.clear();
        clazz = null;
    }

    /**
     * Метод добавляет одиночный объект в {@link DataProvider DataProvider}
     *
     * @param inPut - должен быть одним из 3 классов ({@link classBuilder.Author Author},
     *              {@link classBuilder.Book Book},
     *              {@link classBuilder.Publisher Publisher})
     *              , расширяющих( {@link CashedClass} )
     */
    public void add(CashedClass inPut) {
        Class<? extends CashedClass> inPutClass = inPut.getClass();
        tryAdd(() -> data.add(inPut), inPutClass);
    }

    /**
     * Метод добавляет массив объектов в {@link DataProvider DataProvider}, массив должен иметь хотя бы один элемент
     *
     * @param inPutList - должен быть {@link ArrayList}, с хранящимися в нем объектами ({@link classBuilder.Author Author},
     *                  {@link classBuilder.Book Book},
     *                  {@link classBuilder.Publisher Publisher})
     *                  , расширяющие ( {@link CashedClass} )
     */
    public void addAll(ArrayList<CashedClass> inPutList) {
        Class<? extends CashedClass> inPutClass = inPutList.get(0).getClass();
        listIsValid(inPutList, inPutClass);
        tryAdd(() -> data.addAll(inPutList), inPutClass);
    }

    /**
     * Метод определяет, состоит ли переданный массив из одинаковых объектов
     */
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

    /**
     * Вспомогательный метод добавления
     */
    private void tryAdd(Runnable runnable, Class<? extends CashedClass> inPutClass) {
        if (data.isEmpty()) {
            clazz = inPutClass;
            runnable.run();
        } else if (inPutClass.equals(clazz)) {
            runnable.run();
        } else {
            throw new IllegalArgumentException(String.format("Input isn't valid (expected %s, received %s)", clazz, inPutClass));
        }
    }
}
