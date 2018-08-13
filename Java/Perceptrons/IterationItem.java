import java.text.DecimalFormat;

class IterationItem {
    private int iterationNumber;
    
    private double x0;
    private double x1;
    private double b;
    private double w0;
    private double w1;
    private double wb;
    private double a;
    private int y;
    private int z;
    
    private double r;
    private double t;
    
    public IterationItem(int iterationNumber, double x0, double x1, double b, int z, double r, double t) {
        this.iterationNumber = iterationNumber;
        this.x0 = x0;
        this.x1 = x1;
        this.b = b;
        this.w0 = 0;
        this.w1 = 0;
        this.wb = 0;
        this.r = r;
        this.t = t;
        this.z = z;
        this.a = this.computeA();
        this.y = this.computeY();
    }
    
    public IterationItem(int iterationNumber, double x0, double x1, int z, IterationItem previousIteration) {
        this.iterationNumber = iterationNumber;
        this.x0 = x0;
        this.x1 = x1;
        this.b = previousIteration.getB();
        this.r = previousIteration.getR();
        this.t = previousIteration.getT();
        this.z = z;
        this.w0 = this.computeWeight(previousIteration.getW0(), previousIteration.getR(), previousIteration.getX0(), previousIteration.getZ(), previousIteration.getY());
        this.w1 = this.computeWeight(previousIteration.getW1(), previousIteration.getR(), previousIteration.getX1(), previousIteration.getZ(), previousIteration.getY());
        this.wb = this.computeWeight(previousIteration.getWb(), previousIteration.getR(), previousIteration.getB(), previousIteration.getZ(), previousIteration.getY());
        this.a = this.computeA();
        this.y = this.computeY();
    }
    
    public int getIterationNumber() {
        return this.iterationNumber;
    }
    
    public double getX0() {
        return this.x0;
    }
    
    public double getX1() {
        return this.x1;
    }
    
    public double getB() {
        return this.b;
    }
    
    public double getW0() {
        return this.w0;
    }
    
    public double getW1() {
        return this.w1;
    }
    
    public double getWb() {
        return this.wb;
    }
    
    public double getR() {
        return this.r;
    }
    
    public double getT() {
        return this.t;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getZ() {
        return this.z;
    }
    
    private double computeA() {
        return (x0 * w0) + (x1 * w1) + (b * wb);
    }
    
    private int computeY() {
        return this.a > this.t? 1: 0;
    }
    
    private double computeWeight(double current, double r, double x, int z, int y) {
        return current + (r * x * (z - y)); 
    }
    
    public String toString() {
        return this.iterationNumber + "\t" + truncateDecimal(this.x0) + "\t" + truncateDecimal(this.x1) + "\t" + truncateDecimal(this.b) + "\t" + truncateDecimal(this.w0) + "\t" + truncateDecimal(this.w1) + "\t" + truncateDecimal(this.wb) + "\t" + truncateDecimal(this.a) + "\t" + this.y + "\t" + this.z; 
    }
    
    private String truncateDecimal(double x) {
        return new DecimalFormat("#.##").format(x).toString();
    }
}