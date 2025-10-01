package DataProcessor;

import CashCreater.CashReader;
import Model.DataProvider;

public class FileDataProcessor extends DataProcessor {

    public FileDataProcessor(DataProcessor next) {
        super(next);
    }

    @Override
    public boolean hasData() {

        try {
            System.out.println("Try read cash");
            var inPutArray = CashReader.getArrayList();
            DataProvider.addAll(inPutArray);
        } catch (IllegalArgumentException e){
            System.out.println(e + "\nCash-file is not valid");
        }

        if (!DataProvider.isEmpty()) {
            System.out.println("Cash-file is readied");
            return true;
        } else if (nextProcessor != null) {
            return nextProcessor.hasData();
        }
        System.out.println("Memory is Empty");
        return false;
    }
}
