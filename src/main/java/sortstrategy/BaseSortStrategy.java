package sortstrategy;

import qsort.PartialEvenSort;
import qsort.QuickSort;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;

/// Базовый абстрактный класс с реализацией сортировки по полям объекта

public abstract class BaseSortStrategy<T> implements SortStrategy<T> {

    protected abstract Map<String, Comparator<T>> comparators();

    /// Только для допзадания №1
    protected abstract Map<String, ToIntFunction<T>> intExtractors();

    @Override
    public Comparator<T> sort(List<T> list, String field, boolean ascending) {
        var cmp = comparators().get(field.trim().toLowerCase());
        if (cmp == null) throw new IllegalArgumentException("Unknown field: " + field);

        if (!ascending) cmp = cmp.reversed();
        QuickSort.quickSort(list, cmp);
        return cmp;
    }

    @Override
    public Comparator<T> sort(List<T> list, String field) {
        var cmp = comparators().get(field.trim().toLowerCase());
        if (cmp == null) throw new IllegalArgumentException("Unknown field: " + field);

        QuickSort.quickSort(list, cmp);
        return cmp;
    }


    @Override
    public void sortEvenNatural(List<T> list, String numericField) {
        String key = numericField.trim().toLowerCase();
        var cmp = comparators().get(key);
        var extractor = intExtractors().get(key);

        if (cmp == null)
            throw new IllegalArgumentException("Unknown field (comparator not found): " + numericField);
        if (extractor == null)
            throw new IllegalArgumentException("Field is not numeric or extractor not provided: " + numericField);

        PartialEvenSort.sort(
                list,
                cmp,
                t -> (extractor.applyAsInt(t) % 2) == 0
        );
    }

}
