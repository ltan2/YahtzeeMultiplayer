/**
*This class handles the configuration of the game but does not set it up. 
*Its function is to store the configurations of the file by reading from yahtzeeConfig.txt, and allow the configurations to be change if the user wishes to do so,  
*Before starting the game, it needs to be setup, which is done in Main.java. If you want to run thi program, use-> java hw.Main, where hw is the package for all the class
*CPSC 224-02, Spring 2021
*Programming Assignment #2
*No sources to cite.
*
*@author Josh Seward
*@version v1.0 5/6/21
*/

//Config is a package of hw
package hw;

//import java library
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;


/**
*class function: This Config class when initialize reads and stores the default configurations. It fires off changes to the configView class and other observers when being changed. It is initialize in the Main class when setting up the game
*/
public class Config{
 
  //initialize attribute
  int numberOfDiceinHand = 0;
  int numberOfDiceSides = 0;
  int numberOfRoleAllowed = 0;
  int numberOfPlayers = 1;
  String filename = System.getProperty("user.dir") + "/hw/yahtzeeConfig.txt";
  String scoreFile = System.getProperty("user.dir") + "/hw/topScores.txt";
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
      
  //constructor
  public Config(){
  	ArrayList<Integer>configurations = new ArrayList<Integer>();
  	
  	configurations = readFile(filename);
  	setNumberOfDice(configurations.get(1));
  	setNumberOfSides(configurations.get(0));
  	setNumberOfRoll(configurations.get(2));
  	    
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
  *readFile opens the file, reads the content, closes the file and returns its contents as an array list of integers
  * 
  *@param file name as a string
  *@return array list of integer of contents in the file
  */
  ArrayList<Integer> readFile(String filename){
    //initizalize variables
    ArrayList<Integer>retConfig = new ArrayList<Integer>();
   // System.out.println("Working Directory = " + System.getProperty("user.dir"));
    try{
  	File myObj = new File(filename);
  	String str = "";
  	Scanner myReader = new Scanner(myObj);
  	
    while (myReader.hasNextLine()) {
       retConfig.add(Integer.parseInt(myReader.nextLine()));
    }
    myReader.close();
    }
    catch(Exception ex) {
            ex.printStackTrace();
    }
   
    return retConfig;
  }

	/*readScores reads all the scores from top 10 scores file to display the current top 10 scores for our added feature of yahtzee
	*
	*@param : array list of player name and scores to return to
	*@return: void
	*/
	void readScores(ArrayList<String>playerName,ArrayList<String>scores){
		try{
	  	File myObj = new File(scoreFile);
	  	String str = "";
	  	Scanner myReader = new Scanner(myObj);
	  	
		while (myReader.hasNextLine()) {
		   String line = myReader.nextLine();
		   String[] scoresArray = line.split(",");
		   playerName.add(scoresArray[0]);
		   scores.add(scoresArray[1]);
		}
		myReader.close();
		}
		catch(Exception ex) {
		        ex.printStackTrace();
		}
	}
   /**
	*setNumberOfSides sets the number of sides a die can have
	* 
	*@param number of sides as integer
	*@return no return
    */
  void setNumberOfSides(int sides){
  	numberOfDiceSides = sides;
  }
  
  /**
	*setNumberOfDice sets the number of dice in a hand
	* 
	*@param number of dice in hand as integer
	*@return no return
  */
  void setNumberOfDice(int dices){
  	numberOfDiceinHand = dices;
  }
  
  /**
	*setNumberOfRoll sets the number of rolls/chances a player can have in one round of game
	* 
	*@param number of rolls as integer
	*@return no return
  */
  void setNumberOfRoll(int rolls){
  	numberOfRoleAllowed = rolls;
  }
  
  /**
	*setNumberOfPlayers sets the number of players in a game
	* 
	*@param number of player as integer
	*@return no return
  */
  void setNumberOfPlayers(int numPlayers){
  	System.out.println("The number of players is set to " +numPlayers);
  	numberOfPlayers = numPlayers;
  }

	/*
	* setConfig sets the number of sides, dices and rolls in the game
    *
    *@param: numsides of each die, number of die in hand, and number of rolls allowed for each roll
    *@return: -
    */
   void setConfig(int numSides, int numDie, int numRoll,int numPlayers){
   		setNumberOfSides(numSides);
   		setNumberOfDice(numDie);
   		setNumberOfRoll(numRoll);
   		setNumberOfPlayers(numPlayers);
   		System.out.println("config has been set!");
   		this.pcs.firePropertyChange("config",0,numSides);	
   			
   }  

  
   /**
	*getDiceSides returns the number of sides a die can have
	* 
	*@param no parameters
	*@return number of sides as integer
  */
  int getDiceSides(){
  	return numberOfDiceSides;
  }
  
   /**
	*getDiceNumber returns the number of dice in a hand
	* 
	*@param no parameters
	*@return number of dice as integer
  */
  int getDiceNumber(){
  	return numberOfDiceinHand;
  }
  
   /**
	*getRolls returns the number of rolls/chances each player can have in a round of game
	* 
	*@param no parameters
	*@return number of rolls as integer
  */
  int getRolls(){
  	return numberOfRoleAllowed;
  }
  
   /**
	*getNumberOfPlayers returns the number of players in a game
	* 
	*@param no parameters
	*@return number of players as integer
  */
  int getNumberOfPlayers(){
  	return numberOfPlayers;
  }
 
  /**
	*printConfiguration prints the current configuration of the game
	* 
	*@param no parameters
	*@return no returns
  */
  void printConfiguration(){
  
  	System.out.println("You are playing with " + numberOfDiceinHand + " " + numberOfDiceSides + "-sided dice");
  	System.out.println("You get " + numberOfRoleAllowed + " rolls in a hand");
  		
  }
 
  
  
  /**
	* write to file store the latest configuration set by user to the file "yahtzeeConfig.txt"
	*
	*@param: -
	*@return: -
	*/
  void writeToFile(){
  	try{
	 FileWriter fileWriter = new FileWriter(filename);
	 PrintWriter printWriter = new PrintWriter(fileWriter);
	 printWriter.print(getDiceSides());
	 printWriter.print("\n");
	 printWriter.print(getDiceNumber());
	 printWriter.print("\n");
	 printWriter.print(getRolls());
	 printWriter.close();
	}
	catch(Exception ex) {
       ex.printStackTrace();
    }
  }
 
}
