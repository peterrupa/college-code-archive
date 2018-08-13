class Shape {
    public static void main(String args[]) {
        int width;
        int height;
        int indent;
        
        // set up arguments
        if(args.length == 0) {
            System.out.println("Not enough arguments.");
            return;
        }
        
        // default values
        width = Integer.parseInt(args[0]);
        height = Integer.parseInt(args[0]);
        indent = 0;
        
        // assign values based on given parameters
        if(args.length == 2) {
            height = Integer.parseInt(args[1]);
        }
        if(args.length == 3) {
            height = Integer.parseInt(args[1]);
            indent = Integer.parseInt(args[2]);
        }
        
        // iteration for lines
        for(int i = 0; i < height; i++) {
            // iteration for indent
            for(int j = 0; j < indent * i; j++) {
                System.out.print(" ");
            }
            
            // iteration for *
            for(int k = 0; k < width; k++) {
                System.out.print("*");
            }
            
            System.out.print("\n");
        }
    }
}