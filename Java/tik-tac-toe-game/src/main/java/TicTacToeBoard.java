package main;
import java.util.Arrays;
public class TicTacToeBoard 
{
    private String[][] board;
    protected int counter;
    public int winner;
    public TicTacToeBoard()
    {
        board = new String[3][3];
        Arrays.fill(board[0], "_");
        Arrays.fill(board[1], "_");
        Arrays.fill(board[2], "_");
        counter = 1;
    }   

    public void setToBoard(int pos, int row)
    {
        if(board[row][pos] != "_")
        {
            System.out.println("invalid");
        }
        else
        {
            if(counter % 2 != 0)
            {
                board[row][pos] = "X";
            }
            else 
            {
                board[row][pos] = "O";   
            }
            counter++;
        }
    }
    public String boardString()
    {
        StringBuilder boardRepresentation = new StringBuilder();
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                boardRepresentation.append(board[i][j]).append(" ");
            }
            boardRepresentation.append("\n");
        }
        return boardRepresentation.toString();
    }

    public boolean checkWin()
    {
        // check win | - vertically
        for (int col = 0; col < 3; col++){
            if (isEqual(board[0][col], board[1][col], board[2][col]))
            {
                winner = board[0][col].equals("X") ? 1 : -1;
                return true;
            }
        }
        //check win -- horizontally
        for (int row = 0; row < 3; row++)
        {
            if (isEqual(board[row][0], board[row][1], board[row][2]))
            {
                winner = board[row][0].equals("X") ? 1 : -1;
                return true;
            }
        }
        //check win diagonally \
        if (isEqual(board[0][0], board[1][1], board[2][2]))
        {
            winner = board[0][0].equals("X") ? 1 : -1;
            return true;
        }

        //check win diagonally /
        if (isEqual(board[2][0], board[1][1], board[0][2]))
        {
            winner = board[0][2].equals("X") ? 1 : -1;
            return true;
        }
        /*if all fields are used --> draw*/
        return false;
    }

    
    private boolean isEqual(String a, String b, String c)
    {
        return a.equals(b) && b.equals(c) && !a.equals("_");
    }



}
