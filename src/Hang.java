import java.util.Scanner;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import static java.nio.file.StandardOpenOption.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Hang
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String[] wordBank = {"corona", "virus"};
        Path gameScore = Paths.get("/Users/meghannahnybida/Desktop/CSA/Hangman/src/GameInfo");
        String delimiter = "|";
        FileChannel fcIn = null;
        String userEntry;
        int missesCount = 0;
        char[] misses = new char[6];
        boolean letterGuessed = false;
        boolean wordGuessed = false;
        WinsLosses count = new WinsLosses();

        String randomWord = wordBank[ (int)(Math.random() * wordBank.length) ];
        char[] wordToGuess = new char[randomWord.length()];
        for(int i = 0; i < randomWord.length(); i++)
        {
            wordToGuess[i] = '_';f
        }

        while(!wordGuessed && missesCount < 5)
        {
            System.out.println( "------------------------------------\n" );
            System.out.println( "You have " + (5 - missesCount) + " turns left." );
            System.out.print( "Unknown Word:\t" );
            for( int i = 0; i < randomWord.length(); i++ ){
                System.out.print( wordToGuess[i] + " " );
            }

            System.out.print("\nMisses: ");

            for(int i = 0; i < misses.length; i++){
                System.out.print(misses[i]);
            }
            System.out.print("\nGuess: ");
            userEntry = input.next();
            letterGuessed = false;

            for(int i = 0; i < randomWord.length(); i++ ){
                if (userEntry.charAt(0) == randomWord.charAt(i)){
                    wordToGuess[i] = randomWord.charAt(i);
                    letterGuessed = true;
                }
            }
            if(!letterGuessed){
                missesCount++;
                misses[missesCount] = userEntry.charAt(0);
            }

            int hidden_count = 0;
            for(int i = 0; i < randomWord.length(); i++ ){
                if( '_' == wordToGuess[i])
                    hidden_count++;
            }
            if(hidden_count > 0)
                wordGuessed = false;
            else
                wordGuessed = true;
        }
        if(wordGuessed == false) {
            gameStats(randomWord, wordToGuess, missesCount, misses);
            System.out.println("\nGame Result: Lost");
            count.setLosses(count.getLosses() + 1);
            System.out.println( "------------------------------------" );
        }
        else {
            gameStats(randomWord, wordToGuess, missesCount, misses);
            System.out.println("\nGame Result: Won");
            count.setWins(count.getWins() + 1);
            System.out.println( "------------------------------------" );
        }
        gameLoop();
        try {
            fcIn = (FileChannel) Files.newByteChannel(gameScore, CREATE, WRITE);
            String s = "Wins: " + count.getWins() + delimiter + "Losses: " + count.getLosses();
            byte data[] = s.getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(data);
            fcIn.write(buffer);
            fcIn.close();

        } catch (Exception e) {
            System.out.println("Error message: " + e);
        }
    }
    public static void gameStats(String randomWord, char[] wordToGuess, int missesCount, char[] misses){
        System.out.println( "------------------------------------\n" );
        System.out.println( "You have " + (5 - missesCount) + " turns left." );
        System.out.print( "Word:\t" );
        for( int i = 0; i < randomWord.length(); i++ ){
            System.out.print( wordToGuess[i] + " " );
        }
        System.out.print("\nMisses: ");
        for( int i = 0; i < misses.length; i++ ){
            System.out.print( misses[i] );
        }
    }
    public static void gameLoop(){
        Scanner input = new Scanner(System.in);
        System.out.println("\nDo you want to play again? (y/n) ");
        String playAgain = input.nextLine();
        if(playAgain.equals("y")){
            Hang.main(null);
        }
        else{
            System.out.println("Thanks for playing!");
        }
    }
}
//finish write to file
