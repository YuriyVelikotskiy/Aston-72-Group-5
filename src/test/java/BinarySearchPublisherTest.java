import binarySearch.BinarySearch;
import classBuilder.Publisher;
import classBuilder.Randomization;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sortstrategy.PublisherSortStrategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchPublisherTest {

    @Test
    @DisplayName("Издатель: поиск по name (ASC) — найдено")
    void publisher_searchByName_ASC_found() {
        List<Publisher> list = Randomization.getRandomPublishers(20);
        var strategy = new PublisherSortStrategy();
        Comparator<Publisher> cmp = strategy.sort(list, "name");

        String probe = list.get(list.size() / 2).getName();
        int idx = BinarySearch.search(list, cmp, "name", probe);

        assertNotEquals(-1, idx);
        assertEquals(0, cmp.compare(list.get(idx),
                new Publisher.PublisherBuilder().name(probe).build()));
    }

    @Test
    @DisplayName("Издатель: поиск по city (DESC) — найдено")
    void publisher_searchByCity_DESC_found() {
        List<Publisher> list = Randomization.getRandomPublishers(20);
        var strategy = new PublisherSortStrategy();
        Comparator<Publisher> cmpDesc = strategy.sort(list, "city", false);

        String probe = list.get(list.size() / 3).getCity();
        int idx = BinarySearch.search(list, cmpDesc, "city", probe);

        assertNotEquals(-1, idx);
        assertEquals(0, cmpDesc.compare(list.get(idx),
                new Publisher.PublisherBuilder().city(probe).build()));
    }

    @Test
    @DisplayName("Издатель: поиск по foundingyear — найдено")
    void publisher_searchByYear_found() {
        List<Publisher> list = Randomization.getRandomPublishers(20);
        var strategy = new PublisherSortStrategy();
        Comparator<Publisher> cmp = strategy.sort(list, "foundingyear");

        int y = list.get((list.size() * 2) / 3).getFoundingYear();
        int idx = BinarySearch.search(list, cmp, "foundingyear", String.valueOf(y));

        assertNotEquals(-1, idx);
        assertEquals(0, cmp.compare(list.get(idx),
                new Publisher.PublisherBuilder().foundingYear(y).build()));
    }

    @Test
    @DisplayName("Издатель: неизвестное поле -> -1")
    void publisher_unknownField_returnsMinusOne() {
        List<Publisher> list = Randomization.getRandomPublishers(10);
        var strategy = new PublisherSortStrategy();
        Comparator<Publisher> cmp = strategy.sort(list, "name");
        assertEquals(-1, BinarySearch.search(list, cmp, "unknown", "v"));
    }

    @Test
    @DisplayName("Издатель: дубликаты по city — любой индекс равного")
    void publisher_duplicatesByCity_anyMatchIndex() {
        Publisher p1 = new Publisher.PublisherBuilder().name("N1").city("Rome").foundingYear(1900).build();
        Publisher p2 = new Publisher.PublisherBuilder().name("N2").city("Rome").foundingYear(1950).build();
        Publisher p3 = new Publisher.PublisherBuilder().name("N3").city("Rome").foundingYear(2000).build();
        List<Publisher> list = new ArrayList<>(List.of(p1, p2, p3));

        var strategy = new PublisherSortStrategy();
        Comparator<Publisher> cmp = strategy.sort(list, "city");

        int idx = BinarySearch.search(list, cmp, "city", "Rome");
        assertNotEquals(-1, idx);
        assertEquals(0, cmp.compare(list.get(idx),
                new Publisher.PublisherBuilder().city("Rome").build()));
    }
}
