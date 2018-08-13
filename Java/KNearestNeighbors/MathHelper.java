class MathHelper {
    public static double distance(Coordinates x, Coordinates y) {
        double sum = 0;
        
        // get summation
        for(int i = 0; i < x.getAll().size(); i++) {
            sum += Math.pow(x.getAll().get(i) - y.getAll().get(i), 2);
        }
        
        return Math.sqrt(sum);
    }
}