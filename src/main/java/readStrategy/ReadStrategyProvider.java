package readStrategy;

import classBuilder.CashedClass;

import java.util.Map;

public class ReadStrategyProvider {

    private final Map<Integer, ReadStrategy> strategyMap;

    public ReadStrategyProvider(Class<? extends CashedClass> type, int listSize) {
        strategyMap = Map.of(1, new ConsoleReadStrategy(type, listSize),
                2, new FileReadStrategy(type),
                3, new RandomStrategy(type, listSize));
    }

    public void strategy(int answerNumber) {
        if (strategyMap.containsKey(answerNumber)) {
            strategyMap.get(answerNumber).read();
        } else throw new IllegalArgumentException();

    }
}
