/**
@author Kai Wood
@version 10/16/2018
*/ 
package core;
import java.util.Scanner;

import ui.Connect4ComputerPlayer;
import ui.Connect4GUI;
import ui.Connect4TextConsole;
/**creates a two player game of Connect4*/
public class Connect4 extends Connect4GUI
{
	public static String str1;
	//creates the game of Connect4
	static Connect4TextConsole game = new Connect4TextConsole();
	public static void main(String[] args)
	{
	       Scanner scan = new Scanner(System.in);
	       String str = "";
	       while(!(str.equals("1") || str.equals("2")))
	       {
	    	   System.out.println("Begin Game. Enter '1' for GUI; enter '2' for Console Based UI");
	    	   str = scan.nextLine();
	       }
	       
	       str1 = "";
	       while(!(str1.equals("P") || str1.equals("C")))
	       {
	    	   System.out.println("Begin Game. Enter 'P' if you want to play against another player; enter 'C' to play against computer");
	    	   str1 = scan.nextLine();
	       }
	       if(str.equals("1"))
	       {
	    	   Connect4GUI.launch(args);
	       }
	       else
	       {
	    	   game.restartBoard();
		       //Count stay in loop until game is finished
		       int count = 0;
		       game.drawBoard();
		       while (count == 0)
		       {
		    	   boolean possible = false;
		           System.out.println(game.getPlayer() + " – your turn. Choose a column number from 1-7.");
		           while(possible == false)
		           {
			           int col = scan.nextInt();
			           col--;
			           //prevents index out of bounds by looping until it fits the conditions
			           while(col < 0 || col > 6)
			            {
			            	System.out.println("Number was not between 1-7. Please try again. \n");
			            	col = scan.nextInt();
					        col--;
			            }
			           //checks if the column is full
			           if(game.isFull(col) == false)
			           {
			        	   game.getMove(col);
			        	   possible = true;
			           }
		           }
		           //checks if the game is over
		           if(game.checkForWin())
		           {
		               System.out.println(game.getPlayer() + " wins!");
		               count++;
		               break;
		           }
		           //checks if the board is full
		           if(game.checkIfFull())
		           {
		               System.out.println("It is a tie");
		               count++;
		               break;
		           }
		           game.switchPlayer();
		           //AI's turn
		           if(str.equals("C"))
		           {
		        	   Connect4ComputerPlayer comp = new Connect4ComputerPlayer();
		        	   System.out.println("Computer's turn...");
		        	   int num = game.checkForBlock();
		        	   if(num == -1)
		        	   {
		        		   boolean pos = false;
		        		   while(pos == false)
		        		   {
			        		   int spot = comp.randMove();
			        		   if(game.isFull(spot) == false)
			        		   {
			        			   game.getMove(spot);
			        			   pos = true;
			        		   }
		        		   }
		        	   }
		        	   else
		        	   {
		        		   game.getMove(num);
		        	   }
		   
		        	   if(game.checkForWin())
			           {
			               System.out.println(game.getPlayer() + " wins!");
			               count++;
			               break;
			           }
			           if(game.checkIfFull())
			           {
			               System.out.println("It is a tie");
			               count++;
			               break;
			           }
			           game.switchPlayer();
		        	   
		           }
		       }
	       }
	}
}