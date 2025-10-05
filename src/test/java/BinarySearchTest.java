import binarySearch.BinarySearch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTest {
    @Test
    @DisplayName("Found Element")
    void findElementWhenExists(){
        List<Integer> list = new ArrayList<>(List.of(1,2,3,4,5));
        int element = list.get(3);
        int index = BinarySearch.search(list,element, Comparator.naturalOrder());
        assertEquals(element,list.get(index));
    }
    @Test
    @DisplayName("Return -1 when element not found")
    void shouldNotFoundElement(){
        List<Integer> list = new ArrayList<>(List.of(1,2,3,4,5));
        int element = 6;
        int index = BinarySearch.search(list,element, Comparator.naturalOrder());
        assertEquals(-1, index);
    }

    @Test
    @DisplayName("Return -1 when element in empty list")
    void shouldNotFoundElementInEmptyList() {
        List<Integer> list = new ArrayList<>();
        int element = 1;
        int index = BinarySearch.search(list, element, Comparator.naturalOrder());
        assertEquals(-1, index);
    }

    @Test
    @DisplayName("Found element in large list")
    void shouldFindElementInLargeList() {
        List<Integer> list = IntStream.range(0, 1000).boxed().collect(Collectors.toList());
        int element = 703;
        int index = BinarySearch.search(list, element, Comparator.naturalOrder());
        assertEquals(703, index);
        assertEquals(element, list.get(index));
    }
}
