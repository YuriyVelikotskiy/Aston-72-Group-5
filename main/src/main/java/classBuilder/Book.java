package classBuilder;

import java.time.LocalDate;

/// Класс книги содержащий заголовок, год публикации и жанр.
/// Реализован Builder, поле заголовка является обязательным.
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
    

    //билдер
    public static class BookBuilder {
        private final String tile;
        private int yearPublished;
        private String genre;

        public BookBuilder(String tile) {
            this.tile = tile;
            this.yearPublished = LocalDate.now().getYear();
        }

        //ввод значений с валидацией
        public BookBuilder yearPublished(int yearPublished) {
            if (yearPublished > LocalDate.now().getYear()) {
                throw new IllegalArgumentException("некорректная дата выхода книги");
            } else {
                this.yearPublished = yearPublished;
                return this;
            }
        }

        public BookBuilder genre(String genre) {
            if (genre.length() > 30) {
                throw new IllegalArgumentException("некорректный жанр книги");
            } else {
                this.genre = genre;
                return this;
            }
        }

        //билдер возвращающий книгу
        public Book build() {
            return new Book(this);
        }
    }
}
