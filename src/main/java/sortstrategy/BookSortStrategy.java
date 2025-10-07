package sortstrategy;

import classBuilder.Book;

import java.util.Comparator;
import java.util.Map;
import java.util.function.ToIntFunction;

/// Реализация абстрактного базового класса для объекта Book
/// Переопределяем только Map с компараторами

public final class BookSortStrategy extends BaseSortStrategy<Book> {

    private static final Comparator<Book> BY_GENRE =
            Comparator.comparing(Book::getGenre, Comparator.nullsLast(String::compareTo));
    private static final Comparator<Book> BY_TITLE =
            Comparator.comparing(Book::getTitle, Comparator.nullsLast(String::compareTo));
    private static final Comparator<Book> BY_YEAR =
            Comparator.comparingInt(Book::getYearPublished);

    @Override
    protected Map<String, Comparator<Book>> comparators() {
        return Map.of(
                "genre", BY_GENRE,
                "title", BY_TITLE,
                "yearpublished", BY_YEAR
        );
    }

    @Override
    protected Map<String, ToIntFunction<Book>> intExtractors() {
        return Map.of("yearpublished", Book::getYearPublished);
    }
}