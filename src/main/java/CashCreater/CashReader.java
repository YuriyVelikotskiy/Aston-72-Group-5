package CashCreater;

import classBuilder.Author;
import classBuilder.CashedClass;

import java.util.ArrayList;
import java.util.List;

public class CashReader {

    public static ArrayList<CashedClass> getArrayList() {
        return new ArrayList<>(List.of(new Author.AuthorBuilder().name("AA Filatof").birthAYear(1997).country("Russia").build(),
                new Author.AuthorBuilder().name("AB Filatof").birthAYear(1997).country("Russia").build(),
                new Author.AuthorBuilder().name("AV Filatof").birthAYear(1997).country("Russia").build()));
    }
}
