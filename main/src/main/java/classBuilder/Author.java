package classBuilder;


import java.time.LocalDate;

/// Класс автора содержащий Фио автора, год рождения и страну.
/// Реализован Builder, поле Фио является обязательным.
/// Валидация на возраст автора(не менее 16 лет).
/// По умолчанию поля остаются null

public class Author extends CashedClass{
    private final String fullName;
    private final String country;
    private final int birthAYear;

    //конструктор
    private Author(AuthorBuilder builder) {
        this.fullName = builder.fullName;
        this.country = builder.country;
        this.birthAYear = builder.birthAYear;
    }

    //гетеры
    public int getBirthAYear() {
        return birthAYear;
    }

    public String getCountry() {
        return country;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "fullName='" + fullName + '\'' +
                ", country='" + country + '\'' +
                ", birthAYear=" + birthAYear +
                '}';
    }



    //класс билдер
    public static class AuthorBuilder {
        private final String fullName;
        private String country;
        private int birthAYear;

        public AuthorBuilder(String fullName) {
            this.fullName = fullName;
        }

        public AuthorBuilder country(String country) {
            this.country = country;
            return this;
        }

        public AuthorBuilder birthAYear(int birthAYear) {
            if (birthAYear > LocalDate.now().getYear() - 16) {
                throw new IllegalArgumentException("некорректный год рождения автора");
            } else {
                this.birthAYear = birthAYear;
                return this;
            }

        }

        public Author build() {
            return new Author(this);
        }


    }
}
