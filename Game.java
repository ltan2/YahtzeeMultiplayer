/**
*This class handles the main logic of the game but does not set it up. 
*Its main function playGame() basically starts the game, creates a setOfDices and Score objects to use functions that helps with rolling, storing and sendind updates to the observer each time there is a change
*Before starting the game, it needs to be setup, which is done in Main.java. If you want to run thi program, use-> java hw.Main, where hw is the package for all the class
*CPSC 224-02, Spring 2021
*Programming Assignment #1
*No sources to cite.
*
*@author Lin Ai
*@version v1.0 5/6/21
*/

//Game is a package of hw
package hw;

//import Java libraries
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;

//import personal class
import hw.SetOfDices;
import hw.Scorecard;
  
/**
*class function: This game class when setups all the components of the game such as dice and score card after configuration has been set
*/

public class Game implements PropertyChangeListener{
	JFrame mainFrame;
	Config config;
	
	//constructor
	public Game(JFrame frame, Config newConfig){
		newConfig.addPropertyChangeListener(this);
		config = newConfig;
       
        mainFrame = frame;
        
	}
 
	/*
	* propertyChange setups the dice and score card's controller, views and objects etc, after the user is done setting up the configuration 
    *
    *@param: property change event
    *@return: -
    */
	public void propertyChange(PropertyChangeEvent e){
		int numPlayers = config.getNumberOfPlayers();
		System.out.println("Game reads players as " + numPlayers);
		int numDices = config.getDiceNumber();
		int numSides = config.getDiceSides();
		int numRoll = config.getRolls();
		SetOfDices dices = new SetOfDices(numDices);
		
		//GamePanel.setLayout( new BoxLayout(mainFrame, BoxLayout.PAGE_AXIS));

		DicesView diceView = new DicesView(dices,mainFrame, numDices, numSides, numRoll);
		//PlayerName playerNames = new PlayerName(numPlayers);
		
		Player allPlayers = new Player(numPlayers,numDices,numSides,dices,mainFrame,diceView);
		PlayerView playerView = new PlayerView(allPlayers,mainFrame,numPlayers); 
		PlayerControl playerCont = new PlayerControl(allPlayers,playerView);
		playerCont.setAllNames();
	}					

	/**
	*userRoll prompts the user which dice they want to keep or not.Make sure the input length is same with numberOfDice
	* 
	*@param number of dices in a hand as an integer
	*@return userInput as a string of y and n to which dices they want to keep
	*/
	
	String userRoll(int numberOfDice){
		String userInput = "";
		
		while(userInput.length() != numberOfDice){
			System.out.println("Enter dice to keep (y or n)");
			Scanner sc= new Scanner(System.in); //System.in is a standard input stream  
			userInput = sc.nextLine();
		}
		
		return userInput;
	}
	
	/**
	*playGameAgain prompt users if they want to play again or not function. Returns users input to main playGame() function
	*
	*@param no parameters
	*@return userInput as a string y indicatimg yes to play game again or not
	*/
	
	String playGameAgain(){
		String userInput = "";
		
		System.out.println("Enter 'y' to play again");
		Scanner sc= new Scanner(System.in); //System.in is a standard input stream  
		userInput = sc.nextLine();
		
		return userInput;
	}
	
}



						
						





