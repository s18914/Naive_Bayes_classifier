import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileService {

    public static ArrayList<Point> points ;
    public static int ileWymiarow;


    // Plik excel --> ArrayList (Stringi z liniami z excela)
    public static ArrayList<Point> czytajPlik( String nazwaPliku, boolean isTest) {
        points = new ArrayList<>();       //czyszczenie listy

        Path sciezkaDoPliku = Paths.get(System.getProperty("user.home")+"/"+nazwaPliku);

        ArrayList odczyt = new ArrayList<>();
        try {
            odczyt = (ArrayList) Files.readAllLines(sciezkaDoPliku);
        } catch (IOException ex) {
            System.out.println("Brak pliku!");
        }

        if(isTest)
        doObiektowTest(odczyt);
        else if(!isTest)
            doObiektow(odczyt);

        return points;

    }



    public static void doObiektow(ArrayList<String> odczyt) {
        for (String linia : odczyt) {
            if(linia.contains(";;")){
                break;
            }
            // Dzielenie na poszczegolne pola
            String[] l = linia.split(";");
            //System.out.println("--- " + linia);
            ileWymiarow = l.length;
            ArrayList list = new ArrayList();
            for(String s : l){
                list.add(s);
            }

            Point p = new Point(list);
            points.add(p);
        }
    }

    public static void doObiektowTest(ArrayList<String> odczyt) {
        for (String linia : odczyt) {
            if(linia.contains(";;")){
                break;
            }
            String[] l = linia.split(";");
            ArrayList list = new ArrayList();
            for(String s : l){
                list.add(s);
            }

            Point p = new Point(list);
            points.add(p);
        }
    }
}
