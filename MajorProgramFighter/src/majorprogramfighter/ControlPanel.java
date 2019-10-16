/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package majorprogramfighter;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 *
 * @author wedun
 */
public class ControlPanel extends HBox{
    private Button startGame;//declares button to start the game
    private Button restartGame;//declare button to restart the game
    private Button exitGame;//declare button to exit the game
    
    public ControlPanel(GameInterface gameInterface){
        startGame = new Button("Start Game");//instantiates start game button
        restartGame = new Button("Restart Game");//instantiates restart game button
        exitGame = new Button("Exit Game");//instantiates exit game button
        
        startGame.setPrefWidth(145);//sets the width of game buttons
        restartGame.setPrefWidth(155);//sets the width of game buttons
        exitGame.setPrefWidth(145);//sets the width of game buttons
        
        startGame.setFocusTraversable(false);//prevents focus
        restartGame.setFocusTraversable(false);//prevents focus
        exitGame.setFocusTraversable(false);//prevents focus
        
        this.setStyle("-fx-background-color: #000000;");//set the background color of pane
        this.getChildren().addAll(startGame,restartGame,exitGame);//adds buttons to HBox
        this.setSpacing(378);//sets spacing between buttons
        gameInterface.setBottom(this);//adds HBox to game pane
    }
    
    public Button getStartGame(){
        return startGame;
    }
    public Button getRestartButton(){
        return restartGame;
    }
    public Button getExitGame(){
        return  exitGame;
    }
    
}
