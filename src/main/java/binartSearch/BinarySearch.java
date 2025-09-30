package binartSearch;

import java.util.Comparator;
import java.util.List;

public class BinarySearch {
    public static <T extends Comparable<? super T>> int search(List<T> xs, T x) {
        return search(xs, x, T::compareTo);
    }

    public static <T> int search(List<T> arr, T element, Comparator<? super T> comparator) {
        int left = 0;
        int right = arr.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (comparator.compare(arr.get(mid), element) == 0) {
                return mid;
            }

            if (comparator.compare(arr.get(mid), element) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}