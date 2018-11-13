/**
@author Kai Wood
@version 10/16/2018
*/ 
package ui;

import java.util.Scanner;

public class Connect4ComputerPlayer
{
    public int randMove()
    {
    	return ((int)(Math.random()*7 + 1));
    }
	
}
