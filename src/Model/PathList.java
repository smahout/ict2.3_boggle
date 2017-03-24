/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author Wouter
 */
public class PathList extends ArrayList<String>{
    public PathList(){}
    
    public void makePathsForPoint(CharacterPoint p, ArrayList<CharacterPoint> points_passed){
        ArrayList<CharacterPoint> new_points = (ArrayList<CharacterPoint>) points_passed.clone();
        new_points.add(p);
        Properties friends = p.getFriends();
        String[] locations = "N NE E SE S SW W NW".split(" ");
        boolean found_someplace = false;
        for(String loc: locations){
            CharacterPoint friend = (CharacterPoint) friends.get(loc);
            if(friend != null){
                if(!new_points.contains(friend)){
                    found_someplace = true;
                    makePathsForPoint(friend, new_points);
                }
            }
        }
        if(!found_someplace){
            String path = "";
            for(CharacterPoint cp: new_points){
                path = path + cp.getCharacter();
            };
            add(path);
        }
    }
}
