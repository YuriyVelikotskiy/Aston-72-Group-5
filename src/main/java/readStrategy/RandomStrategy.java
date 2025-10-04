package readStrategy;

import classBuilder.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class RandomStrategy implements ReadStrategy {
    private final Class<? extends CashedClass> type;

    private final Map<Class<? extends CashedClass>, Callable<List<? extends CashedClass>>> randomizationMap = new HashMap<>();

    public RandomStrategy(Class<? extends CashedClass> type, int listSize) {
        this.type = type;
        randomizationMap.put(Author.class, () -> Randomization.getRandomAuthors(listSize));
        randomizationMap.put(Book.class, () -> Randomization.getRandomBooks(listSize));
        randomizationMap.put(Publisher.class, () -> Randomization.getRandomPublishers(listSize));
    }

    @Override
    public void read() {
        try {
            randomizationMap.get(type).call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
