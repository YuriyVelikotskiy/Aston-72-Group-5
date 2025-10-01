package DataProcessor;

import Model.DataProvider;

public class MemoryDataProcessor extends DataProcessor {

    public MemoryDataProcessor(DataProcessor next) {
        super(next);
    }

    @Override
    public boolean hasData() {
        if(!DataProvider.isEmpty()){
            System.out.println("Data in memory");
            return true;
        } else if (nextProcessor != null) {
            return nextProcessor.hasData();
        }
        System.out.println("Memory is Empty");
        return false;
    }
}
