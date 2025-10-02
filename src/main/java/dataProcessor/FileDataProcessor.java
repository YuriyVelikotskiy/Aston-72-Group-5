package dataProcessor;

import CashCreater.CashReader;
import model.DataProvider;

public class FileDataProcessor extends DataProcessor {

    public FileDataProcessor(DataProcessor next) {
        super(next);
    }

    @Override
    public boolean hasData() {
        try {
            System.out.println("Try read cash");
            var inPutArray = CashReader.getArrayList();
            dataProvider.addAll(inPutArray);
        } catch (IllegalArgumentException e) {
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
