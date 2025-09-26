package classBuilder;

import java.time.LocalDate;

/// Класс издательства содержащий назваиние издательства, город и год основания.
/// Реализован Builder, поле названия является обязательным.
/// Валидация на год основания(не позже текущего года).
/// По умолчанию поля остаются null


public class Publisher extends CashedClass {
    private final String name;
    private final String city;
    private final int foundingYear;

    //конструктор
    private Publisher(PublisherBuilder builder) {
        this.name = builder.name;
        this.city = builder.city;
        this.foundingYear = builder.foundingYear;
    }

    //геттеры
    public String getName() {
        return name;
    }

    public int getFoundingYear() {
        return foundingYear;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", foundingYear=" + foundingYear +
                '}';
    }

    //класс билдер
    public static class PublisherBuilder {
        private final String name;
        private String city;
        private int foundingYear;

        public PublisherBuilder(String name) {
            this.name = name;
        }

        public PublisherBuilder city(String city) {
            this.city = city;
            return this;
        }


        public PublisherBuilder foundingYear(int foundingYear) {
            if (foundingYear > LocalDate.now().getYear()) {
                throw new IllegalArgumentException("некорректная основания издательства");
            } else {
                this.foundingYear = foundingYear;
                return this;
            }
        }

        public Publisher build() {
            return new Publisher(this);
        }
    }
}
