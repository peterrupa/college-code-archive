import java.io.*;

class FileHelper {
    public static String readFile(String filename) {
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
    
    public static void writeFile(String filename, String input) {
        try {
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            
            writer.print(input);
            
            writer.close();
        }
        catch(Exception e) {}
    }
}