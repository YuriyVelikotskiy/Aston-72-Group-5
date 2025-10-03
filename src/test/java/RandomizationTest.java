import classBuilder.Author;
import classBuilder.Book;
import classBuilder.Publisher;
import classBuilder.Randomization;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RandomizationTest {
    List<Author> listAuthors = Randomization.getRandomAuthors(5);
    List<Publisher> listPublisher = Randomization.getRandomPublishers(5);
    List<Book> listBooks = Randomization.getRandomBooks(5);


    @Test
    @DisplayName("Рандомизатор не должен возвращать null значение")
    void shouldReturnNotNull() {
        assertNotNull(listAuthors);
        assertNotNull(listPublisher);
        assertNotNull(listPublisher);
    }

    @Test
    @DisplayName("Рандомизатор должен возвращать объекты не равные null")
    void shouldReturnNotNullObjects() {
        listAuthors.forEach(Assertions::assertNotNull);
        listPublisher.forEach(Assertions::assertNotNull);
        listBooks.forEach(Assertions::assertNotNull);
    }


    @Test
    @DisplayName("Рандомизатор должен возвращать объекты со всеми заполненными полями")
    void shouldWriteAllField() {

        listAuthors.forEach(author -> {
            assertNotNull(author.getCountry());
            assertNotNull(author.getFullName());
        });


        listPublisher.forEach(publisher -> {
            assertNotNull(publisher.getCity());
            assertNotNull(publisher.getName());
        });


        listBooks.forEach(book -> {
            assertNotNull(book.getGenre());
            assertNotNull(book.getTile());
        });
    }

    @Test
    @DisplayName("Рандомизатор должен возвращать объекты со всеми заполненными полями")
    void shouldCreateDifferentObjects() {
        assertNotEquals(listPublisher.get(1), listPublisher.get(2));
        assertNotEquals(listAuthors.get(1), listAuthors.get(2));
        assertNotEquals(listBooks.get(1), listBooks.get(2));
    }

    @Test
    @DisplayName("при создании объектов не должны возникать исключения")
    void shouldNotThrowExceptions() {
        int size = 10000;
        assertDoesNotThrow(() ->{
            ArrayList<Author> testAuthors= Randomization.getRandomAuthors(size);
            ArrayList<Publisher> testPublisher= Randomization.getRandomPublishers(size);
            ArrayList<Book> testAuthor= Randomization.getRandomBooks(size);
        });
    }
}
