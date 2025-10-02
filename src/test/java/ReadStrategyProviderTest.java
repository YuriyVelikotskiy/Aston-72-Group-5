import org.junit.jupiter.api.Test;
import readStrategy.ReadStrategyProvider;
import static org.junit.jupiter.api.Assertions.*;

class ReadStrategyProviderTest {
    ReadStrategyProvider readStrategyProvider = new ReadStrategyProvider();
    @Test
    void strategyProviderShouldReadFromFile() {
        readStrategyProvider.strategy(1);
    }
    @Test
    void strategyProviderShouldReturnRandom() {
        readStrategyProvider.strategy(2);
    }
    @Test
    void strategyProviderShouldReadFromConsole(){
        readStrategyProvider.strategy(3);
    }

    @Test
    void strategyShouldThrowException(){
        assertThrows(IllegalArgumentException.class,() ->readStrategyProvider.strategy(4));
    }
}