/**
 *This class handles the overall management of scorecard. It creates an upper and lower scorecard to manage the flow of calculating scores, determining the line of the score to manage function related to scoring and saving the value. It is initialized as an object in Game so that game can make use of this functions. Game has a score card. If you want to run this program, use-> java .Main, where  is the package for all the class
 *CPSC 224-02, Spring 2021
 *Programming Assignment #1
 *No sources to cite.
 *
 *@author Lin Ai
 *@version v1.0
*/

//Score is also in hw package
package hw;

//import Java libraries
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.LinkedHashMap;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;

//import personal class
import hw.UpperScore;
import hw.LowerScore;

/**
*This Score class stores the score of each player of the game. It contains function such as printing the scoring as well as setting the scores for each player
*
*@param: no param
*@return: no return
*/
public class Scorecard{
	private UpperScore upperScores;
	private LowerScore lowerScores;
	int numSide = 0;
	int numDies = 0;
    int numScores = 0;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    LinkedHashMap<String,Integer>current;
    String userName = "";
    
    //constructor
    public Scorecard(int numDices, int numSides, String name){
    	System.out.println("New scorecard");
    	upperScores = new UpperScore(numSides);
    	lowerScores= new LowerScore(numDices);
    	numSide = numSides;
    	numDies = numDices;
    	//numScores = numSides + (numDices-3) + 6;
    	userName = name;
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
    
    /** 
	*getScores gets the current score of the whole scorecard
	*
	*@param: -
	*@return:linkedhash map of key value pairs of scores and labels
	*/
    LinkedHashMap<String,Integer> getScores(){
    	LinkedHashMap<String,Integer>scores = new LinkedHashMap<String,Integer>();
    	upperScores.getScores(scores);
    	lowerScores.getScores(scores);
    	
    	return scores;
    }
    
    
    /** 
	*calculate score calculates the score for the whole score
	*
	*@param: array list of dies values
	*@return: -
	*/
    void calculateScores(ArrayList<Integer>diceValues){
    	LinkedHashMap<String,Integer>scores = new LinkedHashMap<String,Integer>();
    	upperScores.calculateScores(scores,diceValues,numSide,numDies);
    	lowerScores.calculateScores(scores,diceValues,numSide,numDies);
    	current = scores;
    	this.pcs.firePropertyChange("scores",0,scores);
	}
	
	/*saveScore saves the value of the scoreline of user's choice
	*
	*@param: string name of score line
	*@return: -
	*/
	void saveScore(String name){
		System.out.println("We are saving it at " + userName + "'s scorecard");
		upperScores.setScores(name,current);
		lowerScores.setScores(name,current);
		this.pcs.firePropertyChange("update",0,1);
   }
   
	/*totalScore calculates the total score of scorecard
	*
	*@param: -
	*@return: total score as integer
	*/   
   	Integer totalScore(){
   	LinkedHashMap<String,Integer>total = new LinkedHashMap<String,Integer>();
   	total = getScores();
   	Integer returnTotal = 0;
   	
   	 for(Map.Entry<String,Integer>entry: total.entrySet()) {
        Integer scoreValue = (Integer)entry.getValue();
   		returnTotal +=scoreValue;
   	}
   	
   	return returnTotal;
   }
   
   /*returnName returns name of scorecard's owner
	*
	*@param: -
	*@return: string name
	*/
   String returnName(){
   		return userName;
   }
   	
}

	

