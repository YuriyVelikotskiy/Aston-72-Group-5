package sortstrategybyfields;

import classBuilder.Author;
import qsort.QuickSort;

import java.util.Comparator;
import java.util.Map;

public final class AuthorFieldSortStrategy extends BaseFieldSortStrategy<Author> {

    private static final Comparator<Author> BY_COUNTRY =
            Comparator.comparing(Author::getCountry, Comparator.nullsLast(String::compareTo));
    private static final Comparator<Author> BY_NAME =
            Comparator.comparing(Author::getFullName, Comparator.nullsLast(String::compareTo));
    private static final Comparator<Author> BY_BIRTH_YEAR =
            Comparator.comparingInt(Author::getBirthAYear);

    @Override
    protected Map<String, Comparator<Author>> comparators() {
        return Map.of(
                "country", BY_COUNTRY,
                "name", BY_NAME,
                "birthAYear", BY_BIRTH_YEAR
        );
    }
}