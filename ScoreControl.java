/**
 *This file controlls the user interactions with the score card. When user interacts with the score card button, it finds the index that the user wishes to store the score card value at. If you want to run this program, use-> java hw.Main, where hw is the package for all the class
 *CPSC 224-02, Spring 2021
 *Programming Assignment #1
 *No sources to cite.
 *
 *@author Lin Ai
 *@version v1.0 5/6/21
*/

//Score controller is package in hw 

package hw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

import hw.Scorecard;
import hw.ScoreView;


// ScoreController handles the events when set score button has been pressed by getting the index selected and then informs the score object, to be updated and then it tells view to unlock the dices previously selected
public class ScoreControl implements PropertyChangeListener{
	Scorecard scores;
	ScoreView view;
	DicesView diesView;
 	Player player;
 
 	//constructor
	public ScoreControl(Scorecard newScore, ScoreView newView, DicesView newDiesView,Player newPlayer) {
		scores = newScore;
		view = newView;
		diesView = newDiesView;
		player = newPlayer;
 	}
 	
 	/**
	* propertyChange sends message to terminal that score has been set
	*
	*@param: property change event
	*@return: -
	*/
 	public void propertyChange(PropertyChangeEvent e) {
 		System.out.println("score controller confirm twice has been pressed");
 	}
 	
    /**
	* set score sets the score button as a property change listener, so when it is pressed, it finds the radio button(index) that user selects to store the score. Then it updates the score object to update its scoring to store the value being sselected. Then it tells dicesView to unselect the locked dices
	*
	*@param: number of dices and number of sides of the die as integer
	*@return: -
	*/
    public void setScore(){
	 	JButton button = view.getSetButton();
	 	button.addPropertyChangeListener(this);
	 	button.addActionListener(
			new ActionListener() {
			@Override
		    	public void actionPerformed(ActionEvent e) {
		        	System.out.println("scored button pressed");
		 
		        	ButtonGroup buttons = view.getScoreButtons();
		        	String name = "default";
		        	boolean allSet = true;
		        	for (Enumeration<AbstractButton> button = buttons.getElements(); button.hasMoreElements();){
		        		AbstractButton selectButton = button.nextElement();
		        		if(player.getPlayerTurn(view.currentName()) == true){
    						if (selectButton.isSelected()){
        				 		name = selectButton.getText();
        				 		System.out.println(name);
        				 		if(name != "TAKEN"){
        				 			scores.saveScore(name);
        				 			diesView.freeUnlock();
        				 			diesView.setRollsAllowed();
        				 			selectButton.setText("TAKEN");
        				 		}
        				 		else{
        				 			JOptionPane.showMessageDialog(null, "You previously save a score here! Try again!");
        				 		}
        				 	}
        				}
        				if(selectButton.getText() != "TAKEN"){
        				 	allSet = false;
        				}
        			}
        			
        			if(allSet == true){
        				player.displayScores();
        			}
		        	         
		     	}
	 		}        
	 	);      
 	}
	 	
}
