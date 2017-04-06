/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boggle;
import Controllers.BoggleController;
import Model.PathList;
import View.BoggleView;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
/**
 *
 * @author Wouter
 */
public class Boggle{
    public static final int x = 16;
    public static final int y = 16;
    // 4 bij 4 duurde enorm lang. Kennelijk verkeerde structuur gepakt.
    public static final int pixels = 50;
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Thread t = new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    new BoggleController();
                } catch (InterruptedException ex) {}
            }
        });
        t.start();
        Application.launch(BoggleView.class, args);
        
    }
    
}
