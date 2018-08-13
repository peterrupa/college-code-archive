import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;

class UI {
    private JFrame frame;
    private BagOfWords bow;
    private JLabel totalWords;
    private JLabel dictionarySize;
    private JScrollPane scroll;
    
    public UI() {
        // initialize frame
        frame = new JFrame("Bag of words");
        
        frame.setLayout(new FlowLayout());
        frame.setPreferredSize(new Dimension(500, 600));
        
        // initialize selector
        JPanel selectorPanel = new JPanel(new FlowLayout());
        selectorPanel.setPreferredSize(new Dimension(500, 50));
        
        selectorPanel.add(new JLabel("Select file: "));
        
        JButton openButton = new JButton("Open");
        
        openButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // open file chooser
                JFileChooser chooser = new JFileChooser();
                int returnVal = chooser.showOpenDialog(frame);
                
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    // read contents
                    String fileContents = readFile(chooser.getSelectedFile().getAbsolutePath());
                    
                    // add contents to words
                    bow.addWords(fileContents);
                    
                    // recreate UI
                    render();
                }
            }
        });
        
        selectorPanel.add(openButton);
        
        frame.add(selectorPanel);
        
        // initialize dictionary size
        JPanel dictionarySizeContainer = new JPanel(new FlowLayout());
        dictionarySizeContainer.setPreferredSize(new Dimension(500, 50));
        
        dictionarySize = new JLabel("Dictionary Size:  ");
        
        dictionarySizeContainer.add(dictionarySize);
        
        frame.add(dictionarySizeContainer);
        
        // initialize total words
        JPanel totalWordsContainer = new JPanel(new FlowLayout());
        totalWordsContainer.setPreferredSize(new Dimension(500, 50));
        
        totalWords = new JLabel("Total words:  "); 
        totalWordsContainer.add(totalWords);
        
        frame.add(totalWordsContainer);
        
        // initialize table
        String[] col = {"Word", "#"};
        String[][] test = {}; 
        
        JTable table = new JTable(test, col);
        
        table.setEnabled(false);
        
        scroll = new JScrollPane(table);
        
        scroll.setPreferredSize(new Dimension(450, 350));
        
        frame.add(scroll);
        
        frame.pack();
        frame.setVisible(true);
        
        bow = new BagOfWords();
    }
    
    private String readFile(String filename) {
        String fileContents = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String line = null;
            
            // read all lines
            line = in.readLine();
            
            fileContents += line + " ";
            
            while(true) {
                line = in.readLine();
                
                if(line == null) break;
                
                fileContents += line + " ";
            }
            
            in.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return fileContents; 
    }
    
    private void render() {
        // recreate UI with bow values
        totalWords.setText("Total words: " + this.bow.getTotalWords());
        dictionarySize.setText("Dictionary Size: " + this.bow.getDictionarySize());
        
        // table of words
        String[] col = {"Word", "#"};
        
        Hashtable<String, Integer> words = this.bow.getWords();
        
        String[][] wordsList = new String[this.bow.getDictionarySize()][];
        
        int i = 0;
        
        for(String x: words.keySet()) {
            String[] pair = {x, words.get(x) + ""};
            
            wordsList[i] = pair;
            
            i++;
        } 
        
        JTable table = new JTable(wordsList, col);
        
        table.setEnabled(false);
        
        frame.remove(scroll);
        
        scroll = new JScrollPane(table);
        
        scroll.setPreferredSize(new Dimension(450, 350));
        
        frame.add(scroll);
    }
}