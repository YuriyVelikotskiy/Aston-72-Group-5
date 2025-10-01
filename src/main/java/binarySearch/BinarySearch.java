package binarySearch;

import java.util.Comparator;
import java.util.List;

public class BinarySearch {
    public static <T extends Comparable<? super T>> int search(List<T> list, T x) {
        return search(list, x, T::compareTo);
    }

    public static <T> int search(List<T> list, T element, Comparator<? super T> comparator) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (comparator.compare(list.get(mid), element) == 0) {
                return mid;
            }

            if (comparator.compare(list.get(mid), element) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}