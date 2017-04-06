/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import boggle.Boggle;
import java.util.Properties;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

/**
 *
 * @author Wouter
 */
public class CharacterPoint{
    private char c;
    private int x;
    private int y;
    private Properties neighbours = new Properties();
    private Label characterLabel;
    private int red = 240;
    private int green = 100;
    private int blue = 100;
    
    public CharacterPoint(int x, int y, String s){
        this.x = x;
        this.y = y;
        this.c = s.charAt(0);
        makeLabel();
    }
    public CharacterPoint(int x, int y, char c){
        this.x = x;
        this.y = y;
        this.c = c;
        makeLabel();
    }
    
    private void makeLabel(){
        Label l = new Label(Character.toString(getCharacter()));
        l.setMinWidth(Boggle.pixels);
        l.setMinHeight(Boggle.pixels);
        l.setAlignment(Pos.CENTER);
        characterLabel = l;
    }
    public Label getLabel(){
        return characterLabel;
    }
    public void color(){
        red = new Double(red * 0.85).intValue();
        green = new Double(green * 0.85).intValue();
        blue = new Double(blue * 0.85).intValue();
        Platform.runLater(()->{
            characterLabel.setStyle("-fx-background-color:rgb("+red+","+green+","+blue+"); -fx-text-fill:#FFF");
        });
        
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
