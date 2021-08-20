/**
 *This class handles any function related to lower scoring of score card, such as calculating,updating and storing.
 *It is initialized as an object in Scorecard so that score can make use of this functions. 
 *Score has a upper score card. If you want to run this program, use-> java .Main, where  is the package for all the class
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
import java.util.LinkedHashMap;
import java.util.Map;

//import personal class
import hw.threeAboveKind;
import hw.fullHouse;
import hw.maxStraight;
import hw.yahtzeeLine;


/**
*class function: This upperScore class when initialize sets, calculates, updates, print and reset scores related to the lower score card of the score card. It's number of rows is dynamic and changes accordingly to the number of sides of the game. 
*It also keeps track if user has already decided to save score at the upper score card lines
*/
public class LowerScore{
	private threeAboveKind aKind;
	private fullHouse fH;
	private maxStraight straight;
	private yahtzeeLine yahtzee;
	
    //constructor
    public LowerScore(int numDice){
    	aKind = new threeAboveKind(numDice);
    	fH = new fullHouse();
    	straight = new maxStraight();
    	yahtzee = new yahtzeeLine();
    }
    
    /** 
	*getScores gets the current score of all the lower section of scorecard
	*
	*@param: linkedhash map to store value into
	*@return:-
	*/
    void getScores(LinkedHashMap<String,Integer>values){
    	aKind.getScore(values);
    	fH.getScore(values);
    	straight.getScore(values);
    	yahtzee.getScore(values);
    }
    
    /** 
	*calculate scores calculates the score for the lower section of the scorecard
	*
	*@param: linkedhash map to store the latest calculated score, array list of dice values integer, number of sides and number of dice
	*@return: -
	*/
    void calculateScores(LinkedHashMap<String,Integer>values,ArrayList<Integer>dieValues, int numSides, int numDies){
    	aKind.calculateScore(values,dieValues,numSides,numDies);
    	fH.calculateScore(values,dieValues,numSides,numDies);
    	straight.calculateScore(values,dieValues,numSides,numDies);
    	yahtzee.calculateScore(values,dieValues,numSides,numDies);
	
    }
    
    /** 
	*setScores sets the current line's value that user wishes to store the value at lower section of scorecacrd
	*
	*@param: string key of the name of line and all of its value
	*@return: -
	*/
    void setScores(String key,LinkedHashMap<String,Integer>values){
    	aKind.setScore(key,values);
    	fH.setScore(key,values);
    	straight.setScore(key,values);
    	yahtzee.setScore(key,values);
    	
    }
}    
    
