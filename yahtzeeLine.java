/**
 *This class handles any function related to the yahtzee line of score card, such as calculating,updating and storing.
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
import java.util.Collections;

/**
*class function: This yahtzeeLine class class when initialize sets, calculates, updates, print and reset scores related to the lower score card yahtzee line of the score card. 
*/
public class yahtzeeLine{
	LinkedHashMap<String,Integer>labelAndValues = new LinkedHashMap<String,Integer>();
    LinkedHashMap<String,Integer>spotTaken = new LinkedHashMap<String,Integer>();
	
    //constructor
    public yahtzeeLine(){
    	String label = "Yahtzee line";
    	labelAndValues.put(label,0);
    	spotTaken.put(label,0);
    }
    
    /** 
	*getScore gets the current score of yahtzee line
	*
	*@param: linkedhash map to store value into
	*@return:-
	*/
    void getScore(LinkedHashMap<String,Integer>values){
    	for (Map.Entry<String,Integer>entry:labelAndValues.entrySet()) {
            // using put method to copy one Map to Other
            values.put(entry.getKey(),entry.getValue());
        }
    }
    
    /** 
	*calculate score calculates the score for the yahtzee line based on current dies roll
	*
	*@param: linkedhash map to store the latest calculated score, array list of dice values integer, number of sides and number of dice
	*@return: -
	*/
    void calculateScore(LinkedHashMap<String,Integer>values,ArrayList<Integer>dieValues, int numSides, int numDies){
    	String key = "Yahtzee line";
    	int yahtzeeScore = 50;
		if((maxOfAKindFound(dieValues,numSides,numDies) >= numDies)&&(spotTaken.get(key) == 0)){
    		values.put(key,yahtzeeScore);
		}	
		else{
			values.put(key,labelAndValues.get(key));
		}
    }
    
    /** 
	*setScore sets the current line's value that user wishes to store the value
	*
	*@param: string key of the name of line and all of its value
	*@return: -
	*/
    void setScore(String key,LinkedHashMap<String,Integer>values){
    	if((labelAndValues.containsKey(key))&&(spotTaken.get(key) != 1)){
    		labelAndValues.replace(key,values.get(key));
    		spotTaken.replace(key,1);
    	}	
    }
    
    /**
	*maxOfAKindFound function checks for the count of the dice value occurring most in the hand and return it not the value of the dice
	*
	*@param: array list of dice values integers, number of sides of a dice and number of dice
	*@return: returns count of the dice value, not the value
	*/
	int maxOfAKindFound(ArrayList<Integer>values, int numSides, int numDice){
		//sort dice values first
		Collections.sort(values);
		
		//initialize variables first
    	int maxCount = 0;
    	int currentCount= 0;
    
    	for(int dieValue = 1; dieValue <= numSides; dieValue++){
        	currentCount = 0;
        	for(int diePosition = 0; diePosition < numDice; diePosition++){
            	if(values.get(diePosition) == dieValue){
                	currentCount++;
                }
        	}
        	if (currentCount > maxCount){
            	maxCount = currentCount;
    		}
    	}
    	return maxCount;
	}
	
	
    
}
