package qsort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public final class PartialEvenSort {
     /**
     Дополнительное задание №1
     Сортирует только элементы, числовое поле которых четноё.
     Остальные остаются на исходных позициях.
     Стабильность по «несортируемым» сохраняется, потому что мы их не трогаем.
     */
    public static <T> void sort(List<T> list,
                                Comparator<? super T> comparator,
                                Predicate<? super T> selected) {
        // Собираем индексы подходящих элементов
        List<Integer> idx = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) { // [1, 2, 3, 4] -> [0, 2, 0, 4] - 100% не трогать элементы, неподходящие под наши условия
            if (selected.test(list.get(i))) idx.add(i); // (predicate)selected.test - проверяем четность, передавать в конструктор
        }
        if (idx.size() < 2) return;

        // Копируем выбранные элементы в отдельный список (можно подумать как избежать траты лишней памяти)
        List<T> sub = new ArrayList<>(idx.size());
        for (int i : idx) sub.add(list.get(i));

        QuickSort.quickSort(sub, comparator);

        // Возвращаем их на те же позиции
        for (int k = 0; k < idx.size(); k++) {
            list.set(idx.get(k), sub.get(k));
        }
    }
}
