import java.util.ArrayList;

class Main {
    public static void main(String args[]) {
        final int MAX_ITERATIONS = 1000;
        
        // read input
        ArrayList<Input> input = new ArrayList<Input>();
        
        String[] inputLines = FileHelper.readFile("input.txt").split("\n");
        
        double r = Double.parseDouble(inputLines[0]);
        double t = Double.parseDouble(inputLines[1]);
        double b = Double.parseDouble(inputLines[2]);
        
        for(int i = 3; i < inputLines.length; i++) {
            // get values
            String[] values = inputLines[i].split(" ");
            
            Input inputItem = new Input(Double.parseDouble(values[0]), Double.parseDouble(values[1]), Integer.parseInt(values[2]));
            input.add(inputItem);
        }
        
        // compute iterations
        ArrayList<ArrayList<IterationItem>> iterations = new ArrayList<ArrayList<IterationItem>>();
        
        for(int i = 0; i < MAX_ITERATIONS; i++) {
            ArrayList<IterationItem> iteration = new ArrayList<IterationItem>();
             
            for(int j = 0; j < input.size(); j++) {
                if(i == 0 && j == 0) {
                    iteration.add(new IterationItem(i, input.get(j).getX0(), input.get(j).getX1(), b, input.get(j).getZ(), r, t));
                }
                else if(j == 0) {
                    iteration.add(new IterationItem(i, input.get(j).getX0(), input.get(j).getX1(), input.get(j).getZ(), iterations.get(i - 1).get(iterations.get(i - 1).size() - 1)));
                }
                else {
                    iteration.add(new IterationItem(i, input.get(j).getX0(), input.get(j).getX1(), input.get(j).getZ(), iteration.get(j - 1)));
                }
            }
            
            iterations.add(iteration);
            
            // check if convergence
            double w0 = iteration.get(0).getW0();
            double w1 = iteration.get(0).getW1();
            double wb = iteration.get(0).getWb();
            boolean isConvergent = true;
            
            for(int j = 1; j < iteration.size(); j++) {
                if(!(iteration.get(j).getW0() == w0 && iteration.get(j).getW1() == w1 && iteration.get(j).getWb() == wb)) {
                    isConvergent = false;
                    break;
                }
            }
            
            if(isConvergent) {
                break;
            }
        } 
        
        // print output
        String output = "Iter\tx0\tx1\tb\tw0\tw1\twb\ta\ty\tz\n";
        for(ArrayList<IterationItem> iteration: iterations) {
            for(IterationItem item: iteration) {
                output += item + "\n";
            }
        }
        FileHelper.writeFile("output.txt", output);
    }
}