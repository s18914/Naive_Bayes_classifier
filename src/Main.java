import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static int k;
    private static ArrayList<Point> pointsTrain = new ArrayList<>();
    private static ArrayList<Point> pointsTest = new ArrayList<>();
    public static ArrayList<ArrayList<String>> atrybuty;


    public static void main(String[] args) {
        //część treningowa
        pointsTrain = FileService.czytajPlik("trainingset.csv", false);


        //zliczanie atrybutów
        atrybuty = new ArrayList<>();
        for(int i =0; i<FileService.ileWymiarow;i++){
            ArrayList<String> a = new ArrayList<>();
            for(Point p : pointsTrain){

                if(a.indexOf(p.attributes.get(i))==-1){    //jesli nie ma takiej wsrtosci w arrayliście to ją dodaj
                    a.add(p.attributes.get(i));
                }
            }
            atrybuty.add(a);
        }

        //wyświetlam atrybuty
        for (int i=0; i<atrybuty.size(); i++)
            System.out.print(atrybuty.get(i)+" ");

        pointsTest = FileService.czytajPlik("testset.csv", true);


        for(Point p : pointsTest){
            String odp = "";
            float odpF = 0;
            for(int i=0; i<atrybuty.get(atrybuty.size()-1).size(); i++){  
                float c = calculateP(p.attributes,atrybuty.get(atrybuty.size()-1).get(i));
                if(c>odpF){
                    odpF=c;
                    odp = atrybuty.get(atrybuty.size()-1).get(i);
                }
            }
            p.attributes.add(odp);
            System.out.println(p.toString());

        }


        //ręczne dodawanie
        Scanner skaner = new Scanner(System.in);
        Point nowyPunkt = new Point();

        for(int i=0; i<atrybuty.size()-1; i++){
            System.out.println("Podaj parametr z : " + atrybuty.get(i).toString());
            nowyPunkt.attributes.add(skaner.nextLine());
        }
        String odp = "";
        float odpF = 0;

        for(int i=0; i<atrybuty.get(atrybuty.size()-1).size(); i++){ 
            float c = calculateP(nowyPunkt.attributes,atrybuty.get(atrybuty.size()-1).get(i));
            if(c>odpF){
                odpF=c;
                odp = atrybuty.get(atrybuty.size()-1).get(i);
            }
        }
        nowyPunkt.attributes.add(odp);
        System.out.println("Response : " + odp);



    }





    //===================================metody=========================================
    public static float calculateP(ArrayList<String> attributes, String wynik){
        float f =zliczJedno(wynik);
        f =f/pointsTrain.size();

        int i=0;

        for(String s : attributes){
            float e = zliczWarunek(wynik,s,i)+1;
            e = e/(zliczJedno(wynik)+atrybuty.get(i).size());
            f=e;
            i++;
        }
        return f;
    }


    public static int zliczJedno(String s){   //ile jest takich wyników w ostatniej kolumnie
        int i=0;
        for(Point p : pointsTrain){
            if(p.attributes.get(p.attributes.size()-1).equals(s))
                i++;
        }
        return i;
    }
    public static int zliczWarunek(String s1,String s2, int j){
        int i=0;
        ArrayList<Point> list = getListWithCondition(s1);

        for(Point p : list){
            if(p.attributes.get(j).equals(s2))
                i++;
        }
        return i;
    }

    public static ArrayList<Point> getListWithCondition(String s){
        ArrayList<Point> list = new ArrayList();
        for(Point p : pointsTrain){
            if(p.attributes.get(p.attributes.size()-1).equals(s))
                list.add(p);
        }
        return list;
    }


}
