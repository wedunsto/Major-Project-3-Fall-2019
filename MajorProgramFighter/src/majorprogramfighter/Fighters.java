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
public class Fighters extends Assets{
    private Image fighter;
    private Rectangle2D viewRectangle2D;
    
    public Fighters(){
        try {
            String fileLocation = "fighter.png";//name of the file holding the fighter image
            fighter = new Image(new FileInputStream(fileLocation));//saves the fighter image to the fighter variable
            this.setImage(fighter);//sets the fighter image
        } catch (java.io.FileNotFoundException ex) {
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        viewRectangle2D = new Rectangle2D(0,125,59,86);
        this.setViewport(viewRectangle2D);
        setX(getParentWidth()/2-getBoundsInParent().getWidth());
        setY(0);
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
