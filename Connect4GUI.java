/**
@author Kai Wood
@version 10/30/2018
*/ 
package ui;

import core.Connect4;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Connect4GUI extends Application 
{

	// Indicate which player has a turn, initially it is the X player
	private char whoseTurn = 'X';
		  
	// Determines if 2 players or AI
	private String answer = Connect4.str1;

	// Create and initialize cell
	private Cell[][] board = new Cell[6][7];

	// Create and initialize a status label
	private Label lblStatus = new Label("Red's Turn");

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) 
	{
		// Pane to hold cell
		GridPane pane = new GridPane();
			
		for (int i = 0; i < 6; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				pane.add(board[i][j] = new Cell(), j, i);
			}
		}
			
		Button bt1 = new Button("1");
		Button bt2 = new Button("2");
		Button bt3 = new Button("3");
		Button bt4 = new Button("4");
		Button bt5 = new Button("5");
		Button bt6 = new Button("6");
		Button bt7 = new Button("7");
			
		HBox hBox = new HBox(bt1, bt2, bt3, bt4, bt5, bt6, bt7);
		hBox.setSpacing(70);
	    hBox.setAlignment(Pos.CENTER);
		    
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(hBox);
			
		/** Setting up the buttons */
		bt1.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override // Override the handle method
		    public void handle(ActionEvent e) 
			{
				if(!isFull(0))
				{
					int row = getLowest(0);
					board[row][0].setToken(whoseTurn);
					switchTurns();
					if(answer.equals("C"))
					{
						compTurn();
					}
				}
			}
		});
		bt2.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override // Override the handle method
		    public void handle(ActionEvent e) 
			{
				if(!isFull(1))
				{
					int row = getLowest(1);
					board[row][1].setToken(whoseTurn);
					switchTurns();
					if(answer.equals("C"))
					{
						compTurn();
					}
				}
			}
		});
		bt3.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override // Override the handle method
		    public void handle(ActionEvent e) 
			{
				if(!isFull(2))
				{
					int row = getLowest(2);
					board[row][2].setToken(whoseTurn);
					switchTurns();
					if(answer.equals("C"))
					{
						compTurn();
					}
				}
			}
		});
		bt4.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override // Override the handle method
		    public void handle(ActionEvent e) 
			{
				if(!isFull(3))
				{
					int row = getLowest(3);
					board[row][3].setToken(whoseTurn);
					switchTurns();
					if(answer.equals("C"))
					{
						compTurn();
					}
				}
			}
		});
		bt5.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override // Override the handle method
		    public void handle(ActionEvent e) 
			{
				if(!isFull(4))
				{
					int row = getLowest(4);
					board[row][4].setToken(whoseTurn);
					switchTurns();
					if(answer.equals("C"))
					{
						compTurn();
					}
				}
			}
		});
		bt6.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override // Override the handle method
		    public void handle(ActionEvent e) 
			{
				if(!isFull(5))
				{
					int row = getLowest(5);
					board[row][5].setToken(whoseTurn);
					switchTurns();
					if(answer.equals("C"))
					{
						compTurn();
					}
				}
			}
		});
		bt7.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override // Override the handle method
		    public void handle(ActionEvent e) 
			{
				if(!isFull(6))
				{
					int row = getLowest(6);
					board[row][6].setToken(whoseTurn);
					switchTurns();
					if(answer.equals("C"))
					{
						compTurn();
					}
				}
			}
		});
		
		borderPane.setCenter(pane);
		borderPane.setBottom(lblStatus);
		// Create a scene and place it in the stage
		Scene scene = new Scene(borderPane, 700, 700);
		primaryStage.setTitle("Connect 4"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	  }
	  /** Determine if the cell are all occupied */
	  public boolean isFull() 
	  {
		for (int i = 0; i < 6; i++)
		{
			for (int j = 0; j < 7; j++)
			{
			    if (board[i][j].getToken() == ' ')
			    {
			    	return false;
			    }
			}
		}
		return true;
	  }
	  /** Switches the player's turn and checks win conditions */
	  public void switchTurns()
	  {
		  if (whoseTurn != ' ') 
		  {
			  // Check game status
			  if (isWon(whoseTurn)) 
			  {
				  if(whoseTurn == 'X')
				  {
					  lblStatus.setText("Red won! The game is over");
				  }
				  else
				  {
					  lblStatus.setText("Blue won! The game is over");
				  }
				  whoseTurn = ' '; // Game is over
			  }
			  else if (isFull()) 
			  {
				  lblStatus.setText("Draw! The game is over");
				  whoseTurn = ' '; // Game is over
			  }
			  else 
			  {
				  // Change the turn
				  whoseTurn = (whoseTurn == 'X') ? 'O' : 'X';
				  // Display whose turn
				  if(whoseTurn == 'X')
				  {
					  lblStatus.setText("Red's turn");
				  }
				  else
				  {
					  lblStatus.setText("Blue's turn");
				  }
			  }
		  }
	  }
	  /** Get the lowest (greatest value) index in that given column */
	  public int getLowest(int col)
	  {
		  for(int row = 0; row < board.length  ; row++)
		  {
			  if(board[row][col].getToken() != ' ')
			  {
				  if(row != 0)
				  {
					  return row - 1;
				  }
			  }
		  }
		  return 5;
	  }
	  /** Computer's move */
	  public void compTurn()
	  {
		  Connect4ComputerPlayer comp = new Connect4ComputerPlayer();
       	  int num = checkForBlock();
       	  if(num == -1)
       	  {
       		  boolean pos = false;
       		  while(pos == false)
       		  {
       			  int spot = comp.randMove();
	        	   if(isFull(spot) == false)
	        	   {
	       			   getMove(spot);
	       			   pos = true;
	       		   }
       		  }
     	  }
       	  else
       	  {
       		  getMove(num);
    	  }
	  }
	  /** Gets the move in the specified column */
	  public void getMove(int col)
	  {
		  for(int i = board.length - 1; i >= 0; i--)
		  {
			  if(board[i][col].getToken() == ' ')
			  {
				  board[i][col].setToken(whoseTurn);
				  switchTurns();
				  break;
			  }
		  }
	  }
	  /** Checks if the given column is full */
	  public boolean isFull(int col)
	  {
		  int count = 0;
		  for(int i = board.length - 1; i >= 0; i--)
		  {
			  if(board[i][col].getToken() == ' ')
			  {
				  count++;
			  }
		  }
		  if(count == 0 && (col >= 0 && col <= 6))
		  {
			  lblStatus.setText("Column is already full. Try another \n");
			  return true;
		  }
		  return false;
	  }
	  /** Checks if the AI can block the user from winning */
	  public int checkForBlock()
	  {
		  int countX = 0;
		  int num = -1;
		  
		  //horizontal
		  for(int row = 0; row < board.length; row++)
		  {
			  for(int col = 0; col < board[0].length - 3; col++)
			  {
				  countX = 0;
				  int blank = -1;
				  if(board[row][col].getToken() == 'X')
				  {
					  countX++;
				  }
				  if(board[row][col].getToken() == ' ')
				  {	
					  blank = col;
				  }		
				  if(board[row][col+1].getToken() == 'X')
				  {
					  countX++;
				  }
				  if(board[row][col+1].getToken() == ' ')
				  {
					  blank = col+1;
				  }
				  if(board[row][col+2].getToken() == 'X')
				  {
					  countX++;
				  }
				  if(board[row][col+2].getToken() == ' ')
				  {
					  blank = col+2;
				  }
				  if(board[row][col+3].getToken() == 'X')
				  {
					  countX++;
				  }
				  if(board[row][col+3].getToken() == ' ')
				  {
					  blank = col+3;
				  }
				  if(countX == 3 && blank != -1 && blank < 7 && board[row][col].getToken() != ' ')
				  {
					  return blank;
				  }
			  }
		  }
		  //vertical
		  for(int row = 0; row < board.length - 3; row++)
		  {
			  for(int col = 0; col < board[0].length; col++)
			  {
				  if((board[row][col].getToken() == board[row + 1][col].getToken()) && (board[row][col].getToken() == board[row + 2][col].getToken()))
				  {
					  return col;
				  }
			  }
		  }
		  return num;
	  }
	  /** Determine if the player with the specified token wins */
	  public boolean isWon(char token) 
	  {
		  boolean win = false;
		  //horizontal
		  for(int row = 0; row < board.length; row++)
		  {
			  for(int col = 0; col < board[0].length - 3; col++)
			  {
				  if((board[row][col].getToken() == board[row][col + 1].getToken()) && (board[row][col].getToken() == board[row][col + 2].getToken()) && (board[row][col].getToken() == board[row][col + 3].getToken()))
				  {
					  if(board[row][col].getToken() == token)
					  {
						  win = true;
					  }
				  }
			  }
		  }
		  /** Vertical */
		  for(int row = 0; row < board.length - 3; row++)
		  {
			  for(int col = 0; col < board[0].length; col++)
			  {
				  if((board[row][col].getToken() == board[row + 1][col].getToken()) && (board[row][col].getToken() == board[row + 2][col].getToken()) && (board[row][col].getToken() == board[row + 3][col].getToken()))
				  {
					  if(board[row][col].getToken() == token)
					  {
						  win = true;
					  }
				  }
			  }
		  }
		  /** Diagonal \ */
		  for(int row = 0; row < board.length - 3; row++)
		  {
			  for(int col = 0; col < board[0].length - 3; col++)
			  {
				  if((board[row][col].getToken() == board[row + 1][col + 1].getToken()) && (board[row][col].getToken() == board[row + 2][col + 2].getToken()) && (board[row][col].getToken() == board[row + 3][col + 3].getToken()))
				  {
					  if(board[row][col].getToken() == token)
					  {
						  win = true;
					  }
				  }
			  }
		  }
		  /** Diagonal / */
		  for(int row = board.length - 1; row > 2; row--)
		  {
			  for(int col = 0; col < board[0].length - 3; col++)
			  {
				  if((board[row][col].getToken() == board[row - 1][col + 1].getToken()) && (board[row][col].getToken() == board[row - 2][col + 2].getToken()) && (board[row][col].getToken() == board[row - 3][col + 3].getToken()))
				  {
					  if(board[row][col].getToken() == token)
					  {
						  win = true;
					  }
				  }
			  }
		  }
		  return win;
	  }
	  
	  //An inner class for a cell
	  public class Cell extends Pane 
	  {
		  // Token used for this cell
		  private char token = ' ';	

		  public Cell() 
		  {
			  setStyle("-fx-border-color: black");
			  this.setPrefSize(2000, 2000);
		  }

		  /** Return token */
		  public char getToken() 
		  {
			  return token;
		  }

		  /** Set a new token */
		  public void setToken(char c) 
		  {
			token = c;

			if (token == 'X') 
			{
				Ellipse ellipse = new Ellipse(this.getWidth() / 2,
				this.getHeight() / 2, this.getWidth() / 2 - 10,
			    this.getHeight() / 2 - 10);
		        ellipse.centerXProperty().bind(this.widthProperty().divide(2));
				ellipse.centerYProperty().bind(this.heightProperty().divide(2));
				ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
				ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
				ellipse.setStroke(Color.BLACK);
				ellipse.setFill(Color.RED);
				getChildren().add(ellipse);
			}
			else if (token == 'O') 
			{
			    Ellipse ellipse = new Ellipse(this.getWidth() / 2,
			    this.getHeight() / 2, this.getWidth() / 2 - 10,
			    this.getHeight() / 2 - 10);
			    ellipse.centerXProperty().bind(this.widthProperty().divide(2));
			    ellipse.centerYProperty().bind(this.heightProperty().divide(2));
			    ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
			    ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
			    ellipse.setStroke(Color.BLACK);
			    ellipse.setFill(Color.BLUE);
			    getChildren().add(ellipse); // Add the ellipse to the pane
			}
			
		  }
	  }
}
