import classBuilder.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qsort.QuickSort;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {
    @Test
    @DisplayName("Пустой лист - нет изменений")
    void shouldNotBeChangedWithEmptyList() {
        List<Integer> list = new ArrayList<>();
        QuickSort.quickSort(list);
        assertTrue(list.isEmpty());
    }

    @Test
    @DisplayName("Один элемент - нет изменений")
    void shouldNotBeChangedWithOneElement() {
        List<Integer> list = new ArrayList<>(List.of(52));
        QuickSort.quickSort(list);
        assertEquals(List.of(52), list);
    }

    @Test
    @DisplayName("Значение null - NPE")
    void shouldThrowNPEWithNullArgs() {
        assertThrows(NullPointerException.class,
                () -> QuickSort.quickSort(null));
    }

    @Test
    @DisplayName("Отсортированный лист - нет изменений")
    void shouldNotChangedWithSortedList() {
        List<Integer> list = new ArrayList<>(List.of(1,2,3,4,5));
        List<Integer> copy = new ArrayList<>(list);
        QuickSort.quickSort(list);
        assertEquals(copy, list);
    }

    @Test
    @DisplayName("Проверка сортировки")
    void shouldSortList() {
        List<Integer> list = new ArrayList<>(List.of(5,4,3,2,1));
        QuickSort.quickSort(list);
        assertEquals(new ArrayList<>(List.of(1,2,3,4,5)), list);
    }

    @Test
    @DisplayName("Проверка сортировки в обратном порядке")
    void shouldSortListWIthReverseOrder() {
        List<Integer> list = new ArrayList<>(List.of(1,2,3,4,5));
        QuickSort.quickSort(list, false);
        assertEquals(new ArrayList<>(List.of(5,4,3,2,1)), list);
    }

    @Test
    @DisplayName("Лист с дублями - сортировка корректна")
    void shouldSortListWithDuplicates() {
        List<Integer> list = new ArrayList<>(List.of(5,6,3,3,4,4,3,2,1,1,1,5,6));
        List<Integer> sortedCopy = new ArrayList<>(list);
        sortedCopy.sort(Comparator.naturalOrder());
        QuickSort.quickSort(list);
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
        QuickSort.quickSort(list);
        assertEquals(sortedCopy, list);
    }

    @Test
    @DisplayName("Тест с классом Author")
    void authorShouldBeCorrect() {
        List<Author> list = new ArrayList<>();
        Author a1 = new Author.AuthorBuilder().birthAYear(1).country("a").name("A").build();
        Author a2 = new Author.AuthorBuilder().birthAYear(2).country("b").name("B").build();
        Author a3 = new Author.AuthorBuilder().birthAYear(3).country("c").name("C").build();
        Author a4 = new Author.AuthorBuilder().birthAYear(4).country("d").name("D").build();
        list.add(a4);
        list.add(a3);
        list.add(a2);
        list.add(a1);
        List<Author> sortedCopy = new ArrayList<>(list);
        QuickSort.quickSort(list);
        sortedCopy.sort(Comparator.naturalOrder());
        assertEquals(sortedCopy, list);
    }

    @Test
    @DisplayName("Тест сортировки со значениями null")
    void nullsGoToTheEnd_Natural() {
        List<Integer> list = new ArrayList<>(Arrays.asList(3, null, 1, null, 2));
        QuickSort.quickSort(list);
        assertEquals(Arrays.asList(1, 2, 3, null, null), list);
    }
}
