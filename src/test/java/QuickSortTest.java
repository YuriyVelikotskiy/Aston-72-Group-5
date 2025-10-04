import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qsort.QuickSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {
    private final Comparator<? super Integer> cmp = Comparator.naturalOrder();
    private final Comparator<? super Integer> reverseCmp = Comparator.reverseOrder();

    @Test
    @DisplayName("Пустой лист - нет изменений")
    void shouldNotBeChangedWithEmptyList() {
        List<Integer> list = new ArrayList<>();
        QuickSort.quickSort(list, cmp);
        assertTrue(list.isEmpty());
    }

    @Test
    @DisplayName("Один элемент - нет изменений")
    void shouldNotBeChangedWithOneElement() {
        List<Integer> list = new ArrayList<>(List.of(52));
        QuickSort.quickSort(list, cmp);
        assertEquals(List.of(52), list);
    }

    @Test
    @DisplayName("Отсортированный лист - нет изменений")
    void shouldNotChangedWithSortedList() {
        List<Integer> list = new ArrayList<>(List.of(1,2,3,4,5));
        List<Integer> copy = new ArrayList<>(list);
        QuickSort.quickSort(list, cmp);
        assertEquals(copy, list);
    }

    @Test
    @DisplayName("Проверка сортировки")
    void shouldSortList() {
        List<Integer> list = new ArrayList<>(List.of(5,4,3,2,1));
        QuickSort.quickSort(list, cmp);
        assertEquals(new ArrayList<>(List.of(1,2,3,4,5)), list);
    }

    @Test
    @DisplayName("Проверка сортировки в обратном порядке")
    void shouldSortListWIthReverseOrder() {
        List<Integer> list = new ArrayList<>(List.of(1,2,3,4,5));
        QuickSort.quickSort(list, reverseCmp);
        assertEquals(new ArrayList<>(List.of(5,4,3,2,1)), list);
    }

    @Test
    @DisplayName("Лист с дублями - сортировка корректна")
    void shouldSortListWithDuplicates() {
        List<Integer> list = new ArrayList<>(List.of(5,6,3,3,4,4,3,2,1,1,1,5,6));
        List<Integer> sortedCopy = new ArrayList<>(list);
        sortedCopy.sort(Comparator.naturalOrder());
        QuickSort.quickSort(list, cmp);
        assertEquals(sortedCopy, list);
    }

    @Test
    @DisplayName("Тест корректности при большом объеме входных данных")
    void largeDataShouldBeCorrect() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            list.add((int)(Math.random() * 1000));
        }
        List<Integer> sortedCopy = new ArrayList<>(list);
        sortedCopy.sort(Comparator.naturalOrder());
        QuickSort.quickSort(list, cmp);
        assertEquals(sortedCopy, list);
    }
}
