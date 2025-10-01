import classBuilder.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qsort.QuickSort;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {
    @Test
    @DisplayName("Empty list - no changes, no errors")
    void shouldNotBeChangedWithEmptyList() {
        List<Integer> list = new ArrayList<>();
        QuickSort.quickSort(list);
        assertTrue(list.isEmpty());
    }

    @Test
    @DisplayName("One element - no changes")
    void shouldNotBeChangedWithOneElement() {
        List<Integer> list = new ArrayList<>(List.of(52));
        QuickSort.quickSort(list);
        assertEquals(List.of(52), list);
    }

    @Test
    @DisplayName("Null args - NPE")
    void shouldThrowNPEWithNullArgs() {
        assertThrows(NullPointerException.class,
                () -> QuickSort.quickSort(null));
    }

    @Test
    @DisplayName("Sorted List - no changes")
    void shouldNotChangedWithSortedList() {
        List<Integer> list = new ArrayList<>(List.of(1,2,3,4,5));
        List<Integer> copy = new ArrayList<>(list);
        QuickSort.quickSort(list);
        assertEquals(copy, list);
    }

    @Test
    @DisplayName("SortTest")
    void shouldSortList() {
        List<Integer> list = new ArrayList<>(List.of(5,4,3,2,1));
        QuickSort.quickSort(list);
        assertEquals(new ArrayList<>(List.of(1,2,3,4,5)), list);
    }

    @Test
    @DisplayName("ReversedComparator SortTest")
    void shouldSortListWIthReverseOrder() {
        List<Integer> list = new ArrayList<>(List.of(1,2,3,4,5));
        QuickSort.quickSort(list, false);
        assertEquals(new ArrayList<>(List.of(5,4,3,2,1)), list);
    }

    @Test
    @DisplayName("Valid sort with duplicates")
    void shouldSortListWithDuplicates() {
        List<Integer> list = new ArrayList<>(List.of(5,6,3,3,4,4,3,2,1,1,1,5,6));
        List<Integer> sortedCopy = new ArrayList<>(list);
        sortedCopy.sort(Comparator.naturalOrder());
        QuickSort.quickSort(list);
        assertEquals(sortedCopy, list);
    }

    @Test
    @DisplayName("test with large data")
    void shouldSortListWithLargeData() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            list.add((int)(Math.random() * 1000));
        }
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> QuickSort.quickSort(list));
    }

    @Test
    @DisplayName("LargeData sort must be correct")
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
    @DisplayName("Test with Author")
    void authorShouldBeCorrect() {
        List<Author> list = new ArrayList<>();
        Author.AuthorBuilder builder = new Author.AuthorBuilder();
        Author a1 = builder.birthAYear(1).country("a").name("A").build();
        Author a2 = builder.birthAYear(2).country("b").name("B").build();
        Author a3 = builder.birthAYear(3).country("c").name("C").build();
        Author a4 = builder.birthAYear(4).country("d").name("D").build();
        list.add(a1);
        list.add(a2);
        list.add(a3);
        list.add(a4);
        List<Author> sortedCopy = new ArrayList<>(list);
        QuickSort.quickSort(list);
        sortedCopy.sort(Comparator.naturalOrder());
        assertEquals(sortedCopy, list);
    }
}
