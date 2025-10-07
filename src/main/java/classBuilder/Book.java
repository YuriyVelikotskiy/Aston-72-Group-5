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
    private final String tile;
    private final int yearPublished;
    private final String genre;

    private Book(BookBuilder builder) {
        this.tile = builder.tile;
        this.yearPublished = builder.yearPublished;
        this.genre = builder.genre;
    }

    //гетеры
    public String getTile() {
        return tile;
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
                "tile='" + tile + '\'' +
                ", yearPublished=" + yearPublished +
                ", genre='" + genre + '\'' +
                '}';
    }

    public static Book jsonBuild(String json){
        try {
            ObjectMapper mapper = new ObjectMapper();
            Book.BookBuilder builder = mapper.readValue(json, Book.BookBuilder.class);
            return builder.build();
        }catch (JsonProcessingException e){
            throw new RuntimeException("ошибка сборки данных, проверьте правильность данных");
        }
    }
    

    //билдер
    public static class BookBuilder {
        private String tile;
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
        @JsonProperty("tile")
        public BookBuilder tile(String tile){
            this.tile = tile;
            return this;
        }

        //билдер возвращающий книгу
        public Book build() {
            return new Book(this);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(tile, yearPublished, genre);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(
                tile, book.getTile()) &&
                Objects.equals(yearPublished, book.getYearPublished()) &&
                Objects.equals(genre, book.getGenre());
    }
}
