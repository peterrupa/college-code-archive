class CoordinatesClassificationDistance {
    private CoordinatesClassificationPair pair;
    private double distance;
    
    public CoordinatesClassificationDistance(CoordinatesClassificationPair pair, double distance) {
        this.pair = pair;
        this.distance = distance;
    }
    
    public CoordinatesClassificationPair getPair() {
        return this.pair;
    }
    
    public double getDistance() {
        return this.distance;
    }
    
    public String toString() {
        return this.pair.getCoordinates().toString();
    }
}