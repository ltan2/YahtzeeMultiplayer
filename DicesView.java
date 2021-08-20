/**
 *This file setups the view of all the dices. It is responsible for telling each die View to update or setup itself. If you want to run this program, use-> java hw.Main, where hw is the package for all the class
 *CPSC 224-02, Spring 2021
 *Programming Assignment #1
 *No sources to cite.
 *
 *@author Lin Ai
 *@version v1.0 5/6/21
*/

//DicesView is a package in hw 

package hw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.border.Border;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JOptionPane;

import hw.DiceView;
import hw.SetOfDices;

//DicesView represents a hand of dice. It determines how many individual die view to setup and handle based on the number of dies user wants to play with and setups a dice panel for it
public class DicesView{
    JPanel myPanel;
    ArrayList<DiceView> dieViews;
    JButton rollButton = new JButton();
    SetOfDices hand;
	int rollsAllowed =0;	
	int numDices = 0;
	
   /**
	* setupPanel setups the panel to store all individual die view and then adds this panel to the page_start position of the main frame
	*
	*@param: JFrame of the main frame
	*@return: -
	*/
    void setupPanel(JFrame frame) {
        myPanel = new JPanel();
        Border blackline = BorderFactory.createLineBorder(Color.black);
        myPanel.setLayout(new FlowLayout());
        myPanel.setPreferredSize(new Dimension(200, 100));
      
     	Color myColor = new Color(255, 255, 204);
		myPanel.setBackground(myColor);
        frame.add(myPanel,BorderLayout.PAGE_START);
        
    }
	
   /**
	* setsRollsAllowed resets the number of times user has clicked the roll button to 0
	*@param: -
	*@return: -
	*/
	void setRollsAllowed(){
		rollsAllowed = 0;
		rollButton.setText("Roll!");
		rollButton.setFont(new Font("Serif", Font.PLAIN, 18));
	}
		
	  /**
	* setupDiceViews configure how many faces each die can have and aso how many dies does this hand has
	*
	*@param: number of dice and sides of dice as integer
	*@return: -
	*/	
    void setupDiceViews(int numDice, int faces) {
        for( int i = 0; i < numDice; i++ ) {
      		DiceView newView = new DiceView(faces);
            newView.setDieToView(hand.getDieAt(i));
            dieViews.add(newView);
            myPanel.add(newView);
        }
        numDices = numDice;
    }

	  /**
	* setupRollButton setups the roll button and regiter it as an action listener. Everytime when button is click it checks if the number of rolls left for the user is less than the number of rolls allowed. If yes, than it calls the dices to roll the dice
	*
	*@param: property change event
	*@return: -
	*/
    void setupRollButton(int faces, int rolls) {
        rollButton.setText("Roll!");
        rollButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Button pressed to roll");
                    if(rollsAllowed < rolls ){
                    hand.rollDice(faces);
                    rollsAllowed +=1; 
                    }
                    else{
                    	JOptionPane.showMessageDialog(null, "You hit max rolls.Save score on score card before rolling again!");

                    }
                }
            }
        );
        myPanel.add(rollButton);
    }
    
   /**
	* freeUnlock tells each individual dice to unlock itelf
	*
	*@param:-
	*@return: -
	*/
    void freeUnlock(){
    	for( int i = 0; i < numDices; i++ ) {
    		dieViews.get(i).unlock();
    	}
    }
    
	//constructor
    public DicesView(SetOfDices newHand, JFrame frame, int numDices, int numSides, int rolls){
        hand = newHand;
        dieViews = new ArrayList<>();
     
		setupPanel(frame);
        setupDiceViews(numDices,numSides);
        setupRollButton(numSides,rolls);
    }
    
   /**
	* getPanel() returns the dices panel 
	*
	*@param: -
	*@return: -
	*/
    public JPanel getPanel() {
        return myPanel;
    }
}
