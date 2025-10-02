package sortstrategy;

import classBuilder.Publisher;

import java.util.Comparator;
import java.util.List;

/** ConcreteStrategy для Publisher — ручная сортировка вставками по переданному Comparator. */
public class PublisherSortStrategy implements SortStrategy<Publisher> {

    @Override
    public void sort(List<Publisher> list, Comparator<? super Publisher> comparator) {
        if (list == null || list.size() < 2) return;

        for (int i = 1; i < list.size(); i++) {
            Publisher key = list.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }
}
