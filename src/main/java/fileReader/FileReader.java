package fileReader;

import classBuilder.Author;
import classBuilder.Book;
import classBuilder.CashedClass;
import classBuilder.Publisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import config.Config;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class FileReader {

    public static <T extends CashedClass> List<T> readFile(String path, Class<T> type) throws IOException {
        return readFile(Paths.get(path), type);
    }

    public static <T extends CashedClass> List<T> readFile(Path path, Class<T> type) throws IOException {
        if (isFileExist(path)) {
            return getDataFromFile(path, type);
        } else {
            throw new IOException("ошибка чтения файла");
        }
    }

    private static <T extends CashedClass> List<T> getDataFromFile(Path path, Class<T> type) throws IOException {
        List<String> fileData = Files.readAllLines(path);
        try {
            return createList(fileData, type);
        } catch (RuntimeException e) {
            throw new RuntimeException("Ошибка чтения данны. проверьте правильность записи в файл", e);
        }

    }

    private static <T extends CashedClass> List<T> createList(List<String> fileData, Class<T> type) throws JsonProcessingException {
        List<T> objList = new LinkedList<>();
        if (type == Author.class) {
            for (String data : fileData) {
                objList.add((T) Author.jsonBuild(data));
            }
        } else if (type == Book.class) {
            for (String data : fileData) {
                objList.add((T) Book.jsonBuild(data));
            }

        } else if (type == Publisher.class) {
            for (String data : fileData) {
                objList.add((T) Publisher.jsonBuild(data));
            }
        }
        return objList;
    }


    private static boolean isFileExist(Path path) {
        return Files.exists(path) &&
                Files.isRegularFile(path) &&
                Files.isReadable(path);
    }

    public static void main(String[] args) {
        Path path = Config.getCASHPATH();
        try {
            List<Author> test = readFile(path,Author.class);
            test.forEach(x -> System.out.println(x.getFullName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
