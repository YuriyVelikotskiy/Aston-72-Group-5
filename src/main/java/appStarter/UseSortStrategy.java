package appStarter;

import classBuilder.Author;
import classBuilder.Book;
import classBuilder.CashedClass;
import classBuilder.Publisher;
import sortstrategy.AuthorSortStrategy;
import sortstrategy.BookSortStrategy;
import sortstrategy.PublisherSortStrategy;

import java.util.Comparator;
import java.util.List;

@SuppressWarnings("unchecked")
public class UseSortStrategy {

    public static<T extends CashedClass> Comparator<T> useSortStrategy(List<? extends CashedClass> list,
                                                                       Class<? extends CashedClass> type,
                                                                       String field, boolean ascending) {
        Comparator<T> cmp = null;
        if (type == Author.class) {
            List<Author> authors = (List<Author>) list;
            cmp = (Comparator<T>) new AuthorSortStrategy().sort(authors, field, ascending);
        } else if (type == Book.class) {
            List<Book> books = (List<Book>) list;
            cmp = (Comparator<T>) new BookSortStrategy().sort(books, field, ascending);
        } else if (type == Publisher.class) {
            List<Publisher> publishers = (List<Publisher>) list;
            cmp = (Comparator<T>) new PublisherSortStrategy().sort(publishers, field, ascending);
        }
        return cmp;
    }
    
    public static void sortOnlyEvenValues(List<? extends CashedClass> list,
                                                                  Class<? extends CashedClass> type) {
        if (type == Author.class)
            new AuthorSortStrategy().sortEvenNatural((List<Author>) list, "birthAYear");
        if (type == Book.class)
            new BookSortStrategy().sortEvenNatural((List<Book>) list,"yearPublished");
        if (type == Publisher.class)
            new PublisherSortStrategy().sortEvenNatural((List<Publisher>) list, "foundingYear");
    }

}
