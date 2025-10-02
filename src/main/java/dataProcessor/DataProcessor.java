package dataProcessor;

import dataProvider.DataProvider;

public abstract class DataProcessor {
    protected DataProcessor nextProcessor;
    protected DataProvider dataProvider = DataProvider.getInstance();

    public DataProcessor(DataProcessor next) {
        this.nextProcessor = next;
    }

    public abstract boolean hasData();
}

