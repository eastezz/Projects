import java.util.Objects;
import java.util.Scanner;
import java.util.Arrays;
public class HangmanGame {
    public static void main(String[] args)
    {
        int attempts = 6;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the secret word: ");
        String name = sc.nextLine().toLowerCase();
        char[] word = name.toCharArray();
        int  concatenation = word.length;
        char[] symbol = new char[word.length];
        while (concatenation > 0)
        {
            Arrays.fill(symbol, '_');
            concatenation--;
        }
        String different = String.valueOf(symbol);
        concatenation = word.length;
        while (attempts > 0)
        {
                if(name.equals(String.valueOf(symbol)))
                {
                System.out.println("Congratulations! You`ve guessed the word: " + name);
                break;
                }
                System.out.println("Current progress: ");
                while (concatenation > 0)
                {
                    for (int i = 0; i < symbol.length; i++) {
                        System.out.print(symbol[i] + " ");
                        concatenation--;
                    }
                }
                concatenation = word.length;
                System.out.println("\nYou have " + attempts + " wrong guesses left.");
                System.out.print("Guess a letter: ");
                char s = sc.next().toLowerCase().charAt(0);
                for (int i = 0; i < word.length; i++)
                {
                    if (word[i] == s)
                    {
                      symbol[i] = word[i];

                    }
                }
                if(different.equals(String.valueOf(symbol)))
                {
                    System.out.println("Wrong guess!");
                    attempts--;
                }
                else
                {
                    different = String.valueOf(symbol);
                }
            if (attempts == 0)
            {
                System.out.println("Game Over! :( You tried to guess the word: " + name);
            }

        }
    }
}
