import classBuilder.Publisher;
import classBuilder.Randomization;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sortstrategy.PublisherSortStrategy;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PublisherSortStrategyTest {
    List<Publisher> publisherList = Randomization.getRandomPublishers(10);
    List<Publisher> expectedPublisherList = publisherList;

    @Test
    @DisplayName("Тест сортировки издателей по названию")
    void publishersShouldBeSortedByName() {
        new PublisherSortStrategy().sort(publisherList, "name");
        expectedPublisherList.sort(Comparator.comparing(
                Publisher::getName,
                Comparator.nullsLast(String::compareToIgnoreCase)
        ));
        assertEquals(expectedPublisherList, publisherList);
    }

    @Test
    @DisplayName("Тест сортировки издателей по названию в обратном порядке")
    void publishersShouldBeSortedByNameWithReversedOrder() {
        new PublisherSortStrategy().sort(publisherList, "name", false);
        expectedPublisherList.sort(Comparator.comparing(
                Publisher::getName,
                Comparator.nullsLast(String::compareToIgnoreCase).reversed()
        ));
        assertEquals(expectedPublisherList, publisherList);
    }

    @Test
    @DisplayName("Тест сортировки издателей по году основания")
    void publishersShouldBeSortedByFoundingYear() {
        new PublisherSortStrategy().sort(publisherList, "foundingYear");
        expectedPublisherList.sort(Comparator.comparingInt(Publisher::getFoundingYear));
        assertEquals(expectedPublisherList, publisherList);
    }

    @Test
    @DisplayName("Тест сортировки издателей по году основания в обратном порядке")
    void publishersShouldBeSortedByFoundingYearWithReversedOrder() {
        new PublisherSortStrategy().sort(publisherList, "foundingYear", false);
        expectedPublisherList.sort(Comparator.comparingInt(Publisher::getFoundingYear).reversed());
        assertEquals(expectedPublisherList, publisherList);
    }

    @Test
    @DisplayName("Тест сортировки издателей по городу")
    void publishersShouldBeSortedByCity() {
        new PublisherSortStrategy().sort(publisherList, "city");
        expectedPublisherList.sort(Comparator.comparing(
                Publisher::getCity,
                Comparator.nullsLast(String::compareToIgnoreCase)
        ));
        assertEquals(expectedPublisherList, publisherList);
    }

    @Test
    @DisplayName("Тест сортировки издателей по городу в обратном порядке")
    void publishersShouldBeSortedByCityWithReversedOrder() {
        new PublisherSortStrategy().sort(publisherList, "city", false);
        expectedPublisherList.sort(Comparator.comparing(
                Publisher::getCity,
                Comparator.nullsLast(String::compareToIgnoreCase)).reversed());
        assertEquals(expectedPublisherList, publisherList);
    }
}
