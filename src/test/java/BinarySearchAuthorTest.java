import binarySearch.BinarySearch;
import classBuilder.Author;
import classBuilder.Randomization;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sortstrategy.AuthorSortStrategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchAuthorTest {

    @Test
    @DisplayName("Автор: поиск по fullName в отсортированном списке (ASC)")
    void author_searchByFullName_ASC_found() {
        List<Author> list = Randomization.getRandomAuthors(25);
        var strategy = new AuthorSortStrategy();
        Comparator<Author> cmp = strategy.sort(list, "fullName");

        String probe = list.get(list.size() / 2).getFullName();
        int idx = BinarySearch.search(list, cmp, "fullName", probe);

        assertNotEquals(-1, idx);
        assertEquals(0, cmp.compare(list.get(idx),
                new Author.AuthorBuilder().name(probe).build()));
    }

    @Test
    @DisplayName("Автор: поиск по country в отсортированном списке (DESC) — корректная работа с reversed")
    void author_searchByCountry_DESC_found() {
        List<Author> list = Randomization.getRandomAuthors(25);
        var strategy = new AuthorSortStrategy();
        Comparator<Author> cmpDesc = strategy.sort(list, "country", false);

        String probe = list.get(list.size() / 3).getCountry();
        int idx = BinarySearch.search(list, cmpDesc, "country", probe);

        assertNotEquals(-1, idx);
        assertEquals(0, cmpDesc.compare(list.get(idx),
                new Author.AuthorBuilder().country(probe).build()));
    }

    @Test
    @DisplayName("Автор: поиск по birthAYear — найденный индекс корректен")
    void author_searchByBirthAYear_found() {
        List<Author> list = Randomization.getRandomAuthors(25);
        var strategy = new AuthorSortStrategy();
        Comparator<Author> cmp = strategy.sort(list, "birthAYear");

        int year = list.get((list.size() * 2) / 3).getBirthAYear();
        int idx = BinarySearch.search(list, cmp, "birthAYear", String.valueOf(year));

        assertNotEquals(-1, idx);
        assertEquals(0, cmp.compare(list.get(idx),
                new Author.AuthorBuilder().birthAYear(year).build()));
    }

    @Test
    @DisplayName("Автор: неизвестное поле -> -1")
    void author_unknownField_returnsMinusOne() {
        List<Author> list = Randomization.getRandomAuthors(10);
        var strategy = new AuthorSortStrategy();
        Comparator<Author> cmp = strategy.sort(list, "fullName");
        assertEquals(-1, BinarySearch.search(list, cmp, "unknown", "value"));
    }

    @Test
    @DisplayName("Автор: дубликаты по country — возвращается индекс любого равного (cmp == 0)")
    void author_duplicatesByCountry_anyMatchIndex() {
        Author a1 = new Author.AuthorBuilder().name("A").country("X").birthAYear(1950).build();
        Author a2 = new Author.AuthorBuilder().name("B").country("X").birthAYear(1960).build();
        Author a3 = new Author.AuthorBuilder().name("C").country("X").birthAYear(1970).build();
        List<Author> list = new ArrayList<>(List.of(a1, a2, a3));

        var strategy = new AuthorSortStrategy();
        Comparator<Author> cmp = strategy.sort(list, "country");

        int idx = BinarySearch.search(list, cmp, "country", "X");
        assertNotEquals(-1, idx);
        assertEquals(0, cmp.compare(list.get(idx),
                new Author.AuthorBuilder().country("X").build()));
    }
}
