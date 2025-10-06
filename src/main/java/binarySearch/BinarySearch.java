package binarySearch;

import classBuilder.Author;
import classBuilder.Book;
import classBuilder.CashedClass;
import classBuilder.Publisher;

import java.util.Comparator;
import java.util.List;

import static appStarter.ReflectionGetObject.reflectionGetObject;

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

    @SuppressWarnings("unchecked, rawtypes")
    public static int search(List<? extends CashedClass> list,
                                                    Comparator comparator,
                                                    String fieldName,
                                                    String value) {
        CashedClass copy;
        if (list.get(0).getClass() == Author.class) {
            try {
                copy = new Author.AuthorBuilder().build();
                copy = reflectionGetObject(copy, fieldName, value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        } else if (list.get(0).getClass() == Book.class) {
            try {
                copy = new Book.BookBuilder().build();
                copy = reflectionGetObject(copy, fieldName, value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        } else if (list.get(0).getClass() == Publisher.class) {
            try {
                copy = new Publisher.PublisherBuilder().build();
                copy = reflectionGetObject(copy, fieldName, value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        } else return -1;
        if (comparator.compare(list.get(0), list.get(list.size() - 1)) > 0) {
            comparator = comparator.reversed();
        }
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (comparator.compare(list.get(mid), copy) == 0) {
                return mid;
            }
            if (comparator.compare(list.get(mid), copy) < 0) {
                left = mid + 1;
            }  else {
                right = mid - 1;
            }
        }
        return -1;
    }
}