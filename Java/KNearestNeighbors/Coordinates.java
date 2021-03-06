import java.util.ArrayList;

class Coordinates {
    ArrayList<Double> points;
    
    public Coordinates(ArrayList<Double> points) {
        this.points = new ArrayList<Double>(points);
    }
    
    public Double get(int n) {
        return this.points.get(n);
    }
    
    public ArrayList<Double> getAll() {
        return new ArrayList<Double>(this.points);
    }
    
    public String toString() {
        String all = "";
        
        for(int i = 0; i < points.size() - 1; i++) {
            all += points.get(i) + ", ";
        }
        
        all += points.get(points.size() - 1);
        
        return "<" + all + ">";
    }
}