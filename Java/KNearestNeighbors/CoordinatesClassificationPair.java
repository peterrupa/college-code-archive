class CoordinatesClassificationPair {
    private Coordinates coordinates;
    private int classification;
    
    public CoordinatesClassificationPair(Coordinates coordinates, int classification) {
        this.coordinates = coordinates;
        this.classification = classification;
    }
    
    public Coordinates getCoordinates() {
        return this.coordinates;
    }
    
    public int getClassification() {
        return this.classification;
    }
    
    public String toString() {
        return "{" + this.classification + ": " + this.coordinates + "}";
    }
}