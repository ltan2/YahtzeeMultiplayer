/**
 *This file controlls the user interactions with the configurations. When user interacts with the config button, it gets all the text field values that uer has inputed and reports to Config. If you want to run this program, use-> java hw.Main, where hw is the package for all the class
 *CPSC 224-02, Spring 2021
 *Programming Assignment #1
 *No sources to cite.
 *
 *@author Joshua Seward
 *@version v1.0 5/6/21
*/

//Config controller is package in hw 

package hw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

// ConfigController handles the events when set config button has been pressed by sending latest value to the config object
public class ConfigController implements PropertyChangeListener {
	private Config config;
 	private ConfigView view;
 	int sides = 6;
 	int die= 5;
 	int rolls = 3;
 	int players = 1;
 	boolean set = false;
 
 	//constructor
	public ConfigController(Config newConfig, ConfigView newView) {
		newConfig.addPropertyChangeListener(this);
  		config = newConfig;
  		view = newView;
 	}
 	
 	/**
	* propertyChange writes latest config values to file
	*
	*@param: property change event
	*@return: -
	*/
 	public void propertyChange(PropertyChangeEvent e) {
 		//System.out.println("controller confirm twice has been pressed");
 		config.writeToFile();
 	}
 	
 
 	/**
	* setAllConfig setups the config button to be an action listener. When button is pressed it gets the latest config and calls save config
	*
	*@param: -
	*@return: -
	*/
 	public void setAllConfig(){
	 	JButton button = view.getConfigButton();
	 	button.addPropertyChangeListener(this);
	 	button.addActionListener(
		new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		          System.out.println("Button pressed");
		          while(set == false){
	 					if(sides < 11 && sides > 0){                   
		                	sides = Integer.parseInt(view.getNumSidesText());
		                	die = Integer.parseInt(view.getNumDiceText());
		                	rolls = Integer.parseInt(view.getNumRollsText());
		                	players = Integer.parseInt(view.getNumPlayersText());
		                	set = true;
		                	break;
		                }
		                else{
		                	System.out.println("Too many sides!");
		                }
		           }
		           saveConfig(sides,die,rolls,players);		               
		     }
	    }
		        
	 );      
 		
 	}
 	
 	/**
	* saveConfig saves the latest configuartion by sending them to Config for storage
	*
	*@param: number of sides of a die, number of die in hand and number of rolls
	*@return: -
	*/
 	private void saveConfig(int sides,int die, int rolls, int players) {
  		config.setConfig(sides,die,rolls,players);
 	}
 	
} 	

