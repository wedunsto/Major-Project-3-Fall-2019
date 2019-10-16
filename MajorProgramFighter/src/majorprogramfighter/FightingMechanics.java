/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package majorprogramfighter;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author wedun
 */
public class FightingMechanics extends GridPane{
    private static GameInterface gameInterface;
    private FightingStage fightingStage;
    private static Rectangle playerHealth;
    private static Rectangle cpuHealth;
    private static int originalHealth;
    private static int originalTimer;
    private static TextField timer;
    private static Label playerName;
    private static Label cpuName;
    private CountDown countDown;
    private int timerValue;
    
    public FightingMechanics(FightingStage fightingStage, GameInterface gameInterface){
        originalTimer =99;
        timerValue = originalTimer;
        timer = new TextField();
        timer.setAlignment(Pos.CENTER);
        timer.setFocusTraversable(false);
        timer.setText(Integer.toString(timerValue));
        originalHealth=400;
        this.gameInterface = gameInterface;
        playerHealth = new Rectangle(0,0,originalHealth,30);
        playerHealth.setFocusTraversable(false);
        playerHealth.setFill(Color.BLUE);
        cpuHealth = new Rectangle (originalHealth,0,originalHealth,30);
        cpuHealth.setFocusTraversable(false);
        cpuHealth.setFill(Color.BLACK);
        playerName = new Label("Broly");
        cpuName = new Label("Vegeta");
       
        this.add(playerName,0,0);
        this.add(playerHealth,0,1);
        this.add(timer,1,0);
        this.add(cpuName,2,0);
        this.add(cpuHealth,2,1);
        this.setHgap(120);
        gameInterface.setTop(this);
        countDown=new CountDown();
        this.fightingStage=fightingStage;
    }
    
    public static void attackTheEnenmy(Assets playerControlledFighter, Assets computerControlledFighter, Rectangle playerDamage){
        if(playerControlledFighter.getBoundsInParent().intersects(computerControlledFighter.getBoundsInParent())){
            playerDamage.setWidth(playerDamage.getWidth()-10);
        }
    }
    
    public void tooClose(Assets playControlledFighter, Assets computerControlledFighter){//prevent fighters from passing through each other
        if(playControlledFighter.getBoundsInParent().intersects(computerControlledFighter.getBoundsInParent())){
            playControlledFighter.setX(playControlledFighter.getX()-10);
            computerControlledFighter.setX(computerControlledFighter.getX()+10);
        }
    }
    
    public Rectangle getPlayerHealth(){
        return playerHealth;
    }
    public Rectangle getCPUHealth(){
        return cpuHealth;
    }
    public void resetHealth(){
        this.playerHealth.setWidth(originalHealth);
        this.cpuHealth.setWidth(originalHealth);
    }  
    
    public void startCountDown(){
        countDown.start();
    }
    public void stopCountDown(){
        countDown.stop();
    }
    public void resetTimer(){
        this.timerValue=originalTimer+1;
    }
    
    public int getTimerValue(){
        return timerValue;
    }
    
    private class CountDown extends AnimationTimer{
        long countPrevious=0;
        @Override
        public void handle(long now) {
            if(countPrevious==0){
                countPrevious=now;
            }
            if(now-countPrevious>1000000000){
                timerValue--;
                timer.setText(Integer.toString(timerValue));
                countPrevious=now;
            }
        }
        
    }
    
}
