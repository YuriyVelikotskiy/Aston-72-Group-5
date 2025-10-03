package sortstrategy;

import qsort.QuickSort;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public abstract class BaseSortStrategy<T> implements SortStrategy<T> {

    protected abstract Map<String, Comparator<T>> comparators();

    @Override
    public void sort(List<T> list, String field, boolean ascending) {
        var cmp = comparators().get(field.trim().toLowerCase());
        if (cmp == null) throw new IllegalArgumentException("Unknown field: " + field);

        if (!ascending) cmp = cmp.reversed();
        QuickSort.quickSort(list, cmp);
    }

    @Override
    public void sort(List<T> list, String field) {
        var cmp = comparators().get(field.trim().toLowerCase());
        if (cmp == null) throw new IllegalArgumentException("Unknown field: " + field);

        QuickSort.quickSort(list, cmp);
    }
}
