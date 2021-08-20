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
import javax.swing.*;
/**
*class function: This maxStraight class when initialize sets, calculates, updates, print and reset scores related to the lower score card of the score card. 
*/
public class maxStraight{
	LinkedHashMap<String,Integer>labelAndValues = new LinkedHashMap<String,Integer>();
    LinkedHashMap<String,Integer>spotTaken = new LinkedHashMap<String,Integer>();
	
    //constructor
    public maxStraight(){
    	String slabel = "Small Straight Line";
    	String blabel = "Large Straight Line";
    	labelAndValues.put(slabel,0);
    	labelAndValues.put(blabel,0);
    	spotTaken.put(slabel,0);
    	spotTaken.put(blabel,0);
    }
    
   /** 
	*getScore gets the current score of maxStraight line
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
	*calculate score calculates the score for the maxStraight line based on current dies roll
	*
	*@param: linkedhash map to store the latest calculated score, array list of dice values integer, number of sides and number of dice
	*@return: -
	*/
    void calculateScore(LinkedHashMap<String,Integer>values,ArrayList<Integer>dieValues, int numSides, int numDies){
    	String skey = "Small Straight Line";
    	String lkey = "Large Straight Line";
    	int sScore = 30;
    	int lScore = 40;
    	if((maxStraightFound(dieValues,numSides,numDies) >= numDies-1)&&(spotTaken.get(skey) == 0)){
    		values.put(skey,sScore);
		}	
		else{
			values.put(skey,labelAndValues.get(skey));
		}
		if((maxStraightFound(dieValues,numSides,numDies) >= numDies)&&(spotTaken.get(lkey) == 0)){
			 values.put(lkey,lScore);
		}
		else{
			values.put(lkey,labelAndValues.get(lkey));
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
    
    /*
    *maxStraight found finds if there is a small or large straight in die value
    *
    *@param: die values as integer num sides and num dies
    *@return: if large or small straight
    */
    int maxStraightFound(ArrayList<Integer>values, int numSides, int numDice){
    	int maxLength = 1;
    	int curLength = 1;            
                                                                            
    	//sort dice values first
		Collections.sort(values);
    	
    	for(int counter = 0; counter < numDice-1; counter++){
        	if(values.get(counter) + 1 == values.get(counter + 1) ){ //jump of 1
            	curLength++;
            }
        	else if(values.get(counter) + 1 < values.get(counter + 1)){ //jump of >= 2
            	curLength = 1;
            }
        	if(curLength > maxLength){
            	maxLength = curLength;
            }
    	}
    	return maxLength;
	} 
	
    
}
