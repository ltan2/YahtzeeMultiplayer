/**
 *This class handles any function related to the three and above of a kind line of score card, such as calculating,updating and storing.
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
*class function: This threeAboveKind class when initialize sets, calculates, updates, print and reset scores related to the lower score card of the score card. It's number of rows is dynamic and changes accordingly to the number of sides of the game. 
*It also keeps track if user has already decided to save score at the upper score card lines
*/
public class threeAboveKind{
	LinkedHashMap<String,Integer>labelAndValues = new LinkedHashMap<String,Integer>();
    LinkedHashMap<String,Integer>spotTaken = new LinkedHashMap<String,Integer>();
	
    //constructor
    public threeAboveKind(int numDice){
    	int rem = numDice - 3;
    	for(int i=0;i < rem;i++){
    		String label = "The " + Integer.toString(i+3) + " of a kind";
    		labelAndValues.put(label,0);
    		spotTaken.put(label,0);
    	}
    }
    
    /** 
	*getScore gets the current score of three and above kind of line scores
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
	*calculate score calculates the score for the three and above kind of lines based on current dies roll
	*
	*@param: linkedhash map to store the latest calculated score, array list of dice values integer, number of sides and number of dice
	*@return: -
	*/
    void calculateScore(LinkedHashMap<String,Integer>values,ArrayList<Integer>dieValues, int numSides, int numDies){
    	int kind = 3; //3 and above of a kind
    	System.out.println("NUmber of dies is " + numDies);	
    	 System.out.println("the kind value " + kind);	
    	for(int i = kind;i < numDies;i++){
    		String key = "The " + Integer.toString(i) + " of a kind";
    		
    		if((spotTaken.containsKey(key))&&(spotTaken.get(key) != 1)){
    			if(maxOfAKindFound(dieValues,numSides,numDies) >= i ){
    				int valueCalculated = totalAllDice(dieValues,numDies);
		    		values.put(key,valueCalculated);
		    		System.out.println(maxOfAKindFound(dieValues,numSides,numDies));
		    	}
		    	else{
		    		values.put(key,labelAndValues.get(key));
		    	}
		    
		    }
		    else{
		    	values.put(key,labelAndValues.get(key));
		    }
		   
    			
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
    		System.out.println("Value is " + values.get(key));
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
    
