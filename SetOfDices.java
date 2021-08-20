/**
 *This class handles any function related to dices, such as rolling the dice values, printing the dice and returning all dice values.
 *It is initialized as an object in Game so that game can make use of this functions. Game has a set of dices. 
 *If you want to run this program, use-> java hw.Main, where hw is the package for all the class
 *CPSC 224-02, Spring 2021
 *Programming Assignment #1
 *No sources to cite.
 *
 *@author Michael Valerino
 *@version v1.0 5/6/21
*/

//SetOfDices is in hw package
package hw;

//import Java libraries
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;

//import libraries
import hw.Die;
import hw.Config;
  
/**
*This Dice class stores the roll of each dice for each turn of the game. It contains function such as printing the roll dice value, roling the dices, resetting the dices and returning all the dices value
* 
*@param: no parameters
*@return: no return
*/

public class SetOfDices{
	//initialize variables
	ArrayList<Die>dices = new ArrayList<Die>();
	//diceValue stores the value for each dice. It is initialized with all 0s at the start of each role
	int numDices = 0;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	//constructor
    public SetOfDices(int numDice) {
    	//set diceValue all to be 0
        for(int i=0;i < numDice;i++){
        dices.add(new Die());
        }
        numDices = numDice;
        
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
	*rollDice rolls and set value to the dices. Stores this value in the arraylist diceValue
	*
	*@param: number of face of each die
	*@return: no return
	*/
	void rollDice(int faces){
		ArrayList<Integer>diceValues = new ArrayList<Integer>();
		for(int i=0;i<numDices;i++){
			int val = dices.get(i).roll(faces);
			diceValues.add(val);
		}
		//this.pcs.firePropertyChange("dices",0,1);
		this.pcs.firePropertyChange("dices",0,diceValues);	
	}
	
	
	/*
	* getDieAt returns a particular die
    *
    *@param: index of die wanted as an integer
    *@return: die object
    */
	Die getDieAt(int i){
		if(i>=0 && i < numDices){
			return dices.get(i);		
		}
		return dices.get(0);
	}

	/**
	*getAllValues returns all dice values as an array list
	*
	*@param: no parameters
	*@return: dice values as an array list
	*/	
	ArrayList<Integer> getAllValues(){
		ArrayList<Integer>diceValues = new ArrayList<Integer>();
		
		for(int i=0;i< numDices;i++){
			diceValues.add(dices.get(i).getValue());
		}
		return diceValues;
	} 
}
	
	
	
	
	
