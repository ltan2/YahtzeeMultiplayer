/**
 *This file setups the view of all the configuration panel. It is responsible for setting up the config panel so that user can change the configuratios of the game if they want to at the start. If you want to run this program, use-> java hw.Main, where hw is the package for all the class
 *CPSC 224-02, Spring 2021
 *Programming Assignment #1
 *No sources to cite.
 *
 *@author Joshua Seward
 *@version v1.0 5/6/21
*/


package hw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.Scanner;
import java.util.ArrayList;

//import personal library
import hw.Config;

//ConfigView setups the view of the configuration. By default it displays the default values that the yahtzeeConfig.txt file has stored and allows user to set it to new values if they wish to
public class ConfigView implements PropertyChangeListener{
	JPanel configPanel = new JPanel();
	JButton configButton;
	JComboBox <String> sidesField;
	JTextField diceField;
	JTextField rollsField;
	JTextField playerField;
	JLabel sidesLabel;
	JLabel dieLabel;
	JLabel rollsLabel;
	JLabel playerLabel;
	Config config;
	
	
	//constructor
    public ConfigView(Config newConfig, JFrame frame) {
    	newConfig.addPropertyChangeListener(this);
    	configPanel.setLayout(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
    	GridBagConstraints c2 = new GridBagConstraints();
    	GridBagConstraints c3 = new GridBagConstraints();
    	GridBagConstraints c4 = new GridBagConstraints();
    	GridBagConstraints c5 = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c2.anchor = GridBagConstraints.FIRST_LINE_START;
		c3.anchor = GridBagConstraints.FIRST_LINE_START;
		c4.anchor = GridBagConstraints.FIRST_LINE_START;
		c5.anchor = GridBagConstraints.FIRST_LINE_START;
		
		Color myColor = new Color(255, 255, 183);
		configPanel.setBackground(myColor);
    	JPanel content1 = new JPanel(new GridLayout(0,2));

        sidesLabel = new JLabel("  Enter number of sides in a die:    ");
        String[] choices = { "3", "4", "5", "6",
                         "7", "8" , "9" };

   		sidesField = new JComboBox<String>(choices);
   		sidesLabel.setFont(new Font("Serif", Font.PLAIN, 20));

   		//sidesLabel.setFont(sidesLabel.getFont().deriveFont(50));

        c.gridy = 0;
        c.gridx = 0;
        
        content1.add(sidesLabel);
        content1.add(sidesField);
        content1.setBackground(myColor);
        
        JPanel content2 = new JPanel(new GridLayout(0,2));
        dieLabel = new JLabel("  Enter number of dices in a hand:  ");
        diceField = new JTextField(Integer.toString(newConfig.getDiceNumber()),10);
      	dieLabel.setFont(new Font("Serif", Font.PLAIN, 20));
      	   		
		c2.gridy = 2;
		c2.gridx = 0;
		c2.insets = new Insets(100,0,0,0);  //top padding

        content2.add(dieLabel);
        content2.add(diceField);
        content2.setBackground(myColor);
        
        JPanel content3 = new JPanel(new GridLayout(0,2));
        rollsLabel = new JLabel("  Enter number of rolls :                  ");
        rollsField = new JTextField(Integer.toString(newConfig.getRolls()),10);
        rollsLabel.setFont(new Font("Serif", Font.PLAIN, 20));
       
	    c3.gridy = 6;
		c3.gridx = 0;
		c3.insets = new Insets(100,0,0,0);  //top padding
		
        content3.add(rollsLabel);
        content3.add(rollsField);
        content3.setBackground(myColor);
        
        JPanel content5 = new JPanel(new GridLayout(0,2));
        playerLabel = new JLabel("  Enter number of Players :                ");
        playerField = new JTextField(Integer.toString(newConfig.getNumberOfPlayers()),10);
		playerLabel.setFont(new Font("Serif", Font.PLAIN, 20));       
       
	    c5.gridy = 8;
		c5.gridx = 0;
		c5.insets = new Insets(100,0,0,0);  //top padding
		
        content5.add(playerLabel);
        content5.add(playerField);
        content5.setBackground(myColor);        
        
        configButton = new JButton("Set configuration");
        configButton.setFont(new Font("Serif", Font.PLAIN, 18));

        c4.gridy = 10;
        c4.gridx = 0;
        c4.insets = new Insets(100,0,0,0);  //top padding
        
        configPanel.add(content1,c);
        configPanel.add(content2,c2);
        configPanel.add(content3,c3);
        configPanel.add(content5,c5);
        configPanel.add(configButton,c4);
        
        frame.add(configPanel,BorderLayout.LINE_START);
        frame.setVisible(true);
     	config = newConfig;
     
    }
    
	/**
	* getConfigButton returns the configuration button
	*
	*@param:-
	*@return: JButton
	*/    
     public JButton getConfigButton(){
  		return configButton;
 	 }
    
    /**
	* getNumSidesText filter the input and return the user input text for number of sides
	*
	*@param:-
	*@return: string of user entered
	*/
	 public String getNumSidesText(){
	 	String text = sidesField.getSelectedItem().toString();;
	 	Scanner sc= new Scanner(text); 
		
	 	if((text == " ")||(sc.hasNextInt() == false)||(Integer.parseInt(text) > 9)){
	 		return Integer.toString(config.getDiceSides());
	 	}
	 	else{
	 		return text;
	 	} 
	 }
	 
   /**
	* getNumDiceText filter the input and return the user input text for number of dices
	*
	*@param:-
	*@return: string of user entered
	*/
	 public String getNumDiceText(){
	 	String text = diceField.getText();
	 	Scanner sc= new Scanner(text);  
		
	 	if((text == " ")||(sc.hasNextInt() == false)||(Integer.parseInt(text) < 1)){
	 		return Integer.toString(config.getDiceNumber());
	 	}
	 	else{
	 		return text;
	 	} 
	 }
	 
	/**
	* getNumRollsText filter the input and return the user input text for number of rolls
	*
	*@param:-
	*@return: string of user entered
	*/
	 public String getNumRollsText(){
	 	String text = rollsField.getText();
	 	Scanner sc= new Scanner(text); 
		
	 	if((text == " ")||(sc.hasNextInt() == false)||(Integer.parseInt(text) < 1)){
	 		return Integer.toString(config.getRolls());
	 	}
	 	else{
	 		return text;
	 	} 
	 }
	 
	 /**
	* getNumPlayersText filter the input and return the user input text for number of players
	*
	*@param:-
	*@return: string of user entered
	*/
	 public String getNumPlayersText(){
	 	String text = playerField.getText();
	 	Scanner sc= new Scanner(text); 
		
	 	if((text == " ")||(sc.hasNextInt() == false)||(Integer.parseInt(text) < 1)){
	 		return Integer.toString(config.getNumberOfPlayers());
	 	}
	 	else{
	 		return text;
	 	} 
	 }
	 
	 /**
	* property change repaints the whole configPanel to reflect the current configurations set by the user
	*
	*@param:property change event
	*@return: -
	*/
	 public void propertyChange(PropertyChangeEvent e) {
	 	
	 	configPanel.removeAll();
		
		configPanel.repaint();
		configPanel.setLayout(new BoxLayout(configPanel, BoxLayout.PAGE_AXIS));
		configPanel.setMaximumSize( configPanel.getPreferredSize());
		configPanel.setPreferredSize(new Dimension(300, 500));


	 	JLabel currLabel = new JLabel("<html><b>Current configurations: </b><br><br></html>");
	 	JLabel sidesLabel = new JLabel("<html> Number of sides: " + Integer.toString(config.getDiceSides())+ "<br><br></html>");
	 	JLabel dieLabel = new JLabel("<html> Number of dices: " + Integer.toString(config.getDiceNumber())+ "<br><br></html>");
	 	JLabel rollsLabel = new JLabel("<html> Number of rolls: " + Integer.toString(config.getRolls()) + "<br><br></html>");
	 	JLabel playersLabel = new JLabel("<html> Number of players: " + Integer.toString(config.getNumberOfPlayers()) + "<br><br></html>");
	 	JLabel scoresLabel = new JLabel("<html><br><br><br><b>Top 10 scores: </b><br></html>");
	 	playersLabel.setFont(new Font("Serif", Font.PLAIN, 18));     
	 	currLabel.setFont(new Font("Serif", Font.PLAIN, 18));         
	 	sidesLabel.setFont(new Font("Serif", Font.PLAIN, 18));       
	 	dieLabel.setFont(new Font("Serif", Font.PLAIN, 18));  
	    rollsLabel.setFont(new Font("Serif", Font.PLAIN, 18));            
	 	scoresLabel.setFont(new Font("Serif", Font.PLAIN, 18));   
	 	    
	 	configPanel.add(currLabel);
	 	configPanel.add(sidesLabel);
	 	configPanel.add(dieLabel);
	 	configPanel.add(rollsLabel);
	 	configPanel.add(playersLabel);
	 	configPanel.add(scoresLabel);
	 	
	 	ArrayList<String>names = new ArrayList<String>();
	 	ArrayList<String>scores = new ArrayList<String>();
	 	config.readScores(names,scores);
	 	
	 	
		for(int i=0;i<names.size();i++){
			JLabel playerName = new JLabel("<html> "+ names.get(i) + ": " + scores.get(i) + "<br><br></html>");
			playerName.setFont(new Font("Serif", Font.PLAIN, 18));       
			configPanel.add(playerName);
		}
	 	
	 	
	 	configPanel.revalidate();
	 	configPanel.repaint();
 		
 		
 	}
	
	
    
}
