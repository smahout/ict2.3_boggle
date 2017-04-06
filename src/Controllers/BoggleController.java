/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.CharacterPoint;
import Model.PathList;
import View.BoggleView;
import boggle.Boggle;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wouter
 */
public class BoggleController {
    private ArrayList<CharacterPoint> points;
    private BufferedReader br;
    
    public BoggleController() throws InterruptedException{
        Thread.sleep(800); // Wait for application to start
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        try {
            br = new BufferedReader(new FileReader("dict.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        points = new ArrayList();
        for (int i = 0; i < Boggle.x; i++) {
            for (int j = 0; j < Boggle.y; j++) {
                int char_to_pick = (int) Math.floor(Math.random() * (alphabet.length() - 1));
                points.add(new CharacterPoint(i,j,alphabet.charAt(char_to_pick)));
            }
        }
        points.forEach((cp)->{
            findNeighboursForPoint(cp);
        });
        BoggleView.bv.drawWords(points);
        PathList pl = calculatePaths(points);
        for(String w: pl){
            System.out.println(w);
        }
        System.out.println(pl.size());
        
        //findWords(pl);
    }
    private PathList calculatePaths(ArrayList<CharacterPoint> points){
        String word;
        ArrayList<String> words = new ArrayList();
        
        try {
            while((word = br.readLine()) != null){  // O(w)
                words.add(word);
            }
            PathList pl = new PathList(words);      // 1
            for(CharacterPoint cp: points){         // Loop itself is O(n)
                pl.makePathsForPointAndCompare(cp, new ArrayList<CharacterPoint>());
            }
            return pl;
        } catch (IOException ex) {
            Logger.getLogger(BoggleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private void findNeighboursForPoint(CharacterPoint p){
        int x = p.getX();
        int y = p.getY();
        Properties n = p.getFriends();
        if(x != 0){
            n.put("W", findPoint(x-1,y));
            if(y != 0)
                n.put("NW", findPoint(x-1, y-1));
            if(y < Boggle.y - 1)
                n.put("SW", findPoint(x-1,y+1));
        }
        if(x != Boggle.x - 1){
            n.put("E", findPoint(x+1, y));
            if(y < Boggle.y - 1){
                n.put("SE", findPoint(x+1, y+1));
            }
            if(y != 0){
                n.put("NE", findPoint(x+1, y-1));
            }
        }
        if(y != 0)
            n.put("N", findPoint(x, y-1));
        if(y != Boggle.y - 1)
            n.put("S", findPoint(x, y+1));
    }
    private CharacterPoint findPoint(int x, int y){
        for(CharacterPoint cp: points){
           if(cp.getX() == x && cp.getY() == y)
                return cp; 
        }
        return null;
    }
    /*private void findWords(PathList pl){
        HashSet<String> words_found = new HashSet();
        try {
            
            ArrayList<Thread> threads = new ArrayList();
            ArrayList<WordFinder> finders = new ArrayList();
            
            int amount = Boggle.x * Boggle.y;
            int step = words.size() / amount;
            for (int i = 0; i < amount; i++) {
                WordFinder wf = new WordFinder(words, i*step, (i+1)*step, pl);
                Thread t = new Thread(wf);
                t.start();
                threads.add(t);
                finders.add(wf);
            }
            for(Thread t : threads){
                t.join();
            }
            for(WordFinder f : finders){
                for(String word_in_finder : f.words_found){
                    words_found.add(word_in_finder);
                }
            }
        } catch (InterruptedException ex) {
            System.out.println("Something went wrong threading the find clause");
        }
        System.out.println("We found the following words on the board:");
        for(String word: words_found){
            System.out.println(word);
        }
*/
    
}
class WordFinder implements Runnable{
    private ArrayList<String> words;
    public HashSet<String> words_found = new HashSet();
    private int start;
    private int finish;
    private PathList paths;
    public WordFinder(ArrayList<String> words, int start, int finish, PathList paths){
        this.words = words;
        this.start = start;
        this.finish = finish;
        this.paths = paths;
    }
    @Override
    public void run() {
        for (int i = start; i < finish; i++) {
            String word = words.get(i);
            word = word.toUpperCase();
            for(String path: paths){
                if(path.contains(word)){
                    // BOGGLE
                    words_found.add(word);
                }
            }
        } 
    }

}