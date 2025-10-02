package dataProcessor;

import model.DataProvider;

public class MemoryDataProcessor extends DataProcessor {

    DataProvider dataProvider = DataProvider.getInstance();

    public MemoryDataProcessor(DataProcessor next) {
        super(next);
    }

    @Override
    public boolean hasData() {
        if(!dataProvider.isEmpty()){
            System.out.println("Data in memory");
            return true;
        } else if (nextProcessor != null) {
            return nextProcessor.hasData();
        }
        System.out.println("Memory is Empty");
        return false;
    }
}
