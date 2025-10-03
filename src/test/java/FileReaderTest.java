import cashCreator.CashCreator;
import classBuilder.Author;
import classBuilder.Book;
import classBuilder.Publisher;
import classBuilder.Randomization;
import config.Config;
import fileReader.FileReader;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileReaderTest {
    CashCreator cashCreator = CashCreator.getInstance();
    List<Author> testListAuthor = Randomization.getRandomAuthors(20);
    List<Book> testListBook = Randomization.getRandomBooks(20);
    List<Publisher> testListPublisher = Randomization.getRandomPublishers(20);
    Path filePathAuthors = Paths.get(Config.getCASHDIR() + "testCashAuthor");
    Path filePathBooks = Paths.get(Config.getCASHDIR() + "testCashBook");
    Path filePathPublishers = Paths.get(Config.getCASHDIR() + "testCashPublisher");

    @BeforeEach
    void createFile() throws InterruptedException {
        cashCreator.start(testListAuthor,filePathAuthors);
        cashCreator.start(testListBook,filePathBooks);
        cashCreator.start(testListPublisher,filePathPublishers);
        cashCreator.getCashThread().join();
    }

    @Test
    @DisplayName("Чтение осуществляется так же как и запись")
    void shouldCorrectReadAuthors() throws IOException {
        List<Author> authorList = FileReader.readFile(filePathAuthors,Author.class);
        assertEquals(authorList,testListAuthor);
    }

    @Test
    @DisplayName("Чтение осуществляется так же как и запись")
    void shouldCorrectReadBooks() throws IOException {
        List<Book> booksList = FileReader.readFile(filePathBooks, Book.class);
        assertEquals(booksList,testListBook);
    }

    @Test
    @DisplayName("Чтение осуществляется так же как и запись")
    void shouldCorrectReadPublishers() throws IOException {
        List<Publisher> publisherList = FileReader.readFile(filePathPublishers, Publisher.class);
        assertEquals(publisherList,testListPublisher);
    }

    @Test
    @DisplayName("должен возвращать корректные значения")
    void shouldNotNull() throws IOException {
        List<Author> authorList = FileReader.readFile(filePathAuthors,Author.class);
        List<Publisher> publisherList = FileReader.readFile(filePathPublishers, Publisher.class);
        List<Book> booksList = FileReader.readFile(filePathBooks, Book.class);
        assertNotNull(authorList);
        assertNotNull(publisherList);
        assertNotNull(booksList);
        authorList.forEach(Assertions::assertNotNull);
    }

    @AfterEach
    void clearCash(){
        try {
            Files.delete(filePathAuthors);
            Files.delete(filePathBooks);
            Files.delete(filePathPublishers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
