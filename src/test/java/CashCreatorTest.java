import cashCreator.CashCreator;

import classBuilder.Author;
import classBuilder.Randomization;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CashCreatorTest {
    CashCreator cashCreator = CashCreator.getInstance();
    ArrayList<Author> testList = Randomization.getRandomAuthors(100);

    @Test
    @DisplayName("синглт тон всегда должен возвращаться один и тот же")
    void shouldAlwaysReturnSameInstance() {
        CashCreator cashTest2 = CashCreator.getInstance();
        assertSame(cashTest2, cashCreator);
    }

    @Test
    @DisplayName("при записи файлов не должно возникать ошибок")
    void shouldNotThrowExceptions() {
        assertDoesNotThrow(() -> cashCreator.start(testList));
    }

}
