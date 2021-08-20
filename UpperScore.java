/**
 *This class handles any function related to upper scoring of score card, such as calculating,updating and storing.
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

/**
*class function: This upperScore class when initialize sets, calculates, updates, print and reset scores related to the upper score card of the score card. It's number of rows is dynamic and changes accordingly to the number of sides of the game. 
*It also keeps track if user has already decided to save score at the upper score card lines
*/
public class UpperScore{
	LinkedHashMap<String,Integer>labelAndValues = new LinkedHashMap<String,Integer>();
	LinkedHashMap<String,Integer>spotTaken = new LinkedHashMap<String,Integer>();
	
    //constructor
    public UpperScore(int numSides){
    	for(int i=0;i < numSides;i++){
    		String label = Integer.toString(i+1) + " line";
    		labelAndValues.put(label,0);
    		spotTaken.put(label,0);
    	}
    	labelAndValues.put("BONUS LINE",0);
    	spotTaken.put("BONUS LINE",0);
    }
    
    /** 
	*getScores gets the current score of all the upper score line
	*
	*@param: linkedhash map to store value
	*@return:-
	*/
    void getScores(LinkedHashMap<String,Integer>values){
    	for (Map.Entry<String,Integer>entry:labelAndValues.entrySet()) {
            // using put method to copy one Map to Other
            values.put(entry.getKey(),entry.getValue());
        }
    }
    
    /** 
	*calculate score calculates the score for the all the upper scorecard based on current dies roll
	*
	*@param: linkedhash map to store the latest calculated score, array list of dice values integer, number of sides and number of dice
	*@return: -
	*/
    void calculateScores(LinkedHashMap<String,Integer>values,ArrayList<Integer>dieValues, int numSides, int numDies){
    	for(int dieValue = 1; dieValue <= numSides; dieValue++){
    		int currentCount = 0;
		    for(int diePosition = 0; diePosition < numDies; diePosition++){
		        if(dieValues.get(diePosition) == dieValue){
		          	currentCount++;
		        }
		    }
		    
		    String key = Integer.toString(dieValue) + " line";
		    int valueCalculated = currentCount * dieValue;
		    
		    if((spotTaken.containsKey(key))&&(spotTaken.get(key) != 1)){
		    	values.put(key,valueCalculated);
		    }
		    else if((spotTaken.containsKey(key))&&(spotTaken.get(key) == 1)){
		    	values.put(key,labelAndValues.get(key));
		    }
		}
		
		//BONUS LINE
		String bonus = "BONUS LINE";
		int bonusVal = 35;
		
		if((spotTaken.containsKey(bonus))&&(spotTaken.get(bonus) != 1)){
			if(totalAllDice(dieValues, numDies) > 63){
		    	values.put(bonus,bonusVal);
		    }
		    else{
		    	values.put(bonus,0);
		    }
		}
		else if((spotTaken.containsKey(bonus))&&(spotTaken.get(bonus) == 1)){
		    values.put(bonus,labelAndValues.get(bonus));
		}
    }
    
    /** 
	*setScore sets the current line's value that user wishes to store the value
	*
	*@param: string key of the name of line and all of its value
	*@return: -
	*/
    void setScores(String key,LinkedHashMap<String,Integer>values){
    
    	if((labelAndValues.containsKey(key))&&(spotTaken.get(key) != 1)){
    		labelAndValues.replace(key,values.get(key));
    		spotTaken.replace(key,1);
    	}
    }
    
    /**
	*totalAllDice function adds up all dice in a hand and returns total value 
	*
	*@param: array list of dice values , number of dice
	*@return: returns the total value of all the dice
	*/
	
	int totalAllDice(ArrayList<Integer>values, int numDice){
	//initialize variable
    	int total = 0;
    	
    	for(int diePosition = 0; diePosition < numDice; diePosition++){
        	total += values.get(diePosition); //add up all values in the dice
    	}
    	
    	return total;
	}
	
}    
    
