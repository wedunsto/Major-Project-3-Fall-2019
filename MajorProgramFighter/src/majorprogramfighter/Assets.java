/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package majorprogramfighter;

import javafx.scene.image.ImageView;

/**
 *
 * @author wedun
 */
public class Assets extends ImageView implements Animation{
    private double speed;//determines how fast the fighter can move left and right
    private double direction;//determines the direction the fighter will move in
    private double power;//determines how much of the health bar each attack takes
    private double parentWidth;//gets the size of the fighting arena
    private double parentHeight;
    
    public Assets(){
        speed=0.0;
        direction=0.0;
        power=0.0;
        parentWidth=0.0;
    }
    
    public Assets(double speed, double direction, double power,double parentWidth, double parentHeight){//fighter specific properties
        this.speed=speed;
        this.direction=direction;
        this.power=power;
        this.parentWidth=parentWidth;
        this.parentHeight=parentHeight;
    }

    @Override
    public void move() {
        double newX = this.getX() + getSpeed() * Math.cos(Math.toRadians(getDirection()));
        if(newX>0 & newX<getParentWidth()){//if the value of newX is in the boundaries of the field
            this.setX(newX);//set the x value to the newX value
        }
        else if(newX<0){//if the edge is met
            this.setX(0);//continue to set the x value to the edge 
        }
        else if(newX>getParentWidth()){//if the edge is met
            this.setX(getParentWidth());//continue to set the x value to the edge
        }
    }
    
    //Getters and Setters
    public void setSpeed(double speed){
        this.speed=speed;
    }
    public double getSpeed(){
        return this.speed;
    }
    
    public void setDirection(double direction){
        this.direction=direction;
    }
    public double getDirection(){
        return this.direction;
    }
    
    public void setPower(double power){
        this.power=power;
    }
    public double getPower(){
        return this.power;
    }
    
    public void setParentWidth(double parentWidth){
        this.parentWidth=parentWidth;
    }
    public double getParentWidth(){
        return parentWidth;
    }
    
    public void setParentHeight(double parentHeight){
        this.parentHeight = parentHeight;
    }
    public double getParentHeight(){
        return parentHeight;
    }
}
