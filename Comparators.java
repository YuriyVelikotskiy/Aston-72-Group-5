package sortstrategy;

import classBuilder.Author;
import classBuilder.Book;
import classBuilder.Publisher;

import java.util.Comparator;

public final class Comparators {
    private Comparators() {}

    private static final Comparator<String> STR = Comparator.nullsLast(String::compareToIgnoreCase);

    /** Компараторы для Author по полям FieldSelector. */
    public static Comparator<Author> forAuthor(FieldSelector f) {
        switch (f) {
            case FULL_NAME:   return Comparator.comparing(Author::getFullName, STR);
            case COUNTRY:     return Comparator.comparing(Author::getCountry,   STR);
            case BIRTH_YEAR:  return Comparator.comparingInt(Author::getBirthAYear);
            default:
                throw new IllegalArgumentException("Unsupported Author field: " + f);
        }
    }

    /** Компараторы для Book по полям FieldSelector.  ВНИМАНИЕ: у вас getter называется getTile(). */
    public static Comparator<Book> forBook(FieldSelector f) {
        switch (f) {
            case TITLE:          return Comparator.comparing(Book::getTile, STR);
            case GENRE:          return Comparator.comparing(Book::getGenre, STR);
            case YEAR_PUBLISHED: return Comparator.comparingInt(Book::getYearPublished);
            default:
                throw new IllegalArgumentException("Unsupported Book field: " + f);
        }
    }

    /** Компараторы для Publisher по полям FieldSelector. */
    public static Comparator<Publisher> forPublisher(FieldSelector f) {
        switch (f) {
            case NAME:          return Comparator.comparing(Publisher::getName, STR);
            case CITY:          return Comparator.comparing(Publisher::getCity, STR);
            case FOUNDING_YEAR: return Comparator.comparingInt(Publisher::getFoundingYear);
            default:
                throw new IllegalArgumentException("Unsupported Publisher field: " + f);
        }
    }
}
