package config;



import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {
    private static final String CASHDIR = System.getProperty("user.dir") + "\\src\\cash\\";
    private static final String CASHPATH = CASHDIR+"cash";
    private static final String DATAHOLDER = System.getProperty("user.dir") + "\\src\\randomDataHolder\\";
    private static final String TILES = "bookTiles.txt";
    private static final String GENERS = "geners";
    private static final String NAMES = "names";
    private static final String COUNTRY = "country";
    private static final String PUBLISHNAMES = "publishName";
    private static final String CITY = "city";

    public static Path getCASHPATH() {
        return Paths.get(CASHPATH);
    }

    public static Path getPathNames(){
        return Paths.get(DATAHOLDER + NAMES);
    }


    public static Path getPathCountry(){
        return Paths.get(DATAHOLDER + COUNTRY);
    }

    public static Path getPathCity(){
        return Paths.get(DATAHOLDER + CITY);
    }

    public static Path getPathGenres(){
        return Paths.get(DATAHOLDER + GENERS);
    }

    public static Path getPathTitle(){
        return Paths.get(DATAHOLDER + TILES);
    }

    public static Path getPathPublisher(){
        return Paths.get(DATAHOLDER + PUBLISHNAMES);
    }


}
