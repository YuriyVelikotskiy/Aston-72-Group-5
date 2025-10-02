package sortstrategy;

import classBuilder.CashedClass;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/** Context — хранит выбранную стратегию и компаратор и делегирует сортировку. */
public class SortContext<T extends CashedClass> {
    private SortStrategy<T> strategy;
    private Comparator<? super T> comparator;

    public SortContext(SortStrategy<T> strategy, Comparator<? super T> comparator) {
        this.strategy   = Objects.requireNonNull(strategy);
        this.comparator = Objects.requireNonNull(comparator);
    }

    public void setStrategy(SortStrategy<T> strategy) { this.strategy = Objects.requireNonNull(strategy); }
    public void setComparator(Comparator<? super T> comparator) { this.comparator = Objects.requireNonNull(comparator); }

    public void sort(List<T> list) { strategy.sort(list, comparator); }
}
