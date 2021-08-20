/**
 *This file setups the view of all the player panel. It is responsible for setting up the config panel so that user can change the configuratios of the game if they want to at the start. If you want to run this program, use-> java hw.Main, where hw is the package for all the class
 *CPSC 224-02, Spring 2021
 *Programming Assignment #1
 *No sources to cite.
 *
 *@author Lin Ai
 *@version v1.0 5/6/21
*/


package hw;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.Scanner;

//import personal library
import hw.Player;

//ConfigView setups the view of the configuration. By default it displays the default values that the yahtzeeConfig.txt file has stored and allows user to set it to new values if they wish to
public class PlayerView implements PropertyChangeListener{
	JPanel playersPanel = new JPanel();
	ArrayList<JTextField> namesLabel = new ArrayList<JTextField>();
	Player players;
	JButton setNames;
	
	//constructor
    public PlayerView(Player newPlayer, JFrame mainframe,int numPlayer) {
    	newPlayer.addPropertyChangeListener(this);
    	JPanel content2 = new JPanel();
    	content2.setLayout(new BoxLayout(content2, BoxLayout.PAGE_AXIS));
    	
    	JPanel content1 = new JPanel(new GridLayout(0,2));
    	setNames = new JButton("set names!");
		JLabel name = new JLabel("Enter all player names!");
		name.setFont(new Font("Serif", Font.PLAIN, 16));
		
		playersPanel.setPreferredSize(new Dimension(1000, 140));
		content2.add(name);
		Color myColor = new Color(255, 229, 204);
		playersPanel.setBackground(myColor);
		content1.setBackground(myColor);
		content2.setBackground(myColor);
		content2.setPreferredSize(new Dimension(600, 130));
		for(int i=0;i<numPlayer;i++){
			JTextField fieldName = new JTextField("Enter name for player " + Integer.toString(i+1));
			namesLabel.add(fieldName);
			content2.add(fieldName);
		}
		content1.add(content2);
		content2.add(setNames);
		playersPanel.add(content1);
        mainframe.add(playersPanel,BorderLayout.PAGE_END);
        mainframe.setVisible(true);
     	players = newPlayer;
     
    }
    
     public void propertyChange(PropertyChangeEvent e){
	 }
    
    /*getSetButton returns the set names button
    *
    *@param:-
    *@return: JButton
    */
    public JButton getSetButton(){
    	return setNames;
    }
    
    /*getNamesField returns the textfield at which players enter their name
    *
    *@param:-
    *@return: array list of Jtextfield
    */
    public ArrayList<JTextField>getNamesField(){
    	return namesLabel;
    }
   
    
    
}
