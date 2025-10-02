package sortstrategy;

import classBuilder.Book;
import java.util.Comparator;
import java.util.List;

/** ConcreteStrategy для Book — вставками, сравнение через переданный Comparator. */
public class BookSortStrategy implements SortStrategy<Book> {
    @Override
    public void sort(List<Book> list, Comparator<? super Book> comparator) {
        if (list == null || list.size() < 2) return;
        for (int i = 1; i < list.size(); i++) {
            Book key = list.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }
}
