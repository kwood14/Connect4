/**
@author Kai Wood
@version 11/12/2018
*/ 
package core;

import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import ui.Connect4GUI.Cell;

public class Connect4Client extends Application 
    implements Connect4Constants 
    {
		// Indicate whether the player has the turn
	    private boolean myTurn = false;

	    // Indicate the token for the player
	    private char myToken = ' ';

	    // Indicate the token for the other player
	    private char otherToken = ' ';

		// Create and initialize cell
		private Cell[][] board = new Cell[6][7];

		// Create and initialize a title label
		private Label lblTitle = new Label();
		  
		// Create and initialize a status label
		private Label lblStatus = new Label("Red's Turn");
		
		// Indicate selected row and column by the current move
		private int rowSelected;
		private int columnSelected;

		// Input and output streams from/to server
		private DataInputStream fromServer;
		private DataOutputStream toServer;

		// Continue to play?
		private boolean continueToPlay = true;

		// Wait for the player to mark a cell
		private boolean waiting = true;

		// Host name or ip
		private String host = "localhost";

		@Override // Override the start method in the Application class
		public void start(Stage primaryStage) 
		{
			// Pane to hold cell
			GridPane pane = new GridPane();
					
			for (int i = 0; i < 6; i++)
			{
				for (int j = 0; j < 7; j++)
				{
					pane.add(board[i][j] = new Cell(j, j), j, i);
				}
			}
			BorderPane borderPane = new BorderPane();
							
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
			borderPane.setTop(hBox);
					
			/** Setting up the buttons */
			bt1.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override // Override the handle method
				public void handle(ActionEvent e) 
				{
					// if the player has the turn
					if (myTurn) 
					{
						//if the column isn't full
						if(!isFull(0))
						{
							int row = getLowest(0);
							board[row][0].setToken(myToken);
							myTurn = false;
							rowSelected = row;
							columnSelected = 0;
							lblStatus.setText("Waiting for the other player to move");
							waiting = false; // Just completed a successful move
						}
					}
				}
			});
			bt2.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override // Override the handle method
				public void handle(ActionEvent e) 
				{
					// if the player has the turn
					if (myTurn) 
					{
						//if the column isn't full
						if(!isFull(1))
						{
							int row = getLowest(1);
							board[row][1].setToken(myToken);
							myTurn = false;
							rowSelected = row;
							columnSelected = 1;
							lblStatus.setText("Waiting for the other player to move");
							waiting = false; // Just completed a successful move
						}
					}
				}
			});
			bt3.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override // Override the handle method
				public void handle(ActionEvent e) 
				{
					// if the player has the turn
					if (myTurn) 
					{
						//if the column isn't full
						if(!isFull(2))
						{
							int row = getLowest(2);
							board[row][2].setToken(myToken);
							myTurn = false;
							rowSelected = row;
							columnSelected = 2;
							lblStatus.setText("Waiting for the other player to move");
							waiting = false; // Just completed a successful move
						}
					}
				}
			});
			bt4.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override // Override the handle method
				public void handle(ActionEvent e) 
				{
					// if the player has the turn
					if (myTurn) 
					{
						//if the column isn't full
						if(!isFull(3))
						{
							int row = getLowest(3);
							board[row][3].setToken(myToken);
							myTurn = false;
							rowSelected = row;
							columnSelected = 3;
							lblStatus.setText("Waiting for the other player to move");
							waiting = false; // Just completed a successful move
						}
					}	
				}
			});
			bt5.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override // Override the handle method
				public void handle(ActionEvent e) 
				{
					// if the player has the turn
					if (myTurn) 
					{
						//if the column isn't full
						if(!isFull(4))
						{
							int row = getLowest(4);
							board[row][4].setToken(myToken);
							myTurn = false;
							rowSelected = row;
							columnSelected = 4;
							lblStatus.setText("Waiting for the other player to move");
							waiting = false; // Just completed a successful move
						}
					}	
				}	
			});	
			bt6.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override // Override the handle method
				public void handle(ActionEvent e) 
				{
					// if the player has the turn
					if (myTurn) 
					{
						//if the column isn't full
						if(!isFull(5))
						{
							int row = getLowest(5);
							board[row][5].setToken(myToken);
							myTurn = false;
							rowSelected = row;
							columnSelected = 5;
							lblStatus.setText("Waiting for the other player to move");
							waiting = false; // Just completed a successful move
						}
					}
				}
			});
			bt7.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override // Override the handle method
				public void handle(ActionEvent e) 
				{
					// if the player has the turn
					if (myTurn) 
					{
						//if the column isn't full
						if(!isFull(6))
						{
							int row = getLowest(6);
							board[row][6].setToken(myToken);
							myTurn = false;
							rowSelected = row;
							columnSelected = 6;
							lblStatus.setText("Waiting for the other player to move");
							waiting = false; // Just completed a successful move
						}
					}
				}
			});
				
		    //borderPane.setTop(lblTitle);
		    borderPane.setCenter(pane);
		    borderPane.setBottom(lblStatus);
		    
		    // Create a scene and place it in the stage
		    Scene scene = new Scene(borderPane, 320, 350);
		    primaryStage.setTitle("Connect4Client"); // Set the stage title
		    primaryStage.setScene(scene); // Place the scene in the stage
		    primaryStage.show(); // Display the stage   

		    // Connect to the server
		    connectToServer();
  }

  private void connectToServer() 
  {
	  try 
	  {
		  // Create a socket to connect to the server
		  Socket socket = new Socket(host, 8000);

		  // Create an input stream to receive data from the server
		  fromServer = new DataInputStream(socket.getInputStream());
	
		  // Create an output stream to send data to the server
		  toServer = new DataOutputStream(socket.getOutputStream());
	  }
	  catch (Exception ex) 
	  {
		  ex.printStackTrace();
	  }

	  // Control the game on a separate thread
	  new Thread(() -> 
	  {
		  try 
		  {
			  // Get notification from the server
			  int player = fromServer.readInt();
  
			  // Am I player 1 or 2?
			  if (player == PLAYER1) 
			  {
				  myToken = 'X';
				  otherToken = 'O';
				  Platform.runLater(() -> 
				  {
					  lblTitle.setText("Player 1 with token 'X'");
					  lblStatus.setText("Waiting for player 2 to join");
				  });
  
				  // Receive startup notification from the server
				  fromServer.readInt(); // Whatever read is ignored
  
				  // The other player has joined
				  Platform.runLater(() -> 
				  lblStatus.setText("Player 2 has joined. I start first"));
  
				  // It is my turn
				  myTurn = true;
			  }
			  else if (player == PLAYER2) 
			  {
				  myToken = 'O';
				  otherToken = 'X';
				  Platform.runLater(() -> 
				  {
					  lblTitle.setText("Player 2 with token 'O'");
					  lblStatus.setText("Waiting for player 1 to move");
				  });
			  }
  
			  // Continue to play
			  while (continueToPlay) 
			  {      
				  if (player == PLAYER1) 
				  {
					  waitForPlayerAction(); // Wait for player 1 to move
					  sendMove(); // Send the move to the server
					  receiveInfoFromServer(); // Receive info from the server
				  }
				  else if (player == PLAYER2) 
				  {
					  receiveInfoFromServer(); // Receive info from the server
					  waitForPlayerAction(); // Wait for player 2 to move
					  sendMove(); // Send player 2's move to the server
				  }
			  }
		  }
		  catch (Exception ex) 
		  {
			  ex.printStackTrace();
		  }
	  }).start();
  	}

  /** Wait for the player to mark a cell */
  private void waitForPlayerAction() throws InterruptedException 
  {
	  while (waiting) 
	  {
		  Thread.sleep(100);
	  }
	  waiting = true;
  }

  /** Send this player's move to the server */
  private void sendMove() throws IOException 
  {
	  toServer.writeInt(rowSelected); // Send the selected row
	  toServer.writeInt(columnSelected); // Send the selected column
  }

  /** Receive info from the server */
  private void receiveInfoFromServer() throws IOException 
  {
	  // Receive game status
	  int status = fromServer.readInt();

	  if (status == PLAYER1_WON) 
	  {
		  // Player 1 won, stop playing
		  continueToPlay = false;
		  if (myToken == 'X') 
		  {
			  Platform.runLater(() -> lblStatus.setText("I won! (Red)"));
		  }
		  else if (myToken == 'O') 
		  {
			  Platform.runLater(() -> 
			  lblStatus.setText("Player 1 (Red) has won!"));
			  receiveMove();
		  }
	  }
    else if (status == PLAYER2_WON) 
    {
    	// Player 2 won, stop playing
    	continueToPlay = false;
    	if (myToken == 'O') 
    	{
    		Platform.runLater(() -> lblStatus.setText("I won! (Blue)"));
    	}
    	else if (myToken == 'X') 
    	{
    		Platform.runLater(() -> 
    		lblStatus.setText("Player 2 (Blue) has won!"));
    		receiveMove();
    	}
    }
    else if (status == DRAW) 
    {
    	// No winner, game is over
    	continueToPlay = false;
    	Platform.runLater(() -> 
    	lblStatus.setText("Game is over, no winner!"));

      if (myToken == 'O') 
      {
    	  receiveMove();
      }
    }
    else 
    {
    	receiveMove();
    	Platform.runLater(() -> lblStatus.setText("My turn"));
    	myTurn = true; // It is my turn
    }
  }

  private void receiveMove() throws IOException 
  {
	  // Get the other player's move
	  int row = fromServer.readInt();
	  int column = fromServer.readInt();
	  Platform.runLater(() -> board[row][column].setToken(otherToken));
  }

  // An inner class for a cell
  public class Cell extends Pane 
  {
	  // Indicate the row and column of this cell in the board
	  private int row;
	  private int column;

	  // Token used for this cell
	  private char token = ' ';

	  public Cell(int row, int column) 
	  {
		  this.row = row;
		  this.column = column;
		  this.setPrefSize(2000, 2000); // What happens without this?
		  setStyle("-fx-border-color: black"); // Set cell's border 
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
		  repaint();
	  }

	  protected void repaint() 
	  {
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
  /** Get the lowest (greatest value) index in that given column */
  public int getLowest(int col)
  {
	  try
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
	  catch(ArrayIndexOutOfBoundsException ex)
	  {
		  System.out.println("Array Index Out of Bounds");
		  return 0;
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

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
