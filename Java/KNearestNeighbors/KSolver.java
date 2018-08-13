import java.util.ArrayList;
import java.util.HashMap;

class KSolver {
    private HashMap<Integer, ArrayList<Coordinates>> pointsWithClassfication = new HashMap<Integer, ArrayList<Coordinates>>();
    private int k; 
    
    public KSolver(String trainingData) {
        String[] trainingDataArray = trainingData.split("\n");
        
        // get k
        this.k = Integer.parseInt(trainingDataArray[0]);
        
        // convert trainingData to coordinates
        for(int i = 1; i < trainingDataArray.length; i++) {
            String[] numbers = trainingDataArray[i].split(" ");
            
            ArrayList<Double> points = new ArrayList<Double>();
            
            for(int j = 0; j < numbers.length - 1; j++) {
                points.add(Double.parseDouble(numbers[j]));
            }
            
            // get classification
            int classification = Integer.parseInt(numbers[numbers.length - 1]);
            
            // coordinates object
            Coordinates coordinate = new Coordinates(points);
            
            // add to hashmap
            if(this.pointsWithClassfication.containsKey(classification)) {
                // append to arraylist
                this.pointsWithClassfication.get(classification).add(coordinate);
            }
            else {
                // construct new key value pair
                ArrayList<Coordinates> initial = new ArrayList<Coordinates>();
                
                initial.add(coordinate);
                
                this.pointsWithClassfication.put(classification, initial);
            }
        }
    }
    
    public String classify(Coordinates input) {
        // flatten data
        ArrayList<CoordinatesClassificationPair> pointsList = new ArrayList<CoordinatesClassificationPair>();
        
        for(int key: this.pointsWithClassfication.keySet()) {
            ArrayList<Coordinates> coordinatesList = this.pointsWithClassfication.get(key); 
            
            for(int i = 0; i < coordinatesList.size(); i++) {
                pointsList.add(new CoordinatesClassificationPair(coordinatesList.get(i), key));
            }
        }
                
        ArrayList<CoordinatesClassificationDistance> nearestPoints = new ArrayList<CoordinatesClassificationDistance>();
        
        // get distance to each point and check if nearest
        for(int i = 0; i < pointsList.size(); i++) {
            double distance = MathHelper.distance(input, pointsList.get(i).getCoordinates());
            
            if(nearestPoints.size() < this.k) {
                nearestPoints.add(new CoordinatesClassificationDistance(pointsList.get(i), distance));
            }
            else {
                // check if distance is higher than others
                for(int j = 0; j < nearestPoints.size(); j++) {
                    double distanceCompare = nearestPoints.get(j).getDistance();
                    
                    if(distance < distanceCompare) {
                        // remove higher distance
                        nearestPoints.remove(j);
                        
                        // add lower distance
                        nearestPoints.add(new CoordinatesClassificationDistance(pointsList.get(i), distance));
                        
                        break;
                    }
                }
            }
        }
        
        // classify
        HashMap<Integer, Integer> numberOfOccurence = new HashMap<Integer, Integer>();
        
        // count classification occurence
        for(CoordinatesClassificationDistance pointsTrio: nearestPoints) {
            int classification = pointsTrio.getPair().getClassification();
            
            if(numberOfOccurence.containsKey(classification)) {
                // increment count
                numberOfOccurence.put(classification, numberOfOccurence.get(classification) + 1);
                
            }
            else {
                // add classification
                numberOfOccurence.put(classification, 1);
            }
        }
        
        // find maximum count
        int classificationHighest = 0;
        int highestCount = 0;
        boolean flag = false;
        
        for(int classification: numberOfOccurence.keySet()) {
            if(!flag) {
                classificationHighest = classification;
                highestCount = numberOfOccurence.get(classification);
                flag = true;
            }
            else {
                int currentCount = numberOfOccurence.get(classification);
                
                if(currentCount > highestCount) {
                    classificationHighest = classification;
                    highestCount = highestCount;
                }
            }
        }
        
        // add to points
        pointsWithClassfication.get(classificationHighest).add(input);
        
        return "Nearest neighbors of " + input + ": " + nearestPoints + "\n" +
               "Class of " + input + ": " + classificationHighest;
    }
}