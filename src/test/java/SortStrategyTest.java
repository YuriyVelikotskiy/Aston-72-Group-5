import classBuilder.Author;
import classBuilder.Book;
import classBuilder.Publisher;
import classBuilder.Randomization;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sortstrategybyfields.AuthorFieldSortStrategy;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortStrategyTest {

//    List<Book> books = Randomization.getRandomBooks(10);
//    List<Author> authors = Randomization.getRandomAuthors(10);
//    List<Publisher> publishers = Randomization.getRandomPublishers(10);

    @Test
    @DisplayName("Quick Test")
    void test() {
        Author a1 = new Author.AuthorBuilder().name("B").country(null).birthAYear(1990).build();
        Author a2 = new Author.AuthorBuilder().name("A").country("DE").birthAYear(2000).build();
        Author a3 = new Author.AuthorBuilder().name("C").country("BE").birthAYear(1980).build();
        List<Author> list = Arrays.asList( a1, a2, a3 );
        new AuthorFieldSortStrategy().sort(list, "country", true);
        assertEquals(Arrays.asList(a3, a2, a1), list);
    }
}
