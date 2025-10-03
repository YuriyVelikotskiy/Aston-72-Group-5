package sortstrategybyfields;

import classBuilder.Book;
import qsort.QuickSort;

import java.util.Comparator;
import java.util.Map;

public final class BookFieldSortStrategy extends BaseFieldSortStrategy<Book> {

    private static final Comparator<Book> BY_GENRE =
            Comparator.comparing(Book::getGenre, Comparator.nullsLast(String::compareTo));
    private static final Comparator<Book> BY_TILE =
            Comparator.comparing(Book::getTile, Comparator.nullsLast(String::compareTo));
    private static final Comparator<Book> BY_YEAR =
            Comparator.comparingInt(Book::getYearPublished);

    @Override
    protected Map<String, Comparator<Book>> comparators() {
        return Map.of(
                "genre", BY_GENRE,
                "tile", BY_TILE,
                "year", BY_YEAR
        );
    }
}