package sortstrategybyfields;

import classBuilder.Publisher;
import qsort.QuickSort;

import java.util.Comparator;
import java.util.Map;

public final class PublisherFieldSortStrategy extends BaseFieldSortStrategy<Publisher> {

    private static final Comparator<Publisher> BY_CITY =
            Comparator.comparing(Publisher::getCity, Comparator.nullsLast(String::compareTo));
    private static final Comparator<Publisher> BY_NAME =
            Comparator.comparing(Publisher::getName, Comparator.nullsLast(String::compareTo));
    private static final Comparator<Publisher> BY_YEAR =
            Comparator.comparingInt(Publisher::getFoundingYear);

    @Override
    protected Map<String, Comparator<Publisher>> comparators() {
        return Map.of(
                "country", BY_CITY,
                "name", BY_NAME,
                "birthAYear", BY_YEAR
        );
    }
}