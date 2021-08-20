/**
 *This program setups the game by setting up the configuration by making a config object to load default settings and also to allow user to chnage the configuration. It also makes a Game object, so that we can play the game using the playGame() function of class Game.
 *This is the main class. If you want to run this program, use-> java hw.Main, where hw is the package for all the class
 *CPSC 224-02, Spring 2021
 *Programming Assignment #1
 *No sources to cite.
 *
 *@author Lin Ai
 *@version v1.0 5/6/21
*/

//Main is package in hw 
package hw;

//import java library
//import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

//import Game class
import hw.Game;
import hw.Config;
import hw.ConfigController;
import hw.Die;
import hw.DiceView;
import hw.DicesView;
import hw.SetOfDices;
import hw.DiceController;


/**
*Main class setup the game
*
*@param: string of arguments
*@return: no return
*/

public class Main{
	
	public static void main(String[] args) {
		// Creating instance of JFrame
        JFrame frame = new JFrame("Yahtzee Game");
        // Setting the width and height of frame
        frame.setSize(450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       	frame.setLayout(new BorderLayout());
       	
       	Color myColor = new Color(255, 255, 163);
		frame.getContentPane().setBackground(myColor);
       	
		//setup configurations
		Config newConfig = new Config();
		ConfigView observer = new ConfigView(newConfig,frame);
		ConfigController conControl = new ConfigController(newConfig,observer);
		
		newConfig.printConfiguration(); //prints default configuration
		conControl.setAllConfig();
		
		newConfig.writeToFile();
		//setup main game
		Game newGame = new Game(frame, newConfig);
		   
    }

}
