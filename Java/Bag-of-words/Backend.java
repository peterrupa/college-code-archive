import java.io.*;
import java.util.*;

class Backend {
    public static void main(String args[]) {
        SpamChecker checker = new SpamChecker(readFile("spam.txt"), readFile("ham.txt"));
        String input;
        
        // read input file
        input = readFile("input.txt");
        
        String lines[] = input.split("\n");
        
        try {
            PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
            
            for(String line: lines) {
                writer.println(line + " " + (checker.isSpam(line)? "SPAM": "HAM"));
            }
            
            writer.close();
        }
        catch(Exception e) {}
        
    }
    
    private static String readFile(String filename) {
        String fileContents = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String line = null;
            
            // read all lines
            line = in.readLine();
            
            fileContents += line + "\n";
            
            while(true) {
                line = in.readLine();
                
                if(line == null) break;
                
                fileContents += line + "\n";
            }
            
            in.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return fileContents; 
    }
}