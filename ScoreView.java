/**
 *This file setups the view of the score card. It displays the current scoring of the game and buttons for users to interact with the score card. If you want to run this program, use-> java hw.Main, where hw is the package for all the class
 *CPSC 224-02, Spring 2021
 *Programming Assignment #1
 *No sources to cite.
 *
 *@author Lin Ai
 *@version v1.0 5/6/21
*/

//Score view is a package in hw 

package hw;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;

import hw.Scorecard;
import hw.SetOfDices;

// ScoreView setups the score card. It contains radio buttons with labels for their names like 3 of a kind, full house etc, and also the current scoring of the game depending on the dice rolled values and also the values user wish to store
public class ScoreView implements PropertyChangeListener {
 	JPanel scorePanel = new JPanel();
 	ArrayList<JLabel> scoresLabel = new ArrayList<JLabel>();
 	JButton scoreButton;
 	ButtonGroup group = new ButtonGroup();
 	Scorecard newScores;
	SetOfDices dices;
 	Player currPlayer;
 	String playerName;
 	JLabel totalScoreLabel;
 	JLabel totalScore;
 	
 	//constructor
    public ScoreView(Scorecard scores, SetOfDices newDices,JPanel playerFrame, String userName, Player newPlayer){
 		 scores.addPropertyChangeListener(this);
 		 newDices.addPropertyChangeListener(this);
 		 newScores = scores;
 		 LinkedHashMap<String,Integer>allScores;
 		 dices = newDices;
 		 currPlayer = newPlayer;
 		 
 		 scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.PAGE_AXIS)); // create a score panel to be added to main frame
 		 allScores = newScores.getScores();
 		 JLabel name = new JLabel(userName);
 		 name.setFont(new Font("Serif", Font.PLAIN, 17));
 		 
 		 Color myColor = new Color(255, 255, 224);
		 scorePanel.setBackground(myColor);
 		 
 		 scorePanel.add(name);
 		 
 		 JPanel content1 = new JPanel(new GridLayout(0,2));
 		 for(Map.Entry<String,Integer>entry: allScores.entrySet()) {
            String key = (String)entry.getKey();
            Integer scoreValue = (Integer)entry.getValue();
            
            JLabel scoreValueLabel = new JLabel(Integer.toString(scoreValue));
            scoreValueLabel.setFont(new Font("Serif", Font.PLAIN, 16));
            
            JLabel keyLabel = new JLabel(key);
            keyLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        	JRadioButton newButton = new JRadioButton(keyLabel.getText());
        	newButton.setFont(new Font("Serif", Font.PLAIN, 16));
        	//newButton.setPreferredSize(new Dimension(40, 40));
        	
        	newButton.setBackground(myColor);
        	newButton.setActionCommand(key);
        	newButton.setSelected(false);
        	
        	scoresLabel.add(scoreValueLabel);
        	group.add(newButton);
        	 
        	content1.add(newButton);
        	content1.add(scoreValueLabel);
        	scorePanel.add(content1);
        }
        totalScoreLabel = new JLabel("Total Score");
        totalScoreLabel.setFont(new Font("Serif", Font.PLAIN, 16));
		totalScore = new JLabel(Integer.toString(scores.totalScore()));
        totalScore.setFont(new Font("Serif", Font.PLAIN, 16));
		content1.add(totalScoreLabel);
        content1.add(totalScore);
        	
		content1.setBackground(myColor);
        
        scorePanel.add(scoreButton = new JButton("Set Score!"));
        scorePanel.setVisible(true);
        playerFrame.add(scorePanel);
        playerName = userName;
    }
    
   /*setBorder sets the scorecard border to red to signify current user's turn
	*
	*@param: -
	*@return: -
	*/ 
   public void setBorder(){
    	scorePanel.setBorder(BorderFactory.createLineBorder(Color.RED,4));

    }
    
    /*unsetBorder unsets the scorecard border from red to transparent
	*
	*@param: -
	*@return: -
	*/ 
    public void unsetBorder(){
    	scorePanel.setBorder(BorderFactory.createEmptyBorder());

    }
    
    /*propertyChange reacts to any changes in model it is listening to accordingly
	*
	*@param: property change event
	*@return: -
	*/ 
    public void propertyChange(PropertyChangeEvent e){
    	String propertyName = e.getPropertyName();
		if ("dices".equals(propertyName)){
			if(currPlayer.getPlayerTurn(playerName) == true){
	    		newScores.calculateScores(dices.getAllValues());
	    	}
	    	else{
	    		int i=0;
	    		unsetBorder();
	    		for(Map.Entry<String,Integer>entry : newScores.getScores().entrySet()) {
					Integer scoreValue = (Integer)entry.getValue();
					scoresLabel.get(i).setText(Integer.toString(scoreValue));
					i++;
				}
	    	}
	    }
	    
	    if ("scores".equals(propertyName)){
			int i=0;
			LinkedHashMap<String,Integer>lscores = new LinkedHashMap<String,Integer>();
			lscores = (LinkedHashMap<String,Integer>)e.getNewValue();
			if(currPlayer.getPlayerTurn(playerName) == true){
				for(Map.Entry<String,Integer>entry : lscores.entrySet()) {
					Integer scoreValue = (Integer)entry.getValue();
					scoresLabel.get(i).setText(Integer.toString(scoreValue));
					i++;
				}
			}	
			else{
	    		i=0;
	    		for(Map.Entry<String,Integer>entry : newScores.getScores().entrySet()) {
					Integer scoreValue = (Integer)entry.getValue();
					scoresLabel.get(i).setText(Integer.toString(scoreValue));
					i++;
				}
	    	}
	    }
	    
	    if ("update".equals(propertyName)){
	    	int i=0;
	    	if(currPlayer.getPlayerTurn(playerName) == true){
	    		LinkedHashMap<String,Integer>lscores = new LinkedHashMap<String,Integer>();
	    		lscores = newScores.getScores();
	    		for(Map.Entry<String,Integer>entry : lscores.entrySet()) {
					Integer scoreValue = (Integer)entry.getValue();
					scoresLabel.get(i).setText(Integer.toString(scoreValue));
					i++;
				}
				totalScore.setText(Integer.toString(newScores.totalScore()));
				currPlayer.setCurrName();
				
			}
			else{
			i=0;
	    		for(Map.Entry<String,Integer>entry : newScores.getScores().entrySet()) {
					Integer scoreValue = (Integer)entry.getValue();
					scoresLabel.get(i).setText(Integer.toString(scoreValue));
					i++;
				}
	    	}
	    }
	    
	}
	
    /*current name returns name of current ower of scorecard
	*
	*@param: -
	*@return: string name
	*/ 
    String currentName(){
    	return playerName;
    }
 	
 	//returns the set score button
 	/*getEtButton returns the set score button
	*
	*@param: -
	*@return: JButton
	*/ 
 	JButton getSetButton(){
 		return scoreButton;
 	}
 	
 	
 	/*getScoreButtons returns all the radio buttons
	*
	*@param: -
	*@return:button group
	*/ 
 	ButtonGroup getScoreButtons(){
 		return group;
 	}
    
 
    
 
}
