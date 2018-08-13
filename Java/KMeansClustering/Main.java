import java.util.ArrayList;

class Main {
    public static void main(String args[]) {
        // read input fle
        String[] input = FileHelper.readFile("input.txt").split("\n");
        
        // get k
        int k = Integer.parseInt(input[0]);
        ArrayList<Coordinates> coordinates = new ArrayList<Coordinates>();
        
        // read vectors
        for(int i = 1; i < input.length; i++) {
            ArrayList<Double> temp = new ArrayList<Double>();
            
            String[] line = input[i].split(" ");
            
            for(int j = 0; j < line.length; j++) {
                temp.add(Double.parseDouble(line[j]));
            }
            coordinates.add(new Coordinates(temp));
        }
        
        // solve
        KMeansClusteringSolver solver = new KMeansClusteringSolver(k, coordinates);
        
        ArrayList<ArrayList<Coordinates>> solution = solver.solve();
        
        // write output to file
        String output = "";
        
        for(int i = 0; i < solution.size(); i++) {
            output += "Iteration " + (i + 1) + ":\n";
            
            for(int j = 0; j < solution.get(i).size(); j++) {
                output += "Centroid " + (j + 1) + ": " + solution.get(i).get(j) + "\n";
            }
        }
        
        FileHelper.writeFile("output.txt", output);
    }
}