import java.util.*;

class SpamChecker {
    BagOfWords spamBag = new BagOfWords();
    BagOfWords hamBag = new BagOfWords();
    ArrayList<String> spamLines = new ArrayList<String>();
    ArrayList<String> hamLines = new ArrayList<String>();
        
    public SpamChecker(String spam, String ham) {
        // add to bag of words
        spamBag.addWords(spam);   
        hamBag.addWords(ham);
        
        // keep reference to each line as well
        String[] spams = spam.split("\n");
        for(String line: spams) {
            spamLines.add(line);
        }
        
        String[] hams = ham.split("\n");
        for(String line: hams) {
            hamLines.add(line);
        }
    }
    
    public boolean isSpam(String input) {
        return (float)messageIsSpam(input) / ((float)messageIsSpam(input) + (float)messageIsHam(input)) >= 0.5;
    }
    
    private float spamProbability() {
        return (float)spamLines.size() / (float)(spamLines.size() + hamLines.size()); 
    }
    
    private float hamProbability() {
        return 1 - spamProbability(); 
    }
    
    private float wordIsSpam(String word) {        
        if(spamBag.getWords().get(word) == null) {
            return 0;
        }
        
        return (float)spamBag.getWords().get(word) / (float)spamBag.getTotalWords();
    }
    
    private float wordIsHam(String word) {        
        if(hamBag.getWords().get(word) == null) {
            return 0;
        }
        
        return (float)hamBag.getWords().get(word) / (float)hamBag.getTotalWords();
    }
    
    private float messageIsSpam(String message) {
        String[] words = message.split(" ");
        
        float total = 1;
        
        for(String word: words) {
            total *= wordIsSpam(word);
        }
        
        total *= spamProbability();
        
        return total;
    }
    
    private float messageIsHam(String message) {
        String[] words = message.split(" ");
        
        float total = 1;
        
        for(String word: words) {
            total *= wordIsHam(word);
        }
        
        total *= hamProbability();
        
        return total;
    }
}