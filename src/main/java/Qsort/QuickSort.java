package Qsort;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class QuickSort {

    public static <T> void quickSort(List<Integer> list, Comparator<? super Integer> cmp) {
        Objects.requireNonNull(cmp);
        if (list == null) throw new NullPointerException("list");
        if (list.size() < 2) return;
        quickSort(list, 0, list.size() - 1, cmp);
    }

    private static <T> void quickSort(List<T> a, int low, int high, Comparator<? super T> cmp) {
        if (low >= high) return;
        int p = partition(a, low, high, cmp);
        quickSort(a, low, p - 1, cmp);        // левая часть
        quickSort(a, p + 1, high, cmp);        // правая часть
    }

    private static <T> int partition(List<T> a, int low, int high, Comparator<? super T> cmp) {
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

    private static <T> void swap(List<T> a, int i, int j) {
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    // Хелпер
    private static <T> int cmp(T x, T y, Comparator<? super T> cmp) {
        return cmp.compare(x, y);
    }

    //test
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add((int) (Math.random() * 100));
        }
        System.out.println(list);
        Comparator<? super Integer> cmp = Integer::compareTo;
        quickSort(list, cmp);
        System.out.println(list);
    }
}
