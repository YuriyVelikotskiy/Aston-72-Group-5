import classBuilder.Author;
import classBuilder.Randomization;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sortstrategy.AuthorSortStrategy;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorSortStrategyTest {

    List<Author> authorList = Randomization.getRandomAuthors(10);
    List<Author> expectedAuthorList = authorList;

    @Test
    @DisplayName("Тест сортировки авторов по стране")
    void authorsShouldBeSortedByCountry() {
        new AuthorSortStrategy().sort(authorList, "country");
        expectedAuthorList.sort(Comparator.comparing(
                Author::getCountry,
                Comparator.nullsLast(String::compareToIgnoreCase)));
        assertEquals(expectedAuthorList, authorList);
    }

    @Test
    @DisplayName("Тест сортировки авторов по стране в обратном порядке")
    void authorsShouldBeSortedByCountryWithReversedOrder() {
        new AuthorSortStrategy().sort(authorList, "country", false);
        expectedAuthorList.sort(Comparator.comparing(
                Author::getCountry,
                Comparator.nullsLast(String::compareToIgnoreCase)).reversed());

        assertEquals(expectedAuthorList, authorList);
    }

    @Test
    @DisplayName("Тест сортировки авторов по имени")
    void authorsShouldBeSortedByName() {
        new AuthorSortStrategy().sort(authorList, "name");
        expectedAuthorList.sort(Comparator.comparing(
                Author::getFullName,
                Comparator.nullsLast(String::compareToIgnoreCase)
        ));
        assertEquals(expectedAuthorList, authorList);
    }

    @Test
    @DisplayName("Тест сортировки авторов по имени в обратном порядке")
    void authorsShouldBeSortedByNameWithReversedOrder() {
        new AuthorSortStrategy().sort(authorList, "name", false);
        expectedAuthorList.sort(Comparator.comparing(
                Author::getFullName,
                Comparator.nullsLast(String::compareToIgnoreCase).reversed()
        ));
        assertEquals(expectedAuthorList, authorList);
    }

    @Test
    @DisplayName("Тест сортировки авторов по году рождения")
    void authorsShouldBeSortedByBirthAYear() {
        new AuthorSortStrategy().sort(authorList, "birthAYear");
        expectedAuthorList.sort(Comparator.comparingInt(Author::getBirthAYear));
        assertEquals(expectedAuthorList, authorList);
    }

    @Test
    @DisplayName("Тест сортировки авторов по году рождения в обратном порядке")
    void authorsShouldBeSortedByBirthAYearWithReversedOrder() {
        new AuthorSortStrategy().sort(authorList, "birthAYear", false);
        expectedAuthorList.sort(Comparator.comparingInt(Author::getBirthAYear).reversed());
        assertEquals(expectedAuthorList, authorList);
    }
}
