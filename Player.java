/**
*This class handles the number of players in the game. 
*Its function is to set up scorecard related to number of players 
*Before starting the game, it needs to be setup, which is done in Main.java. If you want to run thi program, use-> java hw.Main, where hw is the package for all the class
*CPSC 224-02, Spring 2021
*Programming Assignment #2
*No sources to cite.
*
*@author Isabells
*@version v1.0 5/6/21
*/

//Config is a package of hw
package hw;

//import java library
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;

import hw.Scorecard;
import hw.ScoreView;
import hw.ScoreControl;

/**
*class function: This Config class when initialize reads and stores the default configurations. It fires off changes to the configView class and other observers when being changed. It is initialize in the Main class when setting up the game
*/
public class Player{
   int numPlayers = 1;
   int numDies = 5;
   int numSide = 6;
   JFrame frame;
   DicesView dicesView;
   SetOfDices dice;
   private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);  
   JPanel playerFrame;  
   ArrayList<String>playerNames = new ArrayList<String>();
   ArrayList<ScoreView>scoreView = new ArrayList<ScoreView>();
   ArrayList<Scorecard>scoreCard = new ArrayList<Scorecard>();
   int index = 0;
   String currName;
   
  //constructor
  public Player(int numPlayer,int numDices,int numSides,SetOfDices dices,JFrame mainFrame,DicesView diceView){
	  	playerFrame = new JPanel();
	  	playerFrame.setLayout(new GridLayout(0, 2));
	  	numPlayers = numPlayer;
	  	numDies = numDices;
	  	numSide = numSides;
	  	frame = mainFrame;
	  	dicesView = diceView;
	  	dice = dices;
	  	
		playerFrame.setVisible(true);
  	    
  }
  
   /*
	* addPropertyChangeListener is to add a listener to this model
    *
    *@param: an object that implements property change listener
    *@return: -
    */
  public void addPropertyChangeListener(PropertyChangeListener listener) {
          this.pcs.addPropertyChangeListener(listener);
    }

	/*
	* removePropertyChangeListener is to remove a listener to this model
    *
    *@param: an object that implements property change listener
    *@return: -
    */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
  
  //set names
  /** 
	*setNames creates the scorecard and associates it with the user names
	*
	*@param: array list of strings
	*@return: -
	*/
  void setNames(ArrayList<String>names){
  		currName = names.get(0);
  		for(int i=0;i<numPlayers;i++){
  			String name = names.get(i);
  			playerNames.add(name);
  			System.out.println(name);
  			
  			Scorecard scores = new Scorecard(numDies,numSide,name);
			ScoreView scoreViews = new ScoreView(scores,dice,playerFrame,name,this);
			ScoreControl scoreController = new ScoreControl(scores,scoreViews,dicesView,this);
			scoreController.setScore();
			scoreView.add(scoreViews);
			scoreCard.add(scores);

  			frame.add(playerFrame,BorderLayout.CENTER);
  			
  			frame.revalidate();
  		}
  		scoreView.get(0).setBorder();
  		
  }
  
  /*getPlayerTurn checks if the current player is who. Return true if the name pass into it matches current player and false otherwise
  *
  *@param: string name
  *@return: boolean true or false
  */
  boolean getPlayerTurn(String name){
  	if(currName == name){
  		return true;
  	}
  	return false;
  }
  
  /*getCurrName return current player's name
  *
  *@param:-
  *@return: string player name
  */
  String getCurrName(){
  	return currName;
  }
  
  /*setCurrName sets current user name
  *
  *@param:-
  *@return:-
  */
  void setCurrName(){
  	System.out.println("Reset name");
  	for(int i=0;i<numPlayers;i++){
  		if(playerNames.get(i) == currName){
  			index = i;
  		}
  	}
  	 scoreView.get(index).unsetBorder();
  	
  	if(index > numPlayers-2){
  		index = 0;
  		currName = playerNames.get(index);
  		scoreView.get(index).setBorder();
  	}
  	else{
  		index++;
  		currName = playerNames.get(index);
  		scoreView.get(index).setBorder();
  	}
  }
  
  /*displayScores display total scores of player
  *
  *@param:-
  *@return:-
  */
  void displayScores(){
  	JPanel panel = new JPanel();
  	Integer highestScore = 0;
  	String winnerName = "";
  	panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
  	
  	for(int i=0;i<numPlayers;i++){
  		Integer total = scoreCard.get(i).totalScore();
 		panel.add(new JLabel(scoreCard.get(i).returnName() + ": " + Integer.toString(total)));
 		if(total > highestScore){
 			highestScore = total;
 			winnerName = scoreCard.get(i).returnName();
 		}
  	}
  	JLabel winner = new JLabel("Winner is " + winnerName);
  	panel.add(winner);
  	JOptionPane.showMessageDialog(null,panel,"GAME ENDS",JOptionPane.INFORMATION_MESSAGE);
  }
  
  
}
