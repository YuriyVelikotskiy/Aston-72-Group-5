package readStrategy;

import classBuilder.CashedClass;
//import fileReader.FileReader;


import java.io.IOException;

public class FileReadStrategy implements ReadStrategy {

    private final Class<? extends CashedClass> type;

    public FileReadStrategy(Class<? extends CashedClass> type){
        this.type = type;
    }

    @Override
    public void read() {
       /* try {
            //FileReader.readFile("dfd", type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } */
    }
}
