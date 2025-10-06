package binarySearch;

import classBuilder.Author;
import classBuilder.Book;
import classBuilder.CashedClass;
import classBuilder.Publisher;

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

    @SuppressWarnings("unchecked, rawtypes")
    public static int search(List<? extends CashedClass> list,
                                                    Comparator comparator,
                                                    String fieldName,
                                                    String value) {
        fieldName = fieldName.toLowerCase();
        CashedClass copy = list.get(0);
//        Field f = fin
//        Field[] fields = list.get(0).getClass().getDeclaredFields();
//        for (Field field : fields) {
//            if field.getName().equals(fieldName) {
//
//            }
//        }
        if (copy.getClass() == Author.class) {
            copy = switch (fieldName) {
                case "fullname" -> new Author.AuthorBuilder().name(value).build();
                case "country" -> new Author.AuthorBuilder().country(value).build();
                case "birthayear" -> new Author.AuthorBuilder().birthAYear(Integer.parseInt(value)).build();
                default -> null;
            };
        } else if (copy.getClass() == Book.class) {
            copy = switch (fieldName) {
                case "tile" -> new Book.BookBuilder().tile(value).build();
                case "genre" -> new Book.BookBuilder().genre(value).build();
                case "yearpublished" -> new Book.BookBuilder().yearPublished(Integer.parseInt(value)).build();
                default -> null;
            };
        } else if (copy.getClass() == Publisher.class) {
            copy = switch (fieldName) {
                case "city" -> new Publisher.PublisherBuilder().city(value).build();
                case "name" -> new Publisher.PublisherBuilder().name(value).build();
                case "foundingyear" -> new Publisher.PublisherBuilder().foundingYear(Integer.parseInt(value)).build();
                default -> null;
            };
        } else return -1;
        if (copy == null) return -1;
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