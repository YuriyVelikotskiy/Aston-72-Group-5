import binarySearch.BinarySearch;
import classBuilder.Author;
import org.junit.jupiter.api.Test;
import sortstrategy.AuthorSortStrategy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortAndSearchTest {
    Author a1 = new Author.AuthorBuilder().name("searched").country("HY").birthAYear(1943).build();
    Comparator cmp;
    List<Author> authors = Arrays.asList(
            new Author.AuthorBuilder().name("Bob").country("USA").birthAYear(1999).build(),
            new Author.AuthorBuilder().name("Alice").country("RU").birthAYear(2000).build(),
            a1,
            new Author.AuthorBuilder().name("Josh").country("AZ").birthAYear(2001).build(),
            new Author.AuthorBuilder().name("Suzanna").country("BA").birthAYear(1998).build(),
            new Author.AuthorBuilder().name("Robbin").country("KE").birthAYear(1995).build()
    );

    @Test
    void shouldSortAndSearchAuthors() {
        cmp = new AuthorSortStrategy().sort(authors, "fullName");
        Author a = authors.get(BinarySearch.search(authors, a1, cmp));
        assertEquals(a1.getFullName(), a.getFullName());
    }
}
