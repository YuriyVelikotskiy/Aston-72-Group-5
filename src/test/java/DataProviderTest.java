import classBuilder.Author;
import classBuilder.Book;
import classBuilder.CashedClass;
import dataProvider.DataProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataProviderTest {
    static DataProvider dataProvider;
    static Author authorFirst;
    static Author authorSecond;
    static ArrayList<CashedClass> arrayList;
    static ArrayList<CashedClass> notValidArrayList;
    static Book bookFirst;

    @BeforeAll
    private static void init() {
        dataProvider = DataProvider.getInstance();
        authorFirst = new Author.AuthorBuilder().name("AB Filatof").birthAYear(1997).country("Russia").build();
        authorSecond = new Author.AuthorBuilder().build();
        bookFirst = new Book.BookBuilder().build();
        arrayList = new ArrayList<>(List.of(authorFirst, authorSecond));
        notValidArrayList = new ArrayList<>(List.of(authorFirst, bookFirst));
    }

    @AfterEach
    private void cleanUpEach(){
        dataProvider.clear();
    }

    @Test
    @DisplayName("Тест иммутабельности DataProvider")
    void dataProviderShouldBeSame() {
        DataProvider newDataProvider = DataProvider.getInstance();
        assertSame(newDataProvider, dataProvider);
    }

    @Test
    @DisplayName("Тест вызова getData")
    void inPutOutPutShouldBeSame() {
        dataProvider.addAll(arrayList);
        assertEquals(dataProvider.getData(), arrayList);
    }

    @Test
    @DisplayName("Тест вызова isEmpty")
    void afterAddShouldNotBeEmpty() {
        assertTrue(dataProvider.isEmpty());
        dataProvider.add(authorFirst);
        assertFalse(dataProvider.isEmpty());
    }

    @Test
    @DisplayName("Тест вызова getClazz")
    void afterAddShouldReturnClass() {
        assertNull(dataProvider.getClazz());
        dataProvider.add(authorFirst);
        assertEquals(dataProvider.getClazz(), authorFirst.getClass());
    }

    @Test
    @DisplayName("Тест вызова clear")
    void afterAddShouldBeEmpty() {
        dataProvider.add(authorFirst);
        assertFalse(dataProvider.isEmpty());
        dataProvider.clear();
        assertTrue(dataProvider.isEmpty());
    }

    @Test
    @DisplayName("Тест вызова add")
    void afterAddShouldBeException() {
        dataProvider.add(authorFirst);
        assertThrows(IllegalArgumentException.class,() -> dataProvider.add(bookFirst));
    }

    @Test
    @DisplayName("Тест вызова addAll")
    void afterAddAllShouldBeException() {
        dataProvider.add(bookFirst);
        assertThrows(IllegalArgumentException.class,() -> dataProvider.addAll(arrayList));
        assertThrows(IllegalArgumentException.class,() -> dataProvider.addAll(notValidArrayList));
    }
}