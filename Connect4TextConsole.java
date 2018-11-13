/**
@author Kai Wood
@version 09/30/18
*/ 
package ui;

/**methods that are utilized in a game of Connect4*/
public class Connect4TextConsole 
{
	//initialize variables
    private char[][] board;
    private char player;
    private int turn;
    //Constructor
    public Connect4TextConsole()
    {
        board = new char[6][7];
        player = 'X';
        turn = 0;   
    }
    //returns player
    public String getPlayer()
    {
        if(turn % 2 == 0)
        {
            return "PlayerX";
        }
        else
        {
            return "PlayerO";
        }
    }
    //resets the board
    public void restartBoard()
    {
        for(int row = 0; row < board.length; row++)
        {
            for(int col = 0; col < board[0].length; col++)
            {
                board[row][col] = ' ';
            }
        }
    }
    //changes the player's turn
    public void switchPlayer()
    {
        turn++;
        if(turn % 2 == 0)
        {
        	player = 'X';
        }
        else
        {
        	player = 'O';
        }
    }
    //prints out what the current board looks like
    public void drawBoard()
    {
        for(int row = 0; row < board.length; row++)
        {
            for(int col = 0; col < board[0].length; col++)
            {
                System.out.print("|" + board[row][col]);
            } 
            System.out.print("|\n");
        } 
    }
    //Gets the player's move and then prints the new board with that move
    public void getMove(int col)
    {
        for(int i = board.length - 1; i >= 0; i--)
        {
	        if(board[i][col]== ' ')
	        {
	            board[i][col] = player;
	            drawBoard();
	            break;
	        }
        }
    }
    
    public boolean isFull(int col)
    {
    	int count = 0;
    	for(int i = board.length - 1; i >= 0; i--)
        {
	        if(board[i][col]== ' ')
	        {
	        	count++;
	        }
        }
    	if(count == 0 && (col >= 0 && col <= 6))
        {
        	System.out.println("Column is already full. Try another \n");
            return true;
        }
    	return false;
    }
    //checks if either player has won the game
    public boolean checkForWin()
    {
        boolean win = false;
        //horizontal
        for(int row = 0; row < board.length; row++)
        {
        	for(int col = 0; col < board[0].length - 3; col++)
        	{
	            if((board[row][col] == board[row][col + 1]) && (board[row][col] == board[row][col + 2]) && (board[row][col] == board[row][col + 3]))
	            {
	            	if(board[row][col] == 'X' || board[row][col] == 'O')
	                {
	                    win = true;
	                }
	            }
        	}
        }
        //vertical
        for(int row = 0; row < board.length - 3; row++)
        {
        	for(int col = 0; col < board[0].length; col++)
        	{
	            if((board[row][col] == board[row + 1][col]) && (board[row][col] == board[row + 2][col]) && (board[row][col] == board[row + 3][col]))
	            {
	                if(board[row][col] == 'X' || board[row][col] == 'O')
	                {
	                    win = true;
	                }
	            }
        	}
        }
        //diagonal \
        for(int row = 0; row < board.length - 3; row++)
        {
        	for(int col = 0; col < board[0].length - 3; col++)
        	{
	            if((board[row][col] == board[row + 1][col + 1]) && (board[row][col] == board[row + 2][col + 2]) && (board[row][col] == board[row + 3][col + 3]))
	            {
	                if(board[row][col] == 'X' || board[row][col] == 'O')
	                {
	                    win = true;
	                }
	            }
        	}
        }
        //diagonal /
        for(int row = board.length - 1; row > 2; row--)
        {
        	for(int col = 0; col < board[0].length - 3; col++)
        	{
	            if((board[row][col] == board[row - 1][col + 1]) && (board[row][col] == board[row - 2][col + 2]) && (board[row][col] == board[row - 3][col + 3]))
	            {
	                if(board[row][col] == 'X' || board[row][col] == 'O')
	                {
	                    win = true;
	                }
	            }
        	}
        }
        return win;
    }
    //checks if there are any possible moves remaining
    public boolean checkIfFull()
    {
        int count = 0;
        boolean full = false;
        for(int row = 0; row < 6 ; row++)
        {
            for(int col = 0; col < 7; col++)
            {
                if(board[row][col] == ' ')
                {
                    count++;
                }
            }
        }
        if(count == 0)
        {
            full = true;
        }
        return full;
    }
    
    //checks to see if the AI can block a Player's move if they have 3 in a row
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
        		if(board[row][col] == 'X')
        		{
        			countX++;
        		}
        		if(board[row][col] == ' ')
        		{
        			blank = col;
        		}
        		if(board[row][col+1] == 'X')
	            {
        			countX++;
	            }
        		if(board[row][col+1] == ' ')
        		{
        			blank = col+1;
        		}
        		if(board[row][col+2] == 'X')
	            {
        			countX++;
	            }
        		if(board[row][col+2] == ' ')
        		{
        			blank = col+2;
        		}
        		if(board[row][col+3] == 'X')
	            {
        			countX++;
	            }
        		if(board[row][col+3] == ' ')
        		{
        			blank = col+3;
        		}
        		if(countX == 3 && blank != -1 && blank < 7 && board[row][col] != ' ')
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
	            if((board[row][col] == board[row + 1][col]) && (board[row][col] == board[row + 2][col]))
	            {
	            	return col;
	            }
        	}
        }
        return num;
    }
}