package dataProcessor;

public class MemoryDataProcessor extends DataProcessor {

    public MemoryDataProcessor(DataProcessor next) {
        super(next);
    }

    //Метод пытается найти данные в памяти, в случае успеха возвращает true и пишет в консоль об успехе
    @Override
    public boolean hasData() {
        if (!dataProvider.isEmpty()) {
            System.out.printf("Тип записанных данных %s%n", dataProvider.getClazz().getSimpleName());
            return true;
        } else if (nextProcessor != null) {
            return nextProcessor.hasData();
        }
        System.out.println("Память пуста");
        return false;
    }
}

