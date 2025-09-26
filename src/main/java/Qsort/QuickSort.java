package Qsort;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class QuickSort {

    /// Конструктор для первого вызова
    public static <T> void quickSort(List<Integer> list, Comparator<? super Integer> cmp) {
        Objects.requireNonNull(cmp);
        if (list == null) throw new NullPointerException("list");
        if (list.size() < 2) return;
        quickSort(list, 0, list.size() - 1, cmp);
    }

    /// Конструктор для рекурсии
    private static <T> void quickSort(List<T> a, int low, int high, Comparator<? super T> cmp) {
        if (low >= high) return;
        int p = partition(a, low, high, cmp);
        quickSort(a, low, p - 1, cmp);        // левая часть
        quickSort(a, p + 1, high, cmp);        // правая часть
    }

    ///
    private static <T> int partition(List<T> a, int low, int high, Comparator<? super T> cmp) {
        moveMedianOfThreeToHigh(a, low, high, cmp);
        T pivot = a.get(high);
         int i = low - 1;
         for (int j = low; j < high; j++) {
           if (cmp(a.get(j), pivot, cmp) <= 0) {
               i++; swap(a, i, j);
           }
         }
         swap(a, i + 1, high);
         return i + 1;
    }

    /// follow DRY
    private static <T> void swap(List<T> a, int i, int j) {
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    /// Helper
    private static <T> int cmp(T x, T y, Comparator<? super T> cmp) {
        return cmp.compare(x, y);
    }

    /// To avoid the worst case
    private static <T> void moveMedianOfThreeToHigh(List<T> a, int low, int high, Comparator<? super T> cmp) {
        int mid = low + (high - low) / 2;

        if (cmp(a.get(low), a.get(mid), cmp) > 0) swap(a, low, mid);
        if (cmp(a.get(mid), a.get(high), cmp) > 0) swap(a, mid, high);
        if (cmp(a.get(low), a.get(mid), cmp) > 0) swap(a, low, mid);

        swap(a, mid, high);
    }

    /// Test
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add((int) (Math.random() * 100));
        }
        System.out.println(list);
        Comparator<? super Integer> cmp = Integer::compareTo;
        quickSort(list, cmp);
        long endTime = System.currentTimeMillis();
        System.out.println(list);
        System.out.println(endTime - startTime);
    }
}
