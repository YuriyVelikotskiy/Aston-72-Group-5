package readStrategy;

import classBuilder.CashedClass;

public class ConsoleReadStrategy implements ReadStrategy {

    private final int listSize;
    private final Class<? extends CashedClass> type;

    public ConsoleReadStrategy(Class<? extends CashedClass> type, int listSize) {
        this.type = type;
        this.listSize = listSize;
    }

    @Override
    public void read() {
        System.out.println();
    }
}
