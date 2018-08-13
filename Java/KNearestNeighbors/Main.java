import java.util.ArrayList;

class Main {
    public static void main(String args[]) {
        // read training file
        String trainingText = FileHelper.readFile("training.txt");
        
        // read input file
        String inputText = FileHelper.readFile("input.txt");
        
        // perform algorithm
        KSolver solver = new KSolver(trainingText);
        
        // convert input text to points
        ArrayList<Coordinates> input = parseCoordinates(inputText);
        
        // build output text
        String out = "";
        
        for(int i = 0; i < input.size(); i++) {
            out += solver.classify(input.get(i)) + "\n";
        }
        
        // print output
        FileHelper.writeFile("output.txt", out);
    }
    
    private static ArrayList<Coordinates> parseCoordinates(String input) {
        String[] lines = input.split("\n");
        ArrayList<Coordinates> ret = new ArrayList<Coordinates>(); 
        
        for(int i = 0; i < lines.length; i++) {
            String[] numbers = lines[i].split(" ");
        
            ArrayList<Double> points = new ArrayList<Double>();
            
            for(int j = 0; j < numbers.length; j++) {
                points.add(Double.parseDouble(numbers[j]));
            }
            
            ret.add(new Coordinates(points));
        }
        
        return ret;
    }
}