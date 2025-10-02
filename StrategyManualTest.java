package sortstrategy;

import classBuilder.Author;
import classBuilder.Book;
import classBuilder.Publisher;

import java.util.ArrayList;
import java.util.List;

public class StrategyManualTest {
    public static void main(String[] args) {
        sortAuthorsDemo();
        sortBooksDemo();
        // Если есть PublisherSortStrategy — раскомментируй:
        // sortPublishersDemo();
    }

    private static void sortAuthorsDemo() {
        List<Author> list = new ArrayList<>();
        list.add(new Author.AuthorBuilder().name("Zed").country("US").birthAYear(1999).build());
        list.add(new Author.AuthorBuilder().name("Ann").country("RU").birthAYear(1994).build());
        list.add(new Author.AuthorBuilder().name("Bob").country("DE").birthAYear(1980).build());

        SortContext<Author> ctx = new SortContext<>(
                new AuthorSortStrategy(),
                Comparators.forAuthor(FieldSelector.BIRTH_YEAR) // ← выбираем поле
        );

        System.out.println("Authors BEFORE:");
        list.forEach(a -> System.out.println(a.getBirthAYear() + " " + a.getFullName()));

        ctx.sort(list);

        System.out.println("Authors AFTER (by BIRTH_YEAR):");
        list.forEach(a -> System.out.println(a.getBirthAYear() + " " + a.getFullName()));
        System.out.println();
    }

    private static void sortBooksDemo() {
        List<Book> list = new ArrayList<>();
        list.add(new Book.BookBuilder().tile("Gamma").genre("SciFi").yearPublished(2010).build());
        list.add(new Book.BookBuilder().tile("Alpha").genre("Drama").yearPublished(2001).build());
        list.add(new Book.BookBuilder().tile("Beta").genre("Tech").yearPublished(2005).build());

        SortContext<Book> ctx = new SortContext<>(
                new BookSortStrategy(),
                Comparators.forBook(FieldSelector.TITLE) // ← сортируем по названию (getTile)
        );

        System.out.println("Books BEFORE:");
        list.forEach(b -> System.out.println(b.getYearPublished() + " " + b.getTile()));

        ctx.sort(list);

        System.out.println("Books AFTER (by TITLE):");
        list.forEach(b -> System.out.println(b.getYearPublished() + " " + b.getTile()));
        System.out.println();
    }

    @SuppressWarnings("unused")
    private static void sortPublishersDemo() {
        List<Publisher> list = new ArrayList<>();
        list.add(new Publisher.PublisherBuilder().name("Zebra Pub").city("Berlin").foundingYear(1990).build());
        list.add(new Publisher.PublisherBuilder().name("Alpha Pub").city("Moscow").foundingYear(1980).build());
        list.add(new Publisher.PublisherBuilder().name("Beta Pub").city("Paris").foundingYear(2000).build());

        SortContext<Publisher> ctx = new SortContext<>(
                new PublisherSortStrategy(),
                Comparators.forPublisher(FieldSelector.NAME)
        );
        ctx.sort(list);

        System.out.println("Publishers AFTER (by NAME):");
        list.forEach(p -> System.out.println(p.getName() + " " + p.getCity() + " " + p.getFoundingYear()));
        System.out.println();
    }
}
