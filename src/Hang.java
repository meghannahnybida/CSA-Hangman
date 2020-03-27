import java.util.Scanner;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import static java.nio.file.StandardOpenOption.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Hang
{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] wordBank = {"corona", "virus", "covid", "quarantine", "wuhan" };
        Path gameScore = Paths.get("/Users/meghannahnybida/Desktop/CSA/Hangman/src/GameInfo");
        String delimiter = "|";
        FileChannel fcIn = null;
        String userEntry;
        int missesCount = 0;
        char[] misses = new char[6];
        boolean letterGuessed;
        boolean wordGuessed = false;
        WhoWon count = new WhoWon();

        String randomWord = wordBank[(int) (Math.random() * wordBank.length)]; //chooses a random word from the word bank
        char[] wordToGuess = new char[randomWord.length()]; //turns the random word into a char array
        for(int i = 0; i < randomWord.length(); i++){ //fills the random word with underscores instead of letters
            wordToGuess[i] = '_';
        }

            while(!wordGuessed && missesCount < 5){
                System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                System.out.println("You have " + (5 - missesCount) + " misses left.");
                System.out.print("Unknown Word:\t");
                for(int i = 0; i < randomWord.length(); i++){
                    System.out.print(wordToGuess[i] + " ");
                }

                System.out.print("\nLetters Missed: ");

                for(int i = 0; i < misses.length; i++){
                    System.out.print(misses[i] + " ");
                }
                System.out.print("\nGuess a Letter: ");
                userEntry = input.next();
                letterGuessed = false;

                for(int i = 0; i < randomWord.length(); i++){
                    if(userEntry.charAt(0) == randomWord.charAt(i)){
                        wordToGuess[i] = randomWord.charAt(i);
                        letterGuessed = true;
                    }
                }
                if(!letterGuessed){
                    missesCount++;
                    count.setLettersMissed(count.getLettersMissed() + 1);
                    misses[missesCount] = userEntry.charAt(0);
                    pictures(missesCount);
                }

                int hidden_count = 0;
                for(int i = 0; i < randomWord.length(); i++){
                    if('_' == wordToGuess[i])
                        hidden_count++;
                }
                if(hidden_count > 0)
                    wordGuessed = false;
                else
                    wordGuessed = true;
            }
            if(wordGuessed == false){
                System.out.println( "------------------------------------" );
                System.out.println("Game Result: You Lost!");
                gameStats(randomWord, missesCount, misses);
                count.setCompWin(count.getCompWin() + 1);
            }
            else{
                System.out.println( "------------------------------------" );
                System.out.println("Game Result: You Won!");
                gameStats(randomWord, missesCount, misses);
                count.setUserWin(count.getUserWin() + 1);
            }
            //Write to the text file
        try{
            fcIn = (FileChannel) Files.newByteChannel(gameScore, CREATE, WRITE);
            String s = "You: " + count.getUserWin() + "  " + delimiter + "  Computer: " + count.getCompWin() + "  " + delimiter + "  # of Letters Missed: " + count.getLettersMissed();
            byte data[] = s.getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(data);
            fcIn.write(buffer);
            //Catch the error
        } catch(Exception e){
            System.out.println("Error message: " + e);
        }
    }

    //Displays the stats of the game
    public static void gameStats(String randomWord, int missesCount, char[] misses){
        System.out.println("Misses remaining: " + (5 - missesCount));
        System.out.print( "The word was:\t" + randomWord );
        System.out.print("\nYou missed the letters: ");
        for(int i = 0; i < misses.length; i++){
            System.out.print( misses[i] + " ");
        }
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    //Top notch graphics
    public static void pictures(int missesCount){
        if (missesCount == 1){
            System.out.println("Wrong, guess again >>");
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |             ");
            System.out.println("   |             ");
            System.out.println("   |             ");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("___|___");
        }
        if (missesCount == 2) {
            System.out.println("Wrong, guess again >>");
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        |     |");
            System.out.println("   |         \\_ _/");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("___|___");
        }
        if (missesCount == 3) {
            System.out.println("Wrong, guess again >>");
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        |     |");
            System.out.println("   |         \\_ _/");
            System.out.println("   |           |");
            System.out.println("   |           |");
            System.out.println("   |");
            System.out.println("___|___");
        }
        if (missesCount == 4) {
            System.out.println("Wrong, guess again >>");
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        |     |");
            System.out.println("   |         \\_ _/");
            System.out.println("   |           |");
            System.out.println("   |           |");
            System.out.println("   |          / \\ ");
            System.out.println("___|___      /   \\");
        }
        if (missesCount == 5) {
            System.out.println("Wrong, guess again >>");
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        | x x |");
            System.out.println("   |         \\_ _/");
            System.out.println("   |          _|_");
            System.out.println("   |         / | \\");
            System.out.println("   |          / \\ ");
            System.out.println("___|___      /   \\");
        }
    }
}