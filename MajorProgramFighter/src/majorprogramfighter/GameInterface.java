/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package majorprogramfighter;

import java.util.Optional;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author wedun
 */
public class GameInterface extends BorderPane{
    private PlayerControlledFighter USERFighter;
    private ComputerControlledFighter CPUFighter;
    private FightingStage fightingStage;
    private gameInterfaceHandler keyEvent;
    private FighterAnimation fighterAnimation;
    private CPUMove cPUMove;
    private CPUFight cPUFight;
    private CPUReset cPUReset;
    private FightingMechanics fightingMechanics;
    private ControlPanel controlPanel;
    private Rectangle cpuHealth;
    private Rectangle playerHealth;
    
    public GameInterface(){
        fightingStage = new FightingStage(this);
        USERFighter = new PlayerControlledFighter(fightingStage);
        CPUFighter = new ComputerControlledFighter(fightingStage);
        keyEvent = new gameInterfaceHandler();
        fighterAnimation = new FighterAnimation();
        cPUMove = new CPUMove();
        cPUFight = new CPUFight();
        cPUReset = new CPUReset();
        fightingMechanics = new FightingMechanics(fightingStage,this);
        controlPanel = new ControlPanel(this);
        cpuHealth =fightingMechanics.getCPUHealth();
        playerHealth=fightingMechanics.getPlayerHealth();
        
        ButtonFTN();
    }
    
        public void cpuStart(){
        cPUMove.start();
        cPUFight.start();
        cPUReset.start();
    }
    public void cpuStop(){
        cPUMove.stop();
        cPUFight.stop();
        cPUReset.stop();
    }
    
       public void ButtonFTN(){
        controlPanel.getStartGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                USERFighter.setSpeed(10.0);
                getPlayerControlledFighter().setOnKeyPressed(keyEvent);
                fighterAnimation.start();
                cpuStart();
                fightingMechanics.startCountDown();
            }
        });
        
        controlPanel.getRestartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               cpuStop();
               USERFighter.setSpeed(0.0);
               USERFighter.setX(USERFighter.getOriginalPosition());
               USERFighter.verifyFighter();
               CPUFighter.setX(CPUFighter.getOriginalPosition());
               CPUFighter.verifyFighter();
               fightingMechanics.resetHealth();
               fightingMechanics.stopCountDown();
               fightingMechanics.resetTimer();
               fighterAnimation.stop();
            }
        });
        
        controlPanel.getExitGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cpuStop();
                USERFighter.setSpeed(0.0);
                CPUFighter.setSpeed(0.0);
                fightingMechanics.stopCountDown();
                fighterAnimation.stop();
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Existing 2D Fighter");
                alert.setHeaderText("Last Chance");
                alert.setContentText("Are you sure?");
                
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    System.exit(-1);
                }
                else{
                    USERFighter.setSpeed(10.0);
                    CPUFighter.setSpeed(10.0);
                    fighterAnimation.start();
                }

            }
        });
    }
       
    public void gameOver(){
        if((cpuHealth.getWidth() < 1)||(playerHealth.getWidth()<1)){
            cpuStop();
            fightingMechanics.stopCountDown();
            fighterAnimation.stop();
            if(cpuHealth.getWidth() < playerHealth.getWidth()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Player Wins!");
                alert.setHeaderText("Congradulations!");
                alert.setContentText("Click OK to exit the game or reset the game!");
                alert.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Player Loses!");
                alert.setHeaderText("Good luck next time");
                alert.setContentText("Click OK to exit the game and Cancel to be able to reset!");
                alert.show();
            }
        }
        
        if(fightingMechanics.getTimerValue()<=0){
            cpuStop();
            fightingMechanics.stopCountDown();
            fighterAnimation.stop();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Time is up!");    
            alert.setHeaderText("Defeat your enemy within the given time!");    
            alert.setContentText("Click OK to exit the game or reset the game!");
            alert.show();
        }
    }
    
    private class gameInterfaceHandler implements EventHandler<KeyEvent>{

        @Override
        public void handle(KeyEvent event) {
            switch(event.getCode()){
                case LEFT:
                    USERFighter.setDirection(180.0);
                    USERFighter.move();
                    break;
                case RIGHT:
                    USERFighter.setDirection(0.0);
                    USERFighter.move();
                    fightingMechanics.tooClose(USERFighter,CPUFighter);
                    break;
                case SPACE:
                    USERFighter.fight();
                    fightingMechanics.attackTheEnenmy(USERFighter,CPUFighter,cpuHealth);
                    break;
            }
        }   
    }
    
    private class FighterAnimation extends AnimationTimer{
        long playerPrevious = 0;
        @Override
        public void handle(long now) {
            if((playerPrevious==0)){
                playerPrevious=now;
            }
          
            else if((int)(now-playerPrevious)/(1e9) > 0.75){
                USERFighter.verifyFighter();
                gameOver();
                playerPrevious=now;
            } 
        }
    }
    
    private class CPUMove extends AnimationTimer{
        long move=0;
        @Override
        public void handle(long now) {
            if(move==0){
                move=now;
            }
            else if((int)(now-move)/(1e9)>1){
                CPUFighter.movement();
                fightingMechanics.tooClose(USERFighter,CPUFighter);
                move=now;
            }
        } 
    }
    
    private class CPUFight extends AnimationTimer{
        long fight=0;
        @Override
        public void handle(long now) {
            if(fight==0){
                fight=now;
            }
            else if((int)(now-fight)/(1e9)>2){
                CPUFighter.performAttack();
                fightingMechanics.attackTheEnenmy(CPUFighter,USERFighter,playerHealth);
                gameOver();
                fight=now;
            }
        }
    }
    
    private class CPUReset extends AnimationTimer{
        long reset=0;
        @Override
        public void handle(long now) {
            if(reset==0){
                reset=now;
            }
            else if((int)((now-reset)/(1e9))>2.5){
                CPUFighter.verifyFighter();
                reset=now;
            }
        }
    }
    
    public FightingStage getFightingStage(){
        return this.fightingStage;
    }
    public PlayerControlledFighter getPlayerControlledFighter(){
        return USERFighter;
    }
}
