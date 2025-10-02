package sortstrategy;

import classBuilder.Author;

import java.util.Comparator;
import java.util.List;

/**
 * ConcreteStrategy — конкретная стратегия сортировки для Author.
 * Реализована ручная сортировка вставками (Insertion Sort), без библиотечных сортировок.
 * Сравнение — через переданный Comparator (какое поле — решаешь снаружи).
 */
public class AuthorSortStrategy implements SortStrategy<Author> {

    @Override
    public void sort(List<Author> list, Comparator<? super Author> comparator) {
        if (list == null || list.size() < 2) return;

        // Ручной Insertion Sort
        for (int i = 1; i < list.size(); i++) {
            Author key = list.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }
}