package readStrategy;

import java.util.HashMap;
import java.util.Map;

public class ReadStrategyProvider {
    private final Map<Integer,ReadStrategy> mapOfStrategy;

    public ReadStrategyProvider(){
        mapOfStrategy = new HashMap<>();
        mapOfStrategy.put(1,new FileReadStrategy());
        mapOfStrategy.put(2,new RandomStrategy());
        mapOfStrategy.put(3,new ConsoleReadStrategy());
    }

    public void strategy(int answerNumber) {
        mapOfStrategy.get(answerNumber).read();
    }
}
