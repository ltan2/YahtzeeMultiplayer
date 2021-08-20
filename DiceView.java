/**
 *This file setups the view of a die. It is responsible for setting up its look and return its value, lock and unlock itself when user interacts with the die. If you want to run this program, use-> java hw.Main, where hw is the package for all the class
 *CPSC 224-02, Spring 2021
 *Programming Assignment #1
 *No sources to cite.
 *
 *@author Lin Ai
 *@version v1.0 2/6/21
*/

//DiceView is a package in hw 

package hw;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import hw.DiceImages;

//DiceView represents one die. It gets its appearance from DiceImages, setup its look and can read its own value and lock and unlock itself depending on user
public class DiceView extends JButton implements PropertyChangeListener{
    boolean locked = false;
    DiceImages images;
    Die dieToView;
    
	//constructor
    public DiceView(String buttonText, int faces) {
        super(buttonText);
        images = new DiceImages("media/",faces);
    }

    public DiceView(int faces) {
        super("");
        images = new DiceImages("media/",faces);

        setSize(200,200);
        setIcon(images.getDieImage(1));
    }
	
  /**
	* lock locks the button by updating its look to have a red border
	*
	*@param: -
	*@return: -
	*/
    public void lock() {
        locked = true;
        updateLockedState();
    }

  /**
	* unlock unlocks the button by updating its look to have a black border
	*
	*@param: -
	*@return: -
	*/
    public void unlock() {
        locked = false;
        updateLockedState();
    }

	/**
	* toggleLocked changes the current locked stage of a die
	*
	*@param: -
	*@return: -
	*/
    void toggleLocked() {
        locked = !locked;
        updateLockedState();
    }
	
	/**
	* updateLockedState checks if die is being selected to locked or unlock. If lock, it has red border, else it has black border
	*
	*@param: -
	*@return: -
	*/
    void updateLockedState() {
        Border blackline = BorderFactory.createLineBorder(Color.black);
        Border redline = BorderFactory.createLineBorder(Color.red);
        if(locked) {
            setBorder(redline);
            dieToView.lock();
        } else {
            setBorder(blackline);
            dieToView.unlock();
        }
    }

	/**
	* setDieToView switched the current locked state to lock or unlock when being pressed
	*
	*@param: die object
	*@return: -
	*/
    void setDieToView(Die newDieToView) {
        dieToView = newDieToView;
        dieToView.addPropertyChangeListener(this::propertyChange);
        addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    toggleLocked();
                }
            }
        );
    }

	/**
	* propertyChange, when there is an update to the die, it changes its image to reflect current die value
	*
	*@param: propertychange event
	*@return: -
	*/
    public void propertyChange(PropertyChangeEvent e) {
           // System.out.println(("DieView sees value changed to: " + e.getNewValue()));
            setIcon(images.getDieImage((int) e.getNewValue()));
        
    }

}
