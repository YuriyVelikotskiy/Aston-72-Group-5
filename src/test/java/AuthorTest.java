import classBuilder.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


public class AuthorTest {

    @Test
    @DisplayName("Два одинаковых обьекта должны быть equals")
    void shouldEqualsTwoEqualObj() {
        Author.AuthorBuilder builder = new Author.AuthorBuilder();
        builder.birthAYear(2000).name("Bober").country("Kurwa");

        Author a1 = builder.build();
        Author a2 = builder.build();
        assertTrue(a1.equals(a2));
    }

    @Test
    @DisplayName("Два разных обьекта должны быть не equals")
    void shouldNotEqualsTwoDifferentObj() {
        Author.AuthorBuilder builder = new Author.AuthorBuilder();
        builder.birthAYear(2000).name("Bober").country("Kurwa");
        Author a1 = builder.build();
        builder.birthAYear(1999);
        Author a2 = builder.build();
        assertFalse(a1.equals(a2));
    }

    @Test
    @DisplayName("Два одинаковых обьекта должны иметь одинаковый hashCode")
    void shouldHaveSameHashCode() {
        Author.AuthorBuilder builder = new Author.AuthorBuilder();
        builder.birthAYear(2000).name("Bober").country("Kurwa");

        Author a1 = builder.build();
        Author a2 = builder.build();
        assertTrue(a1.hashCode() == a2.hashCode());
    }

    @Test
    @DisplayName("Два разных обьекта должны иметь разный hashCode")
    void shouldNotHaveSameHashCode() {
        Author.AuthorBuilder builder = new Author.AuthorBuilder();
        builder.birthAYear(2000).name("Bober").country("Kurwa");
        Author a1 = builder.build();
        builder.birthAYear(1999);
        Author a2 = builder.build();
        assertFalse(a1.hashCode() == a2.hashCode());
    }

    @Test
    @DisplayName("Тест compareTo при отсутствии реализованной стратегии")
    void shouldComparingByCountry() {

        Author.AuthorBuilder builder = new Author.AuthorBuilder();
        builder.birthAYear(2000).name("Bober").country("A");

        Author a1 = builder.build();
        builder.country("B");
        Author a2 = builder.build();
        assertEquals(-1, a1.compareTo(a2));
        a2 = builder.country("A").build();
        a1 = builder.country("B").build();
        assertEquals(1, a1.compareTo(a2));
        a1 = builder.country("A").build();
        assertEquals(0, a1.compareTo(a2));
    }

    @Test
    @DisplayName("equals = одинаковый hashCode")
    void equalsImpliesSameHashCode() {
        Author a1 = new Author.AuthorBuilder().birthAYear(2000).name("Bober").country("Kurwa").build();
        Author a2 = new Author.AuthorBuilder().birthAYear(2000).name("Bober").country("Kurwa").build();

        assertEquals(a1, a2);
        assertEquals(a1.hashCode(), a2.hashCode());
    }


    @Test
    @DisplayName("Разный hashCode = объекты не равны (если хэши действительно различны)")
    void differentHashImpliesNotEquals() {
        Author a1 = new Author.AuthorBuilder().birthAYear(1990).name("Alice").country("RU").build();
        Author a2 = new Author.AuthorBuilder().birthAYear(1991).name("Bob").country("US").build();

        assumeTrue(a1.hashCode() != a2.hashCode(), "Редкая коллизия: не удалось получить разные хэши");
        assertNotEquals(a1, a2);
    }


    @Test
    @DisplayName("Проверка рефлексивности equals")
    void equalsReflectivity() {
        Author a1 = new Author.AuthorBuilder().birthAYear(2000).name("Bober").country("Kurwa").build();
        assertEquals(a1, a1);
    }

    @Test
    @DisplayName("Проверка симметричности equals")
    void equalsSymmetric() {
        Author a1 = new Author.AuthorBuilder().birthAYear(2000).name("Bober").country("Kurwa").build();
        Author a2 = new Author.AuthorBuilder().birthAYear(1952).name("Kurwa").country("Bober").build();
        Author a3 = new Author.AuthorBuilder().birthAYear(2000).name("Bober").country("Kurwa").build();
        assertTrue(a1.equals(a2) == a2.equals(a1)); //проверка equals == false
        assertTrue(a1.equals(a3) == a3.equals(a1)); //проверка equals == true
    }
}
