/**
 *This file setups the a die. It is responsible for knowing its current value, rolling itself an knowing if it is lock or not. If you want to run this program, use-> java hw.Main, where hw is the package for all the class
 *CPSC 224-02, Spring 2021
 *Programming Assignment #1
 *No sources to cite.
 *
 *@author Lin Ai
 *@version v1.0 2/6/21
*/

package hw;

import java.util.Random;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class Die {
    static int dieCount = 0;
    int myID = 0;
    int value = 0;
    boolean locked = false;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	//constructor
    public Die() {
        myID = dieCount;
        dieCount++;
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
          this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
	
	/**
	* lock sets the boolean locked to true so that the die knows if it is lock or not
	*
	*@param: -
	*@return: -
	*/
    public void lock(){
    	locked = true;
    }
    
    /**
	* unlock sets the boolean locked to false so that the die knows if it is lock or not
	*
	*@param: -
	*@return: -
	*/
    public void unlock(){
    	locked = false;
    }

	/**
	* roll randomly generates a number between 1 to the max num face set
	*
	*@param: number of faces/sides
	*@return: the current value on die
	*/
    public int roll(int faces) {
        if(!locked) {
            Random rand = new Random(); //instance of random class
            int upperbound = faces;
            int val = rand.nextInt(upperbound) + 1;
            this.setValue(val);
            return val;
        }
        return 1;
    }

	/**
	*  setValue locks the current value of the die and then informs listeners of this die
	*
	*@param: new value of die
	*@return: -
	*/
    private void setValue(int newValue) {
        int oldValue = this.value;
        this.value = newValue;
        this.pcs.firePropertyChange("dievalue", oldValue, newValue);
    }

	/**
	* getValue returns current value of die
	*
	*@param:-
	*@return: current value of die as int
	*/
    public int getValue() {
        return value;
    }

	/**
	* convert value to string
	*
	*@param: -
	*@return: value of die as string
	*/
    public String toString() {
        return "Die value: " + value;
    }
}
