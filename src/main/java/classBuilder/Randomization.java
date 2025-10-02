package classBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/// Класс создает объекты на основе выборки случайных фрагментов данных из файла.
/// Возвращает списки объектов
/// список не может быть меньше 1 элемента

public class Randomization {
    /// Ссылки на файлы содержащие списки
    private static final String DATAHOLDER = System.getProperty("user.dir") + "\\src\\randomDataHolder\\";
    private static final String TILES = "bookTiles.txt";
    private static final String GENERS = "geners";
    private static final String NAMES = "names";
    private static final String COUNTRY = "country";
    private static final String PUBLISHNAMES = "publishName";
    private static final String CITY = "city";
    /// Класс рандомизации
    private static final Random RANDOM = new Random();

    private static final List<String> namesList;
    private static final List<String> countryList;
    private static final List<String> cityList;
    private static final List<String> genersList;
    private static final List<String> titleList;
    private static final List<String> publishNameList;

    static {
        Path dataPathNames = Paths.get(DATAHOLDER + NAMES);
        Path dataPathCountry = Paths.get(DATAHOLDER + COUNTRY);
        Path dataPathCity = Paths.get(DATAHOLDER + CITY);
        Path dataPathGeners = Paths.get(DATAHOLDER + GENERS);
        Path dataPathTitle = Paths.get(DATAHOLDER + TILES);
        Path dataPathPublisher = Paths.get(DATAHOLDER + PUBLISHNAMES);
        try {
            namesList = Files.readAllLines(dataPathNames);
            countryList = Files.readAllLines(dataPathCountry);
            cityList = Files.readAllLines(dataPathCity);
            genersList = Files.readAllLines(dataPathGeners);
            titleList = Files.readAllLines(dataPathTitle);
            publishNameList = Files.readAllLines(dataPathPublisher);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /// Получение случайного числа в диапазоне
    private static int randomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    /// Получение случайной даты
    private static int getRandomDate() {
        return randomInt(0, LocalDate.now().getYear());
    }

    /// Метод получения случайной строки по названию файла с данными
    private static String getRandomString(List<String> list) {
        return list.get(RANDOM.nextInt(list.size()));
    }

    /// Получение случайного Автора
    public static ArrayList<Author> getRandomAuthors(int listSize) {
        if (listSize > 0) {
            ArrayList<Author> randomList = new ArrayList<>();
            Author.AuthorBuilder builder = new Author.AuthorBuilder();
            for (int i = 0; i < listSize; i++) {
                randomList.add(builder.name(getRandomString(namesList))
                        .country(getRandomString(countryList))
                        .birthAYear(getRandomDate()).build());
            }
            return randomList;
        } else {
            throw new IllegalArgumentException("колчиество эллементов не может быть меньше 1");
        }

    }

    /// Получение случайного Издателя
    public static ArrayList<Publisher> getRandomPublishers(int listSize) {
        if (listSize > 0) {
            ArrayList<Publisher> randomList = new ArrayList<>();
            Publisher.PublisherBuilder builder = new Publisher.PublisherBuilder();
            for (int i = 0; i < listSize; i++) {
                randomList.add(builder.name(getRandomString(publishNameList))
                        .city(getRandomString(cityList))
                        .foundingYear(getRandomDate()).build());
            }
            return randomList;
        } else {
            throw new IllegalArgumentException("колчиество эллементов не может быть меньше 1");
        }

    }

    /// Получение случайной книги
    public static ArrayList<Book> getRandomBooks(int listSize) {
        if (listSize > 0) {
            ArrayList<Book> randomList = new ArrayList<>();
            Book.BookBuilder builder = new Book.BookBuilder();
            for (int i = 0; i < listSize; i++) {
                randomList.add(builder.tile(getRandomString(titleList))
                        .genre(getRandomString(genersList))
                        .yearPublished(getRandomDate()).build());
            }
            return randomList;
        } else {
            throw new IllegalArgumentException("колчиество эллементов не может быть меньше 1");
        }

    }
}
