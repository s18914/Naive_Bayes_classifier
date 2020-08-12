import java.util.ArrayList;

public class Point {
    ArrayList<String> attributes;
    String name;
    static int number=-50;

    public Point(){
    this.attributes = new ArrayList<>();
    }
    public Point(ArrayList dim){
        this.attributes = dim;
        number++;
        this.name = "Point" + number;
    }

    public String toString(){
        return name + attributes.toString();
    }


}

