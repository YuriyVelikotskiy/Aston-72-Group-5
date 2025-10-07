package fileReader;

import classBuilder.Author;
import classBuilder.Book;
import classBuilder.CashedClass;
import classBuilder.Publisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;


/// API использования
/// readFile(String путь, класс ожидаемого объекта)
/// readFile(Path путь, класс ожидаемого объекта)
/// возвращает список указанных элементов, в случае если невозможно получить выдает исключение IOException

public class FileReader {
    /// Вызов получения данных по строке
    public static <T extends CashedClass> List<T> readFile(String path, Class<T> type) throws IOException {
        return readFile(Paths.get(path), type);
    }

    /// Вызов получения данных по пути
    public static <T extends CashedClass> List<T> readFile(Path path, Class<T> type) throws IOException {
        if (isFileExist(path)) {
            return getDataFromFile(path, type);
        } else {
            throw new IOException("ошибка чтения файла");
        }
    }

    /// Метод чтения для кеша
    public static List<? extends CashedClass> readCash(Path path)throws IOException{
        List<String> fileData = readDataFromFile(path);
        Class<? extends CashedClass> type = getDataType(fileData.get(0));
        return createList(fileData,type);
    }
    /// Метод чтения данных из файла
    private static List<String> readDataFromFile(Path path) throws IOException{
        return Files.readAllLines(path);
    }
    ///Получение данных
    private static <T extends CashedClass> List<T> getDataFromFile(Path path, Class<T> type) throws IOException {
        List<String> fileData = readDataFromFile(path);
        try {
            return createList(fileData, type);
        } catch (RuntimeException e) {
            throw new RuntimeException("Ошибка чтения данных. проверьте правильность записи в файл");
        }

    }
    /// Метод получения типа данных
    private static  <T extends CashedClass> Class<T> getDataType(String elem) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode fieldName = mapper.readTree(elem);
        if (fieldName.has(Author.class.getDeclaredFields()[0].getName())){
            return (Class<T>) Author.class;
        } else if (fieldName.has(Book.class.getDeclaredFields()[0].getName())) {
            return (Class<T>)Book.class;
        }else if (fieldName.has(Publisher.class.getDeclaredFields()[0].getName())) {
            return (Class<T>)Publisher.class;
        }else {
            throw new RuntimeException("Ошибка типизации, проверьте правильность записи в файл");
        }

    }


    /// Создаем списки объектов в зависимости отт переданного класса
    private static <T extends CashedClass> List<T> createList(List<String> fileData, Class<T> type) throws IOException {
        List<T> objList = new LinkedList<>();
        if (type == Author.class) {
            for (String data : fileData) {
                objList.add(type.cast(Author.jsonBuild(data)) );
            }
        } else if (type == Book.class) {
            for (String data : fileData) {
                objList.add(type.cast(Book.jsonBuild(data))) ;
            }

        } else if (type == Publisher.class) {
            for (String data : fileData) {
                objList.add(type.cast(Publisher.jsonBuild(data)) );
            }
        }else {
            throw new IOException("Ошибка типизации, проверьте правильность записи в файл");
        }
        return objList;
    }

    /// Проверка на существование и доступность файла
    private static boolean isFileExist(Path path) {
        return Files.exists(path) &&
                Files.isRegularFile(path) &&
                Files.isReadable(path);
    }

}
