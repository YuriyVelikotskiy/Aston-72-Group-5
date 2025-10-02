package sortstrategy;

import classBuilder.CashedClass;

import java.util.Comparator;
import java.util.List;

/**
 * Strategy — общий интерфейс алгоритма сортировки.
 * Универсален: работает с любыми наследниками CashedClass и любым Comparator.
 */
public interface SortStrategy<T extends CashedClass> {

    /**
     * Выполняет сортировку списка на месте, используя переданный компаратор.
     * Никаких готовых Collections.sort — алгоритм пишем в конкретной стратегии.
     */
    void sort(List<T> list, Comparator<? super T> comparator);

    /**
     * Для удобства логирования/меню.
     */
    default String name() {
        return getClass().getSimpleName();
    }
}