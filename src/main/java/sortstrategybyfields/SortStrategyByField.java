package sortstrategybyfields;

import java.util.List;

public interface SortStrategyByField<T> {

    default void sort(List<T> list, String field, boolean ascending) {}

}
