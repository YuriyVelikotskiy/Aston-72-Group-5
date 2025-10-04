package readStrategy;

public interface ReadStrategy {
    void read();
}

class FileReadStrategy implements ReadStrategy {
    @Override
    public void read() {
        System.out.println("Я читаю файл");
    }
}

class RandomStrategy implements ReadStrategy {
    @Override
    public void read() {
        System.out.println("Получаю рандомные данные");
    }
}

class ConsoleReadStrategy implements ReadStrategy {
    @Override
    public void read() {
        System.out.println("Читаю из консоли");
    }
}


