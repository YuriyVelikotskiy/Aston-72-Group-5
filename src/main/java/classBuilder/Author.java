package classBuilder;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.Objects;

/// Класс автора содержащий Фио автора, год рождения и страну.
/// Реализован Builder.
/// Валидация на возраст автора(не менее 16 лет).
/// По умолчанию поля остаются null

public class Author extends CashedClass {
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

    public static Author jsonBuild(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        AuthorBuilder builder = mapper.readValue(json, AuthorBuilder.class);
        return builder.build();
    }

    //класс билдер
    public static class AuthorBuilder {
        private String fullName;
        private String country;
        private int birthAYear;


        @JsonProperty("country")
        public AuthorBuilder country(String country) {
            this.country = country;
            return this;
        }

        @JsonProperty("fullName")
        public AuthorBuilder name(String fullName) {
            this.fullName = fullName;
            return this;
        }

        @JsonProperty("birthAYear")
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

    @Override
    public int hashCode() {
        return Objects.hash(fullName, country, birthAYear);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(
                fullName, author.getFullName()) &&
                Objects.equals(country, author.getCountry()) &&
                Objects.equals(birthAYear, author.getBirthAYear());
    }


}
