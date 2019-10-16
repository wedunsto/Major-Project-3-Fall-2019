/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package majorprogramfighter;

import java.io.FileInputStream;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

/**
 *
 * @author wedun
 */
public class ComputerControlledFighter extends Assets{
    private Image fighter;
    private Rectangle2D viewRectangle2D;
    private double originalPosition;
    private int movement;
    private int attack;
    
    public ComputerControlledFighter(FightingStage fightingStage){
        super(40.0,0.0,0.0,fightingStage.getPrefWidth(),fightingStage.getPrefHeight());
        try {
            String fileLocation = "Assets/VegetaAssets.gif";//name of the file holding the fighter image
            fighter = new Image(new FileInputStream(fileLocation));//saves the fighter image to the fighter variable
            this.setImage(fighter);//sets the fighter image
            fightingStage.getChildren().add(this);
        } catch (java.io.FileNotFoundException ex) {
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        viewRectangle2D = new Rectangle2D(234,63,43,80);
        this.setViewport(viewRectangle2D);
        originalPosition = (getParentWidth()/2-getBoundsInParent().getWidth())+100;
        this.setScaleX(2);
        this.setScaleY(2);
        setX(originalPosition);
        setY(getParentHeight()-getBoundsInParent().getHeight()-40);
    }
    
    public double getOriginalPosition(){
        return originalPosition;
    }
    
    public void verifyFighter(){
        this.setViewport(viewRectangle2D);
    }
    
    public void movement(){
        movement = (int)Math.round(Math.random());
        if(movement<=0){
            this.setDirection(0.0);
            move();
        }
        else{
            this.setDirection(180.0);
            move();
        }
    }
    
    public void performAttack(){
        attack = (int)Math.round(Math.random());
        if(attack<=0){
            Rectangle2D attackRectangle2D = new Rectangle2D(139,313,76,77);
            this.setViewport(attackRectangle2D);
        }
        else{
            Rectangle2D attackRectangle2D = new Rectangle2D(59,388,67,74);
            this.setViewport(attackRectangle2D);
        }
    }
    
    @Override
    public void move(){
        double newX = this.getX() + getSpeed() * Math.cos(Math.toRadians(getDirection()));//determines direct and speed when GameObjects move horizontally
          if(newX >0 & newX <getParentWidth()-getFitWidth()){//if the x value of the new position is greater than
           this.setX( newX );//continue to set x position
          }
          else if(newX < 0){//if the x value is less than 
           this.setX(0);//statically set x value to the smallest value allowed
          }
          else if(newX > getParentWidth()){//statically set x value to the largest value allowed
           this.setX(getParentWidth());//statically set x value to the largest value allowed
          }
    }
}
