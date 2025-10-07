package classBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManualInput {
    private static final Scanner scanner=new Scanner(System.in);


    public static List<Publisher> processPublisherCreation(int listSize) {
        List<Publisher> publisherList = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            System.out.println("Введите название издательства:");
            String name = scanner.nextLine();

            System.out.println("Введите город:");
            String city = scanner.nextLine();

            System.out.println("Введите год основания:");
            int foundingYear = scanner.nextInt();
            scanner.nextLine(); // очистка буфера
            publisherList.add(createPublisher(name, city, foundingYear));
        }
        return publisherList;
    }

    public static List<Book> processBookCreation(int listSize) {
        List<Book> bookList = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            System.out.println("Введите название книги:");
            String title = scanner.nextLine();

            System.out.println("Введите год публикации:");
            int yearPublished = scanner.nextInt();
            scanner.nextLine(); // очистка буфера

            System.out.println("Введите жанр книги:");
            String genre = scanner.nextLine();
            bookList.add(createBook(title, yearPublished, genre));
        }
        return bookList;
    }

    public static List<Author> processAuthorCreation(int listSize) {
        List<Author> authorList = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            System.out.println("Введите ФИО автора:");
            String fullName = scanner.nextLine();

            System.out.println("Введите страну:");
            String country = scanner.nextLine();

            System.out.println("Введите год рождения:");
            int birthYear = scanner.nextInt();
            scanner.nextLine();

            authorList.add(createAuthor(fullName, country, birthYear));
        }
        return authorList;
    }

    private static Author createAuthor(String fullName, String country, int birthAYear) {
        return new Author.AuthorBuilder().name(fullName).country(country).birthAYear(birthAYear).build();
    }

    private static Book createBook(String title, int yearPublished, String genre) {
        return new Book.BookBuilder().title(title).yearPublished(yearPublished).genre(genre).build();
    }

    private static Publisher createPublisher(String name, String city, int foundingYear) {
        return new Publisher.PublisherBuilder().name(name).city(city).foundingYear(foundingYear).build();
    }
}