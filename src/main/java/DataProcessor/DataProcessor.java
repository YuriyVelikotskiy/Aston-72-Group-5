package DataProcessor;

import Model.DataProvider;

public abstract class DataProcessor {
    protected DataProcessor nextProcessor;
    public DataProcessor(DataProcessor next){
        this.nextProcessor = next;
    }
    public abstract boolean hasData();
}

