package qsort;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class QuickSort {

    /// Конструктор первого вызова
    public static <T> void quickSort(List<T> list, Comparator<? super T> cmp) {
        if (list.size() < 2) return;

        quickSortTwoThreads(list, cmp);
    }

    /// Конструктор который распараллеливает сортировку на два потока
    private static <T> void quickSortTwoThreads(List<T> list, Comparator<? super T> cmp) {

        int low = 0, high = list.size() - 1;
        int p = partition(list, low, high, cmp);

        ExecutorService pool = Executors.newFixedThreadPool(2);
        try  {
            Future<?> f1 = (p - 1 >= low)
                    ? pool.submit(() -> quickSort(list, low, p - 1, cmp))
                    : null;
            Future<?> f2 = (p + 1 <= high)
                    ? pool.submit(() -> quickSort(list, p + 1, high, cmp))
                    : null;
            if (f1 != null) f1.get();
            if(f2 != null) f2.get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getCause());
        } finally {
            pool.shutdown();
        }
    }

    /// Конструктор для рекурсии
    private static <T> void quickSort(List<T> a, int low, int high, Comparator<? super T> cmp) {
        if (low >= high) return;
        int p = partition(a, low, high, cmp);
        quickSort(a, low, p - 1, cmp);        // левая часть
        quickSort(a, p + 1, high, cmp);        // правая часть
    }

    private static <T> int partition(List<T> a, int low, int high, Comparator<? super T> cmp) {
        moveMedianOfThreeToHigh(a, low, high, cmp);
        T pivot = a.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (cmp.compare(a.get(j), pivot) <= 0) {
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i + 1, high);
        return i + 1;
    }

    /// следование паттерну DRY
    private static <T> void swap(List<T> a, int i, int j) {
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    /* Helper
    private static <T> int cmp(T x, T y, Comparator<? super T> cmp) {
        return cmp.compare(x, y);
    }*/

    /// Логика для избежания худшего случая O(n^2)
    /// Выбираем медианный элемент, и переносим его в high. Алгоритм Lomuto ожидает опорную точку(pivot) в переменной high
    private static <T> void moveMedianOfThreeToHigh(List<T> a, int low, int high, Comparator<? super T> cmp) {
        int mid = low + (high - low) / 2;

        if (cmp.compare(a.get(low), a.get(mid)) > 0) swap(a, low, mid);
        if (cmp.compare(a.get(mid), a.get(high)) > 0) swap(a, mid, high);
        if (cmp.compare(a.get(low), a.get(mid)) > 0) swap(a, low, mid);

        swap(a, mid, high);
    }

}
