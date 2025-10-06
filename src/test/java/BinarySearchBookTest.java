import binarySearch.BinarySearch;
import classBuilder.Book;
import classBuilder.Randomization;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sortstrategy.BookSortStrategy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchBookTest {

    @Test
    @DisplayName("Книга: поиск по yearPublished (ASC) — найдено")
    void book_searchByYearPublished_ASC_found() {
        List<Book> books = Randomization.getRandomBooks(20);

        // сортируем по yearpublished по возрастанию
        var strategy = new BookSortStrategy();
        Comparator<Book> cmpAsc = strategy.sort(books, "yearpublished");

        // берём существующее значение из середины
        int probe = books.get(books.size() / 2).getYearPublished();

        int idx = BinarySearch.search(books, cmpAsc, "yearpublished", String.valueOf(probe));

        assertNotEquals(-1, idx);
        assertEquals(probe, books.get(idx).getYearPublished());
    }

    @Test
    @DisplayName("Книга: поиск по yearPublished (DESC) — найдено")
    void book_searchByYearPublished_DESC_found() {
        List<Book> books = Randomization.getRandomBooks(20);

        // сортируем по yearpublished по убыванию
        var strategy = new BookSortStrategy();
        Comparator<Book> cmpDesc = strategy.sort(books, "yearpublished", false);

        // берём существующее значение из середины
        int probe = books.get(books.size() / 2).getYearPublished();

        int idx = BinarySearch.search(books, cmpDesc, "yearpublished", String.valueOf(probe));

        assertNotEquals(-1, idx);
        assertEquals(probe, books.get(idx).getYearPublished());
    }

    @Test
    @DisplayName("Книга: поиск по yearPublished (ASC) — не найдено (-1)")
    void book_searchByYearPublished_ASC_notFound() {
        List<Book> books = Randomization.getRandomBooks(30);

        var strategy = new BookSortStrategy();
        Comparator<Book> cmpAsc = strategy.sort(books, "yearpublished");

        // подбираем год, которого точно нет в списке (в пределах валидного диапазона билдера)
        Set<Integer> years = books.stream().map(Book::getYearPublished).collect(Collectors.toSet());
        int maxYear = LocalDate.now().getYear();
        int missing = -1;
        for (int y = 0; y <= maxYear; y++) {
            if (!years.contains(y)) { missing = y; break; }
        }
        assertTrue(missing >= 0, "Не удалось подобрать отсутствующий год для теста");

        int idx = BinarySearch.search(books, cmpAsc, "yearpublished", String.valueOf(missing));
        assertEquals(-1, idx);
    }

    @Test
    @DisplayName("Книга: поиск по yearPublished (DESC) — не найдено (-1)")
    void book_searchByYearPublished_DESC_notFound() {
        List<Book> books = Randomization.getRandomBooks(30);

        var strategy = new BookSortStrategy();
        Comparator<Book> cmpDesc = strategy.sort(books, "yearpublished", false);

        // подбираем год, которого нет
        Set<Integer> years = books.stream().map(Book::getYearPublished).collect(Collectors.toSet());
        int maxYear = LocalDate.now().getYear();
        int missing = -1;
        for (int y = 0; y <= maxYear; y++) {
            if (!years.contains(y)) { missing = y; break; }
        }
        assertTrue(missing >= 0, "Не удалось подобрать отсутствующий год для теста");

        int idx = BinarySearch.search(books, cmpDesc, "yearpublished", String.valueOf(missing));
        assertEquals(-1, idx);
    }

    @Test
    @DisplayName("Книга: поиск по tile (ASC) — найдено")
    void book_searchByTile_ASC_found() {
        List<Book> list = Randomization.getRandomBooks(30);
        var strategy = new BookSortStrategy();
        Comparator<Book> cmp = strategy.sort(list, "tile");

        String probe = list.get(list.size() / 2).getTile();
        int idx = BinarySearch.search(list, cmp, "tile", probe);

        assertNotEquals(-1, idx);
        assertEquals(0, cmp.compare(list.get(idx),
                new Book.BookBuilder().tile(probe).build()));
    }

    @Test
    @DisplayName("Книга: поиск по genre (DESC) — найдено")
    void book_searchByGenre_DESC_found() {
        List<Book> list = Randomization.getRandomBooks(30);
        var strategy = new BookSortStrategy();
        Comparator<Book> cmpDesc = strategy.sort(list, "genre", false);

        String probe = list.get(list.size() / 3).getGenre();
        int idx = BinarySearch.search(list, cmpDesc, "genre", probe);

        assertNotEquals(-1, idx);
        assertEquals(0, cmpDesc.compare(list.get(idx),
                new Book.BookBuilder().genre(probe).build()));
    }

    @Test
    @DisplayName("Книга: поиск по yearpublished — найдено")
    void book_searchByYear_found() {
        List<Book> list = Randomization.getRandomBooks(30);
        var strategy = new BookSortStrategy();
        Comparator<Book> cmp = strategy.sort(list, "yearpublished");

        int year = list.get((list.size() * 2) / 3).getYearPublished();
        int idx = BinarySearch.search(list, cmp, "yearpublished", String.valueOf(year));

        assertNotEquals(-1, idx);
        assertEquals(0, cmp.compare(list.get(idx),
                new Book.BookBuilder().yearPublished(year).build()));
    }

    @Test
    @DisplayName("Книга: неизвестное поле -> -1")
    void book_unknownField_returnsMinusOne() {
        List<Book> list = Randomization.getRandomBooks(10);
        var strategy = new BookSortStrategy();
        Comparator<Book> cmp = strategy.sort(list, "tile");
        assertEquals(-1, BinarySearch.search(list, cmp, "unknown", "v"));
    }

    @Test
    @DisplayName("Книга: дубликаты по yearpublished — любой индекс равного")
    void book_duplicatesByYear_anyMatchIndex() {
        Book b1 = new Book.BookBuilder().tile("A").genre("G").yearPublished(2000).build();
        Book b2 = new Book.BookBuilder().tile("B").genre("G").yearPublished(2000).build();
        Book b3 = new Book.BookBuilder().tile("C").genre("G").yearPublished(2000).build();
        List<Book> list = new ArrayList<>(List.of(b1, b2, b3));

        var strategy = new BookSortStrategy();
        Comparator<Book> cmp = strategy.sort(list, "yearpublished");

        int idx = BinarySearch.search(list, cmp, "yearpublished", "2000");
        assertNotEquals(-1, idx);
        assertEquals(0, cmp.compare(list.get(idx),
                new Book.BookBuilder().yearPublished(2000).build()));
    }
}
