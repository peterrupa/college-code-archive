import java.util.*;

class SpamChecker {
    BagOfWords spamBag = new BagOfWords();
    BagOfWords hamBag = new BagOfWords();
    ArrayList<String> spamLines = new ArrayList<String>();
    ArrayList<String> hamLines = new ArrayList<String>();
    int k, jointDictionarySize;
        
    public SpamChecker(String spam, String ham, int k) {
        this.k = k;
        
        // add to bag of words
        spamBag.addWords(spam);   
        hamBag.addWords(ham);
        
        this.jointDictionarySize = jointDictionarySize(spamBag, hamBag);
        
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
        return computeIsSpam(input) >= 0.5;
    }
    
    private double computeIsSpam(String input) {
        return (double)messageIsSpam(input) / ((double)messageIsSpam(input) + (double)messageIsHam(input));
    }
    
    private double spamProbability() {
        return ((double)spamLines.size() + (double)this.k) / ((double)(spamLines.size() + hamLines.size()) + (double)(2 * this.k)); 
    }
    
    private double hamProbability() {
        return 1 - spamProbability(); 
    }
    
    private double wordIsSpam(String word, int newWordsCount) {
        int wordCount;
                
        if(spamBag.getWords().get(word) == null) {
            wordCount = 0;
        }
        else {
            wordCount = spamBag.getWords().get(word);
        }
        
        return ((double)wordCount + (double)this.k) / ((double)spamBag.getTotalWords() + (double)this.k * ((double)this.jointDictionarySize + (double)newWordsCount));
    }
    
    private double wordIsHam(String word, int newWordsCount) {        
        int wordCount;
                
        if(hamBag.getWords().get(word) == null) {
            wordCount = 0;
        }
        else {
            wordCount = hamBag.getWords().get(word);
        }
        
        return ((double)wordCount + (double)this.k) / ((double)hamBag.getTotalWords() + (double)this.k * ((double)this.jointDictionarySize + (double)newWordsCount));
    }
    
    private double messageIsSpam(String message) {
        String[] words = message.split(" ");
        
        double total = 1;
        
        // get number of new words
        ArrayList<String> newWords = new ArrayList<String>();
        
        for(String word: words) {
            // check if in spam bag
            if(spamBag.getWords().containsKey(word) || hamBag.getWords().containsKey(word)) {
                continue;
            }
            
            if(!newWords.contains(word)) {
                newWords.add(word);
            }
        }
        
        for(String word: words) {
            total *= wordIsSpam(word, newWords.size());
        }
        
        total *= spamProbability();
        
        return total;
    }
    
    private double messageIsHam(String message) {
        String[] words = message.split(" ");
        
        double total = 1;
        
        // get number of new words
        ArrayList<String> newWords = new ArrayList<String>();
        
        for(String word: words) {
            // check if in spam bag
            if(spamBag.getWords().containsKey(word) || hamBag.getWords().containsKey(word)) {
                continue;
            }
            
            if(!newWords.contains(word)) {
                newWords.add(word);
            }
        }
        
        for(String word: words) {
            total *= wordIsHam(word, newWords.size());
        }
        
        total *= hamProbability();
        
        return total;
    }
    
    private int jointDictionarySize(BagOfWords x, BagOfWords y) {
        ArrayList<String> jointBagOfWords = new ArrayList<String>();
        
        // add x bag
        for(String word: x.getWords().keySet()) {
            jointBagOfWords.add(word);
        }
        
        // add y bag
        for(String word: y.getWords().keySet()) {
            if(!jointBagOfWords.contains(word)) {
                jointBagOfWords.add(word);
            }
        }
        
        return jointBagOfWords.size();
    }
}