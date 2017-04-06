/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wouter
 */
public class PathList extends HashSet<String>{
    ArrayList<String> words;
    public PathList(ArrayList<String> input){
        words = input;
    }
    
    public void makePathsForPointAndCompare(CharacterPoint p, ArrayList<CharacterPoint> points_passed){
        ArrayList<CharacterPoint> new_points = (ArrayList<CharacterPoint>) points_passed.clone();
        new_points.add(p);
        String path_string = "";
        for(CharacterPoint cp: new_points){ 
            path_string += cp.getCharacter();
        }
        boolean match = false;
        int i = 0;
        path_string = path_string.toLowerCase();
        while(!match && i < words.size() - 1){
            if(words.get(i).startsWith(path_string)){
                match = true;
                if(words.get(i).equals(path_string)){
                    for(CharacterPoint cp: new_points){ // WC: O(nw)
                        cp.color();
                        
                    }
                    try {
                            Thread.sleep(500);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(PathList.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    add(path_string);
                }
            }
            else{
//                System.out.println("-------------------------");
//                System.out.println(path_string);
//                System.out.println(words.get(i));
//                System.out.println("-------------------------");
            }
            i++;
        }
        if(!match){
            return;
        }
        Properties friends = p.getFriends();
        String[] locations = "N NE E SE S SW W NW".split(" ");
        boolean found_someplace = false;
        for(String loc: locations){
            CharacterPoint friend = (CharacterPoint) friends.get(loc);
            if(friend != null){
                if(!new_points.contains(friend)){
                    found_someplace = true;
                    makePathsForPointAndCompare(friend, new_points);
                }
            }
        }
        /*if(!found_someplace){
            String path = "";
            for(CharacterPoint cp: new_points){
                path = path + cp.getCharacter();
            };
            add(path);
        }*/
    }
}
