import classBuilder.Author;
import org.junit.jupiter.api.Test;
import sortstrategy.AuthorSortStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExtraTask1Test {

    List<Author> authors = new ArrayList<>();
    Author a1 = new Author.AuthorBuilder().name("John").country("ET").birthAYear(2002).build();
    Author a2 = new Author.AuthorBuilder().name("Bob").country("HE").birthAYear(1998).build();
    Author a3 = new Author.AuthorBuilder().name("Alice").country("QW").birthAYear(1992).build();
    Author a4 = new Author.AuthorBuilder().name("Josh").country("CH").birthAYear(1993).build();
    Author a5 = new Author.AuthorBuilder().name("Joe").country("HZ").birthAYear(1994).build();
    Author a6 = new Author.AuthorBuilder().name("Hue").country("PO").birthAYear(1995).build();

    @Test
    public void authorsShouldBeSortByEvenYear() {
        authors.add(a1);
        authors.add(a2);
        authors.add(a3);
        authors.add(a4);
        authors.add(a5);
        authors.add(a6);
        new AuthorSortStrategy().sortEvenNatural(authors, "birthAYear");
        System.out.println(authors);
        assertEquals(Arrays.asList(a3, a5, a2, a4, a1, a6), authors);

    }
}
