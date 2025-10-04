import classBuilder.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class AuthorTest {

    private Author.AuthorBuilder baseBuilder() {
        return new Author.AuthorBuilder()
                .birthAYear(1898)
                .name("Erich Maria Remarque")
                .country("Germany");
    }

    @Test
    @DisplayName("Одинаковые значения полей equals == true")
    void shouldBeEqualWhenAllFieldsMatch() {
        Author.AuthorBuilder builder = baseBuilder();
        Author a1 = builder.build();
        Author a2 = builder.build();

        assertEquals(a1, a2);
        assertNotSame(a1, a2);
    }

    @Test
    @DisplayName("Различие хотя бы одного поля equals == false")
    void shouldNotBeEqualWhenAnyFieldDiffers() {
        Author.AuthorBuilder builder = baseBuilder();
        Author a1 = builder.build();
        builder.birthAYear(1999);
        Author a2 = builder.build();

        assertNotEquals(a1, a2);
    }

    @Test
    @DisplayName("Если equals == true, hashCode одинаковый")
    void shouldHaveSameHashCodeWhenEqual() {
        Author a1 = baseBuilder().build();
        Author a2 = baseBuilder().build();

        assertEquals(a1, a2);
        assertEquals(a1.hashCode(), a2.hashCode());
    }

    @Test
    @DisplayName("Если хэши различны (без коллизий), объекты не равны")
    void shouldNotBeEqualWhenHashesDifferAndTheyActuallyDiffer() {
        Author a1 = new Author.AuthorBuilder().birthAYear(1990).name("Alice").country("RU").build();
        Author a2 = new Author.AuthorBuilder().birthAYear(1991).name("Bob").country("US").build();

        assumeTrue(a1.hashCode() != a2.hashCode()); // проверка, что хэши действительно разные и нет коллизии
        assertNotEquals(a1, a2);
    }

    @Test
    @DisplayName("Рефлексивность equals")
    void shouldBeReflexive() {
        Author a1 = baseBuilder().build();
        assertEquals(a1, a1);
    }

    @Test
    @DisplayName("Симметричность equals")
    void shouldBeSymmetricForEqualAndNonEqualPairs() {
        Author a1 = baseBuilder().build();
        Author a2 = new Author.AuthorBuilder().birthAYear(1952).name("Not Erich").country("Not Germany").build();
        Author a3 = baseBuilder().build();

        assertNotEquals(a1, a2);
        assertNotEquals(a2, a1);

        assertEquals(a1, a3);
        assertEquals(a3, a1);
    }

    @Test
    @DisplayName("Транзитивность equals")
    void shouldBeTransitiveForEqualTriples() {
        Author a1 = baseBuilder().build();
        Author a2 = baseBuilder().build();
        Author a3 = baseBuilder().build();

        assertEquals(a1, a2);
        assertEquals(a2, a3);
        assertEquals(a1, a3);
    }

    @Test
    @DisplayName("Сравнение с null и другим типом")
    void shouldReturnFalseWhenComparedWithNullOrDifferentType() {
        Author a1 = baseBuilder().build();

        assertNotEquals(null, a1);
        assertNotEquals("not-an-author", a1);
    }

    @Test
    @DisplayName("Консистентность: повторные вызовы equals/hashCode стабильны")
    void shouldRemainConsistentForEqualsAndHashCode() {
        Author a1 = baseBuilder().build();
        Author a2 = baseBuilder().build();

        for (int i = 0; i < 5; i++) {
            assertEquals(a1, a2);
            assertEquals(a1.hashCode(), a2.hashCode());
            assertEquals(a1.hashCode(), a1.hashCode());
        }
    }
}
