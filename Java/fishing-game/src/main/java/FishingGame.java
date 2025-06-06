import java.util.Arrays;
import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
public class FishingGame 
{
    private String [][] board; 
    public FishingGame()
    {
        ArrayList<Fishable> fishlist = new ArrayList<>();
        
        int x;
        int y;
        int totalweight = 0;
        int score = 0;
        int attempts = 0;
        String boundprove = "";

        System.out.println("--------- Fishing Game ---------\nWelcome to the pond. Good luck!");

        this.board = new String[4][4];
       
        Arrays.fill(board[0], "▓");
        Arrays.fill(board[1], "▓");
        Arrays.fill(board[2], "▓");
        Arrays.fill(board[3], "▓");
    
        while(true)
        {
            System.out.println("\n" + boardString());

            if (totalweight >= 1000 || attempts > 16)
            {
                System.out.println("Game over! Your score is " + score);
                break;
            }

            Scanner input = new Scanner(System.in);
            System.out.print("Select an X-Coordinate: ");
            try
            {
                x = Integer.parseInt(input.next());
                boundprove = board[x-1][0];
            }
            catch(NumberFormatException e)
            {
                System.out.println("Invalid number");
                continue;
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                System.out.println("X is out of bounds");
                continue;
            }
            
            System.out.print("Select a Y-Coordinate: ");
            try
            {
                y = Integer.parseInt(input.next()); 
                boundprove = board[0][y-1];
            }
            catch(NumberFormatException e)
            {
                System.out.println("Invalid number");
                continue;
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Y is out of bounds");
                continue;
            }
            try
            {
                if(board[x-1][y-1].equals("░"));
                {
                    x = -100;
                    boundprove = board[x-1][y];
                }
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                System.out.println("This pos is not available");
            }
            
            
            
            fishlist.add(new Pollan());
            fishlist.add(new VoidFish());
            fishlist.add(new RepulsiveKaraka());
            fishlist.add(new costianecatrix());
            Collections.shuffle(fishlist);
            System.out.println("\nYou fished a " + fishlist.getFirst().getName() + "\nWeight: " + fishlist.getFirst().getWeight() + "\nValue: " + fishlist.getFirst().getValue());
            totalweight += fishlist.getFirst().getWeight();
            score += fishlist.getFirst().getValue();
            System.out.println("Your current score is: " + score);
            System.out.println("The total weight is: " + totalweight);
            fishlist.clear();
            attempts++;
            
            
            board[x-1][y-1] = "░";
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
            boardRepresentation.append("\n\n");
        }
        return boardRepresentation.toString();
    }
}
