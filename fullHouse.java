/**
 *This class handles any function related to the full House line of score card, such as calculating,updating and storing.
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
*class function: This fullHouse class when initialize sets, calculates, updates, print and reset scores related to the lower score card of the score card. 
*/
public class fullHouse{
	LinkedHashMap<String,Integer>labelAndValues = new LinkedHashMap<String,Integer>();
    LinkedHashMap<String,Integer>spotTaken = new LinkedHashMap<String,Integer>();
	
    //constructor
    public fullHouse(){
    	String label = "Full House line";
    	labelAndValues.put(label,0);
    	spotTaken.put(label,0);
    }
    
    /** 
	*getScore gets the current score of full house line
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
	*calculate score calculates the score for the full house line based on current dies roll
	*
	*@param: linkedhash map to store the latest calculated score, array list of dice values integer, number of sides and number of dice
	*@return: -
	*/
    void calculateScore(LinkedHashMap<String,Integer>values,ArrayList<Integer>dieValues, int numSides, int numDies){
    	String key = "Full House line";
    	int fullscore = 25;
    	if((fullHouseFound(dieValues,numSides,numDies) == true)&&(spotTaken.get(key) == 0)){
    		values.put(key,fullscore);
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
	*fullHouseFound function checks if the set of dices values is a full house or not
	*
	*@param: array list of dice values integer, number of sides and number of dice
	*@return: true if it is a full house, and false otherwise
	*/
	
	boolean fullHouseFound(ArrayList<Integer>values, int numSides, int numDice){
		//initialize variables
    	boolean foundFH = false;
    	boolean found3K = false;
    	boolean found2K = false;
    	int currentCount = 0;
    	int fiveK = 5;
    	int threeK = 3;
    	int twoK = 2;
    	
    	//sort dice values first
		Collections.sort(values);
    	
    	for(int dieValue = 1; dieValue <= numSides; dieValue++){
        	currentCount = 0;
        	for(int diePosition = 0; diePosition < numDice; diePosition++){
            	if(values.get(diePosition) == dieValue){
                	currentCount++;
            	}
        	}
        	
        	if(currentCount >= fiveK){
            	foundFH = true;
            	break;
            }
        	if(currentCount >= threeK){
            	found3K = true;
            }
        	else if(currentCount >= twoK){
            	found2K = true;
            }
    	}
    	
    	if(found2K && found3K){
        	foundFH = true;
        }
    	return foundFH;
	}
    
}
