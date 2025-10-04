package sortstrategy;

import classBuilder.Book;

import java.util.Comparator;
import java.util.Map;

    /// Реализация абстрактного базового класса для объекта Book
    /// Переопределяем только Map с компараторами

public final class BookSortStrategy extends BaseSortStrategy<Book> {

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
                "yearpublished", BY_YEAR
        );
    }
}