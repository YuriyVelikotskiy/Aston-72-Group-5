import classBuilder.Publisher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class PublisherTest {

    private Publisher.PublisherBuilder baseBuilder() {
        return new Publisher.PublisherBuilder()
                .name("Kippenhan und Witschel")
                .city("Berlin")
                .foundingYear(1877);
    }

    @Test
    @DisplayName("Одинаковые значения полей equals == true")
    void shouldBeEqualWhenAllFieldsMatch() {
        Publisher.PublisherBuilder builder = baseBuilder();
        Publisher p1 = builder.build();
        Publisher p2 = builder.build();

        assertEquals(p1, p2);
        assertNotSame(p1, p2);
    }

    @Test
    @DisplayName("Различие хотя бы одного поля equals == false")
    void shouldNotBeEqualWhenAnyFieldDiffers() {
        Publisher.PublisherBuilder builder = baseBuilder();
        Publisher p1 = builder.build();
        builder.city("Hamburg");
        Publisher p2 = builder.build();

        assertNotEquals(p1, p2);
    }

    @Test
    @DisplayName("Если equals == true, hashCode одинаковый")
    void shouldHaveSameHashCodeWhenEqual() {
        Publisher p1 = baseBuilder().build();
        Publisher p2 = baseBuilder().build();

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    @DisplayName("Если хэши различны (без коллизий), объекты не равны")
    void shouldNotBeEqualWhenHashesDifferAndTheyActuallyDiffer() {
        Publisher p1 = new Publisher.PublisherBuilder()
                .name("Alpha Press")
                .city("Moscow")
                .foundingYear(1990)
                .build();

        Publisher p2 = new Publisher.PublisherBuilder()
                .name("Beta Books")
                .city("New York")
                .foundingYear(1991)
                .build();

        assumeTrue(p1.hashCode() != p2.hashCode()); // проверяем, что коллизии нет
        assertNotEquals(p1, p2);
    }

    @Test
    @DisplayName("Рефлексивность equals")
    void shouldBeReflexive() {
        Publisher p1 = baseBuilder().build();
        assertEquals(p1, p1);
    }

    @Test
    @DisplayName("Симметричность equals")
    void shouldBeSymmetricForEqualAndNonEqualPairs() {
        Publisher p1 = baseBuilder().build();
        Publisher p2 = new Publisher.PublisherBuilder()
                .name("Different Publisher")
                .city("Different City")
                .foundingYear(Math.max(1, LocalDate.now().getYear() - 1))
                .build();
        Publisher p3 = baseBuilder().build();

        assertNotEquals(p1, p2);
        assertNotEquals(p2, p1);

        assertEquals(p1, p3);
        assertEquals(p3, p1);
    }

    @Test
    @DisplayName("Транзитивность equals")
    void shouldBeTransitiveForEqualTriples() {
        Publisher p1 = baseBuilder().build();
        Publisher p2 = baseBuilder().build();
        Publisher p3 = baseBuilder().build();

        assertEquals(p1, p2);
        assertEquals(p2, p3);
        assertEquals(p1, p3);
    }

    @Test
    @DisplayName("Сравнение с null и другим типом")
    void shouldReturnFalseWhenComparedWithNullOrDifferentType() {
        Publisher p1 = baseBuilder().build();

        assertNotEquals(null, p1);
        assertNotEquals("not-a-publisher", p1);
    }

    @Test
    @DisplayName("Консистентность: повторные вызовы equals/hashCode стабильны")
    void shouldRemainConsistentForEqualsAndHashCode() {
        Publisher p1 = baseBuilder().build();
        Publisher p2 = baseBuilder().build();

        for (int i = 0; i < 5; i++) {
            assertEquals(p1, p2);
            assertEquals(p1.hashCode(), p2.hashCode());
            assertEquals(p1.hashCode(), p1.hashCode());
        }
    }
}
