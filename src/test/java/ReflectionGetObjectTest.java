import classBuilder.Author;
import org.junit.jupiter.api.Test;

import static appStarter.ReflectionGetObject.reflectionGetObject;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReflectionGetObjectTest {

    @Test
    public void getObjectTest() throws IllegalAccessException {
        Author author = new Author.AuthorBuilder().country("France").name("Joe").birthAYear(1999).build();
        reflectionGetObject(author, "country", "Jamal");
        Author author1 = new Author.AuthorBuilder().country("Jamal").name("Joe").birthAYear(1999).build();
        assertEquals(author1, author );
    }
}
