package main;
import java.util.Scanner;
public class TicTacToeGame
{
    TicTacToeBoard board;
    public TicTacToeGame()
    {
        this.board = new TicTacToeBoard();
    }
    public void start()
    {
        while(true)
        {
            System.out.println(board.boardString());
            if (board.checkWin())
            {
                System.out.println("The winner is: " + (board.winner == 1 ? "X" : "O"));
                break;
            }
            if(board.counter > 9)
            {
                System.out.println("draw");
                break;
            }
            Scanner input = new Scanner(System.in);
            if(board.counter % 2 != 0)
            {
                System.out.print("Current turn: X \nWhere do you want to place next? ");
            }
            else 
            {
                System.out.print("Current turn: O \nWhere do you want to place next? ");
            }

            int pos;
            try 
            {
                pos = Integer.parseInt(input.next());
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("invalid");
                continue; 
            }
            if (pos < 1 || pos > 9) 
            {
                System.out.println("invalid");
                continue;
            }


            if(pos <= 3)
            {
                board.setToBoard(pos - 1, 0);
            }
            else if(pos > 3 && pos <= 6)
            {
                board.setToBoard(pos - 4, 1);
            }
            else if(pos > 6)
            {
                board.setToBoard(pos - 7, 2);
            }
            

        }
    }
}
