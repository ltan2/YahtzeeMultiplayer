/**
 *This file controlls the user interactions with the configurations. When user interacts with the config button, it gets all the text field values that uer has inputed and reports to Config. If you want to run this program, use-> java hw.Main, where hw is the package for all the class
 *CPSC 224-02, Spring 2021
 *Programming Assignment #1
 *No sources to cite.
 *
 *@author Lin Ai
 *@version v1.0 5/6/21
*/

//Config controller is package in hw 

package hw;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

// ConfigController handles the events when set config button has been pressed by sending latest value to the config object
public class PlayerControl implements PropertyChangeListener {
	private Player player;
 	private PlayerView view;
 	
 
 	//constructor
	public PlayerControl(Player newPlayers, PlayerView newView) {
		newPlayers.addPropertyChangeListener(this);
  		player = newPlayers;
  		view = newView;
 	}
 	
 	/**
	* propertyChange stores player names
	*
	*@param: property change event
	*@return: -
	*/
 	public void propertyChange(PropertyChangeEvent e) {
 		System.out.println("Is set!");
 	}
 	
 
 	/**
	* setAllNames setups the config button to be an action listener. When button is pressed it gets the latest config and calls save config
	*
	*@param: -
	*@return: -
	*/
 	public void setAllNames(){
	 	JButton button = view.getSetButton();
	 	button.addPropertyChangeListener(this);
	 	button.addActionListener(
		new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		          System.out.println("Button pressed");
		          ArrayList<JTextField> allNames = view.getNamesField();
		          ArrayList<String>names = new ArrayList<String>();
		          for(int i=0;i<allNames.size();i++){
		          	 names.add(allNames.get(i).getText());
		          }
		          player.setNames(names);
		                         
		     }
	    }
		        
	 );      
 		
 	}
 	
 	
 	
} 	

