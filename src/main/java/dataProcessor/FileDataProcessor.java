package dataProcessor;

import CashCreater.CashReader;
import classBuilder.Author;
import classBuilder.CashedClass;
import model.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class FileDataProcessor extends DataProcessor {

    public FileDataProcessor(DataProcessor next) {
        super(next);
    }

    @Override
    public boolean hasData() {
        DataProvider dataProvider = DataProvider.getInstance();
        try {
            System.out.println("Try read cash");
            var inPutArray = new ArrayList<CashedClass>(List.of(new Author.AuthorBuilder().build(),new Author.AuthorBuilder().build())); //TODO заглушка - убрать
            dataProvider.addAll(inPutArray);
        } catch (IllegalArgumentException e){
            System.out.println(e + "\nCash-file is not valid");
        }

        if (!dataProvider.isEmpty()) {
            System.out.println("Cash-file is readied");
            return true;
        } else if (nextProcessor != null) {
            return nextProcessor.hasData();
        }
        System.out.println("Memory is Empty");
        return false;
    }
}
