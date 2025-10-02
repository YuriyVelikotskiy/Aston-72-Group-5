import classBuilder.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}
