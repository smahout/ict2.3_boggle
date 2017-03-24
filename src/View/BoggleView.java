/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.CharacterPoint;
import boggle.Boggle;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Wouter
 */
public class BoggleView extends Application{
    public static BoggleView bv;
    
    private GridPane root;
    @Override
    public void start(Stage primaryStage) {
        BoggleView.bv = this;
        
        root = new GridPane();
        Scene scene = new Scene(root, Boggle.x*Boggle.pixels, Boggle.y*Boggle.pixels);
        
        primaryStage.setTitle("Boggle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void drawWords(ArrayList<CharacterPoint> cp_list){
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                for(CharacterPoint cp: cp_list){
                    Label l = new Label(Character.toString(cp.getCharacter()));
                    l.setMinWidth(Boggle.pixels);
                    l.setMinHeight(Boggle.pixels);
                    l.setAlignment(Pos.CENTER);
                    root.add(l, cp.getX(), cp.getY());
                }
            }
        });
    }
}
