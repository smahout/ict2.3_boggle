/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Properties;

/**
 *
 * @author Wouter
 */
public class CharacterPoint{
    private char c;
    private int x;
    private int y;
    private Properties neighbours = new Properties();
    
    public CharacterPoint(int x, int y, String s){
        this.x = x;
        this.y = y;
        this.c = s.charAt(0);
    }
    public CharacterPoint(int x, int y, char c){
        this.x = x;
        this.y = y;
        this.c = c;
    }
    public void setCharacter(Character c){
        this.c = c;
    }
    public void setCharacter(String s){
        this.c = s.charAt(0);
    }
    public char getCharacter(){
        return this.c;
    }
    public int getY(){
        return this.y;
    }
    public int getX(){
        return this.x;
    }
    public Properties getFriends(){
        return neighbours;
    }
    @Override
    public String toString(){
        return "[x: " + getX() + ", y: " + getY() + ", char: " + c + "]";
    }
    public boolean equals(CharacterPoint cp){
        if(this.x == cp.getX() && this.y == cp.getY()){
            return true;
        }
        else{
            return false;
        }
    }
}
