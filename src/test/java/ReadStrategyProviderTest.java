import classBuilder.Author;
import org.junit.jupiter.api.Test;
import readStrategy.RandomStrategy;
import readStrategy.ReadStrategy;
import readStrategy.ReadStrategyProvider;
import static org.junit.jupiter.api.Assertions.*;

class ReadStrategyProviderTest {
    ReadStrategyProvider readStrategyProvider;

    @Test
    void strategyProviderShouldReadFromFile() {
        readStrategyProvider = new ReadStrategyProvider(Author.class, 10);
        readStrategyProvider.strategy(3);
    }
    @Test
    void strategyProviderShouldReturnRandom() {

    }
    @Test
    void strategyProviderShouldReadFromConsole(){

    }

    @Test
    void strategyShouldThrowException(){
        readStrategyProvider = new ReadStrategyProvider(Author.class, 10);
        assertThrows(IllegalArgumentException.class,() ->readStrategyProvider.strategy(4));
    }
}