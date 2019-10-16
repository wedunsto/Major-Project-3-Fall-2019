/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package majorprogramfighter;

import java.io.FileInputStream;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

/**
 *
 * @author wedun
 */
public class FightingStage extends Pane{
    private Image preBackground;
    private BackgroundImage postBackground;
    private Background trueBackground;
    
    public FightingStage(GameInterface gameInterface){
        this.setPrefWidth(1200);
        this.setPrefHeight(920);
        
        try{
            String fileName = "Assets/BackgroundAsset1.png";
            preBackground = new Image(new FileInputStream(fileName));
            postBackground = new BackgroundImage(preBackground,BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            trueBackground = new Background(postBackground);
            this.setBackground(trueBackground);
        }
        catch(java.io.FileNotFoundException ex){
            System.err.println("file not found");
            System.exit(-1);
        }
        
        gameInterface.setCenter(this);
        
    }
    
}
