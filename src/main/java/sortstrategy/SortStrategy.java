package sortstrategy;

import java.util.List;

    /// Интерфейс для реализации стратегии сортировки по полям объекта

public interface SortStrategy<T> {

    default void sort(List<T> list, String field, boolean ascending) {}

    void sort(List<T> list, String field);
}
