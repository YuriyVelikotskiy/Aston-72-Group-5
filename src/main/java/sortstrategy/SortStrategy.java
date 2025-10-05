package sortstrategy;

import java.util.Comparator;
import java.util.List;

    /// Интерфейс для реализации стратегии сортировки по полям объекта

public interface SortStrategy<T> {

    Comparator<T> sort(List<T> list, String field, boolean ascending);

    Comparator<T> sort(List<T> list, String field);

    /// Дополнительное задание №1
    void sortEvenNatural(List<T> list, String numericField);
}
