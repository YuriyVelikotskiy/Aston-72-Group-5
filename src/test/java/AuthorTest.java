import classBuilder.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorTest {

    @Test
    @DisplayName("two identical objects must be equal")
    void shouldEqualsTwoEqualObj() {
        Author.AuthorBuilder builder = new Author.AuthorBuilder();
        builder.birthAYear(2000).name("Bober").country("Kurwa");

        Author a1 = builder.build();
        Author a2 = builder.build();
        assertTrue(a1.equals(a2));
    }

    @Test
    @DisplayName("two different objects must not be equal")
    void shouldNotEqualsTwoDifferentObj() {
        Author.AuthorBuilder builder = new Author.AuthorBuilder();
        builder.birthAYear(2000).name("Bober").country("Kurwa");
        Author a1 = builder.build();
        builder.birthAYear(1999);
        Author a2 = builder.build();
        assertFalse(a1.equals(a2));
    }

    @Test
    @DisplayName("two identical objects must have the same hash")
    void shouldHaveSameHashCode() {
        Author.AuthorBuilder builder = new Author.AuthorBuilder();
        builder.birthAYear(2000).name("Bober").country("Kurwa");

        Author a1 = builder.build();
        Author a2 = builder.build();
        assertTrue(a1.hashCode() == a2.hashCode());
    }

    @Test
    @DisplayName("two different objects must not have the same hash")
    void shouldNotHaveSameHashCode() {
        Author.AuthorBuilder builder = new Author.AuthorBuilder();
        builder.birthAYear(2000).name("Bober").country("Kurwa");
        Author a1 = builder.build();
        builder.birthAYear(1999);
        Author a2 = builder.build();
        assertFalse(a1.hashCode() == a2.hashCode());
    }

    @Test
    @DisplayName("test compareTo without Strategy")
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
