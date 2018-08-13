import java.util.Hashtable;

class BagOfWords {
    private Hashtable<String, Integer> words;
    
    public BagOfWords() {
        words = new Hashtable<String, Integer>();
    }
    
    public void addWords(String inputWords) {
        String input = new String(inputWords);
        
        // replace newlines with spaces
        input = input.replaceAll("[\\t\\n\\r]", " ");
        
        // split by space
        String[] wordsArray = input.split(" ");
        
        // add all words
        for(String word: wordsArray) {
            this.addWord(word);
        }
    }
    
    private void addWord(String word) {
        String input = new String(word);
        
        input = escape(input);
        
        if(words.containsKey(input)) {
            int number = words.get(input);
            
            // increment value
            words.put(input, number + 1);
        }
        else {
            words.put(input, 1);
        }
    }
    
    public Hashtable<String, Integer> getWords() {
        return this.words;
    }
    
    // for debugging purposes
    public void print() {
        System.out.println(this.words.toString());
    }
    
    private String escape(String x) {
        String input = new String(x);
        
        input = input.toLowerCase();
        
        // remove all non-alphanumeric characters
        input = input.replaceAll("[^a-z0-9]", "");
        
        return input;
    }
    
    public int getTotalWords() {
        int total = 0;
        
        for(String x: this.words.keySet()) {
            total += this.words.get(x);
        }
        
        return total;
    }
    
    public int getDictionarySize() {
        int total = 0;
        
        return this.words.keySet().size();
    }
}