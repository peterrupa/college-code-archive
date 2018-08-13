import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.Collections;

class KMeansClusteringSolver {
    private int k;
    private ArrayList<Coordinates> coordinates;
    
    public KMeansClusteringSolver(int k, ArrayList<Coordinates> coordinates) {
        this.k = k;
        this.coordinates = coordinates;
    }
    
    public ArrayList<ArrayList<Coordinates>> solve() {
        ArrayList<Coordinates> centroids = new ArrayList<Coordinates>();
        ArrayList<ArrayList<Coordinates>> iterations = new ArrayList<ArrayList<Coordinates>>();
        
        // initialize centroids (random k points from training set)
        ArrayList<Coordinates> centroidsRandom = new ArrayList<Coordinates>(this.coordinates);
        for(int i = 0; i < k; i++) {
            Random rand = new Random();
            
            int randomNumber = rand.nextInt(centroidsRandom.size());
            
            centroids.add(centroidsRandom.get(randomNumber));
            centroidsRandom.remove(randomNumber);
        }
        
        // add centroids to iteration lists
        iterations.add(centroids);
        
        while(true) {
            HashMap<Coordinates, HashMap<Coordinates, Double>> distanceTable = new HashMap<Coordinates, HashMap<Coordinates, Double>>();
            
            // put centroids in table
            for(int i = 0; i < centroids.size(); i++) {
                distanceTable.put(centroids.get(i), new HashMap<Coordinates, Double>());
            } 
            
            // for each point, compute distance from centroids
            for(int i = 0; i < coordinates.size(); i++) {
                // iterate centroids
                for(int j = 0; j < centroids.size(); j++) {
                    // compute the distance between p and c
                    double distance = MathHelper.distance(coordinates.get(i), centroids.get(j));
                    
                    distanceTable.get(centroids.get(j)).put(coordinates.get(i), distance);
                }
            }
                    
            // classify to nearest centroid
            HashMap<Coordinates, ArrayList<Coordinates>> classifications = new HashMap<Coordinates, ArrayList<Coordinates>>();
            
            // initialize array lists
            for(int i = 0; i < centroids.size(); i++) {
                classifications.put(centroids.get(i), new ArrayList<Coordinates>());
            }
            
            for(int i = 0; i < coordinates.size(); i++) {
                Coordinates nearest = centroids.get(0);
                
                for(int j = 0; j < centroids.size(); j++) {
                    if(distanceTable.get(centroids.get(j)).get(coordinates.get(i)) < distanceTable.get(nearest).get(coordinates.get(i))) {
                        nearest = centroids.get(j);
                    }
                }
                
                classifications.get(nearest).add(coordinates.get(i));
            }
            
            // get new centroids
            ArrayList<Coordinates> newCentroids = new ArrayList<Coordinates>();
            
            for(Coordinates centroid: classifications.keySet()) {
                ArrayList<Double> points = new ArrayList<Double>();
                
                for(int i = 0; i < centroid.size(); i++) {
                    double sum = 0;
                    
                    for(int j = 0; j < classifications.get(centroid).size(); j++) {
                        sum += classifications.get(centroid).get(j).get(i);
                    }
                    
                    points.add(sum / classifications.get(centroid).size());
                }
                
                // add centroid
                newCentroids.add(new Coordinates(points));
            }
            
            // if centroids change, repeat step 2
            if(compareCoordinates(newCentroids, centroids)) {
                // add centroids to iteration lists
                iterations.add(centroids);
                break;
            }
            
            centroids = newCentroids;
        }
        
        return iterations;
    }
    
    public boolean compareCoordinates(ArrayList<Coordinates> a, ArrayList<Coordinates> b) {
        a = new ArrayList<Coordinates>(a);
        b = new ArrayList<Coordinates>(b);
        
        boolean flag = true;
        
        for(Coordinates coordinateA: a) {
            if(!b.contains(coordinateA)) {
                flag = false;
                break;
            }
        }
        
        for(Coordinates coordinateB: b) {
            if(!a.contains(coordinateB)) {
                flag = false;
                break;
            }
        }
        
        return flag;
    }
}