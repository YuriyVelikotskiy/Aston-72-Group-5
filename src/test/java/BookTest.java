import classBuilder.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class BookTest {

    private Book.BookBuilder baseBuilder() {
        return new Book.BookBuilder()
                .title("The Night in Lisbon")
                .yearPublished(1962)
                .genre("War Novel");
    }

    @Test
    @DisplayName("Одинаковые значения полей equals == true")
    void shouldBeEqualWhenAllFieldsMatch() {
        Book.BookBuilder builder = baseBuilder();
        Book b1 = builder.build();
        Book b2 = builder.build();

        assertEquals(b1, b2);
        assertNotSame(b1, b2);
    }

    @Test
    @DisplayName("Различие хотя бы одного поля equals == false")
    void shouldNotBeEqualWhenAnyFieldDiffers() {
        Book.BookBuilder builder = baseBuilder();
        Book b1 = builder.build();
        builder.yearPublished(1930);
        Book b2 = builder.build();

        assertNotEquals(b1, b2);
    }

    @Test
    @DisplayName("Если equals == true, hashCode одинаковый")
    void shouldHaveSameHashCodeWhenEqual() {
        Book b1 = baseBuilder().build();
        Book b2 = baseBuilder().build();

        assertEquals(b1, b2);
        assertEquals(b1.hashCode(), b2.hashCode());
    }

    @Test
    @DisplayName("Если хэши различны (без коллизий), объекты не равны")
    void shouldNotBeEqualWhenHashesDifferAndTheyActuallyDiffer() {
        Book b1 = new Book.BookBuilder()
                .title("Alpha")
                .yearPublished(1990)
                .genre("Fiction")
                .build();

        Book b2 = new Book.BookBuilder()
                .title("Beta")
                .yearPublished(1991)
                .genre("Nonfiction")
                .build();

        assumeTrue(b1.hashCode() != b2.hashCode()); // проверка отсутствия коллизии
        assertNotEquals(b1, b2);
    }

    @Test
    @DisplayName("Рефлексивность equals")
    void shouldBeReflexive() {
        Book b1 = baseBuilder().build();
        assertEquals(b1, b1);
    }

    @Test
    @DisplayName("Симметричность equals")
    void shouldBeSymmetricForEqualAndNonEqualPairs() {
        Book b1 = baseBuilder().build();
        Book b2 = new Book.BookBuilder()
                .title("Not The Night in Lisbon")
                .yearPublished(1962)
                .genre("Not War Novel")
                .build();
        Book b3 = baseBuilder().build();

        assertNotEquals(b1, b2);
        assertNotEquals(b2, b1);

        assertEquals(b1, b3);
        assertEquals(b3, b1);
    }

    @Test
    @DisplayName("Транзитивность equals")
    void shouldBeTransitiveForEqualTriples() {
        Book b1 = baseBuilder().build();
        Book b2 = baseBuilder().build();
        Book b3 = baseBuilder().build();

        assertEquals(b1, b2);
        assertEquals(b2, b3);
        assertEquals(b1, b3);
    }

    @Test
    @DisplayName("Сравнение с null и другим типом")
    void shouldReturnFalseWhenComparedWithNullOrDifferentType() {
        Book b1 = baseBuilder().build();

        assertNotEquals(null, b1);
        assertNotEquals("not-a-book", b1);
    }

    @Test
    @DisplayName("Консистентность: повторные вызовы equals/hashCode стабильны")
    void shouldRemainConsistentForEqualsAndHashCode() {
        Book b1 = baseBuilder().build();
        Book b2 = baseBuilder().build();

        for (int i = 0; i < 5; i++) {
            assertEquals(b1, b2);
            assertEquals(b1.hashCode(), b2.hashCode());
            assertEquals(b1.hashCode(), b1.hashCode());
        }
    }
}
