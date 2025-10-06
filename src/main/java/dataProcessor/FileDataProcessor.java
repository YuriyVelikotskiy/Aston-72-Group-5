package dataProcessor;

import config.Config;
import fileReader.FileReader;

import java.io.IOException;


public class FileDataProcessor extends DataProcessor {

    public FileDataProcessor(DataProcessor next) {
        super(next);
    }

    //Метод пытается прочитать файл, в случае успеха возвращает true и пишет в консоль об успешном чтении
    @Override
    public boolean hasData() {
        System.out.println("Попытка прочитать кэш-файл");
        try {
           dataProvider.addAll(FileReader.readCash(Config.getCASHPATH()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!dataProvider.isEmpty()) {
            System.out.println("Кэш-файл прочитан");
            System.out.printf("Тип записанных данных %s%n", dataProvider.getClazz().getSimpleName());
            return true;
        } else if (nextProcessor != null) {
            return nextProcessor.hasData();
        }
        System.out.println("Память пуста");
        return false;
    }
}

