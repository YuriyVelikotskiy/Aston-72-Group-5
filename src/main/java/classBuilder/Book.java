package classBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.Objects;

/// Класс книги содержащий заголовок, год публикации и жанр.
/// Реализован Builder.
/// Валидация на длину жанра и на проверку года.
/// По умолчанию поле жанра null, год ставится текущий
public class Book extends CashedClass{
    private final String title;
    private final int yearPublished;
    private final String genre;

    private Book(BookBuilder builder) {
        this.title = builder.title;
        this.yearPublished = builder.yearPublished;
        this.genre = builder.genre;
    }

    //гетеры
    public String getTitle() {
        return title;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", yearPublished=" + yearPublished +
                ", genre='" + genre + '\'' +
                '}';
    }

    public static Book jsonBuild(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Book.BookBuilder builder = mapper.readValue(json, Book.BookBuilder.class);
        return builder.build();
    }
    

    //билдер
    public static class BookBuilder {
        private String title;
        private int yearPublished;
        private String genre;

        public BookBuilder() {
            this.yearPublished = LocalDate.now().getYear();
        }

        //ввод значений с валидацией
        @JsonProperty("yearPublished")
        public BookBuilder yearPublished(int yearPublished) {
            if (yearPublished > LocalDate.now().getYear()) {
                throw new IllegalArgumentException("некорректная дата выхода книги");
            } else {
                this.yearPublished = yearPublished;
                return this;
            }
        }
        @JsonProperty("genre")
        public BookBuilder genre(String genre) {
            if (genre.length() > 30) {
                throw new IllegalArgumentException("некорректный жанр книги");
            } else {
                this.genre = genre;
                return this;
            }
        }
        @JsonProperty("title")
        public BookBuilder title(String title){
            this.title = title;
            return this;
        }

        //билдер возвращающий книгу
        public Book build() {
            return new Book(this);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, yearPublished, genre);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(
                title, book.getTitle()) &&
                Objects.equals(yearPublished, book.getYearPublished()) &&
                Objects.equals(genre, book.getGenre());
    }
}
