import classBuilder.Book;
import classBuilder.Randomization;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sortstrategy.BookSortStrategy;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookSortStrategyTest {
    List<Book> bookList = Randomization.getRandomBooks(10);
    List<Book> expectedBookList = bookList;

    @Test
    @DisplayName("Тест сортировки книг по названию")
    void booksShouldBeSortedByTitle() {
        new BookSortStrategy().sort(bookList, "title"); // поле getTile
        expectedBookList.sort(Comparator.comparing(
                Book::getTitle,
                Comparator.nullsLast(String::compareToIgnoreCase)
        ));
        assertEquals(expectedBookList, bookList);
    }

    @Test
    @DisplayName("Тест сортировки книг по названию в обратном порядке")
    void booksShouldBeSortedByTitleWithReversedOrder() {
        new BookSortStrategy().sort(bookList, "title", false); // поле getTile
        expectedBookList.sort(Comparator.comparing(
                Book::getTitle,
                Comparator.nullsLast(String::compareToIgnoreCase).reversed()
        ));
        assertEquals(expectedBookList, bookList);
    }

    @Test
    @DisplayName("Тест сортировки книг по году публикации")
    void booksShouldBeSortedByYearPublished() {
        new BookSortStrategy().sort(bookList, "yearPublished");
        expectedBookList.sort(Comparator.comparingInt(Book::getYearPublished));
        assertEquals(expectedBookList, bookList);
    }

    @Test
    @DisplayName("Тест сортировки книг по году публикации в обратном порядке")
    void booksShouldBeSortedByYearPublishedWithReversedOrder() {
        new BookSortStrategy().sort(bookList, "yearPublished", false);
        expectedBookList.sort(Comparator.comparingInt(Book::getYearPublished).reversed());
        assertEquals(expectedBookList, bookList);
    }

    @Test
    @DisplayName("Тест сортировки книг по жанру")
    void booksShouldBeSortedByGenre() {
        new BookSortStrategy().sort(bookList, "genre");
        expectedBookList.sort(Comparator.comparing(
                Book::getGenre,
                Comparator.nullsLast(String::compareToIgnoreCase)
        ));
        assertEquals(expectedBookList, bookList);
    }

    @Test
    @DisplayName("Тест сортировки книг по жанру в обратном порядке")
    void booksShouldBeSortedByGenreWithReversedOrder() {
        new BookSortStrategy().sort(bookList, "genre", false);
        expectedBookList.sort(Comparator.comparing(
                Book::getGenre,
                Comparator.nullsLast(String::compareToIgnoreCase)).reversed());
        assertEquals(expectedBookList, bookList);
    }
}
