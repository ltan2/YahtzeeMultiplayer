/**
 *This file loads the image for each die and responds to any changes by sending appropriate images to the die from the media folde inside src folder. If you want to run this program, use-> java hw.Main, where hw is the package for all the class
 *CPSC 224-02, Spring 2021
 *Programming Assignment #1
 *No sources to cite.
 *
 *@author Lin Ai
 *@version v1.0 2/6/21
*/

//Config controller is package in hw

package hw;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.imageio.*;
import java.util.ArrayList;

//DiceImages setups the image to display for each die. By default it renders one as die face
public class DiceImages{
    ArrayList<ImageIcon> images;

    void loadImages(String imagesPath, int faces) {
        BufferedImage currPicture;
        images.add(null);
        for( int i = 1; i < faces+1; i++) {
            try {
                String filename = imagesPath + "/D6-0" + i + ".png";
                //System.out.println(filename);
                currPicture = ImageIO.read(new File(filename));
                Image dimg = currPicture.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                ImageIcon scaledImage = new ImageIcon(dimg);
                images.add(scaledImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
	//constructor
    public DiceImages(String imagesPath, int faces) {
        images = new ArrayList<>(12);
        loadImages(imagesPath,faces);
    }

	/**
	* getDieImage returns the correct die image as an image icon given a die value
	*
	*@param: die value as an integer
	*@return: imageIcon
	*/
    public ImageIcon getDieImage(int dieValue) {
        return images.get(dieValue);
    }
}
