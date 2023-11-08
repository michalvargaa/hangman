import java.util.Random;
import java.util.Scanner;

public class Main {
    static String getRandomWord(String[] words) {
        if (words.length == 0)
            return null;

        Random r = new Random();

        return words[r.nextInt(words.length)];

    }
    static char[] setupInitialState(String wordToGuess) {
        char[] guessed = new char[wordToGuess.length()];
        for (int i = 0; i < guessed.length; i++) 
            guessed[i] = '_';
        return guessed;
    }
    static boolean updateStateArray(String wordToGuess, char[] state, char guess) {
        boolean found = false;
        for (int i = 0; i < state.length; i++) {

            if (guess == wordToGuess.charAt(i)) {
                state[i] = guess;
                found = true;
            }

        }
        return found;
    }
    static boolean isWordGuessed(char[] state) {
        for (char a : state) {
            if (a == '_') 
                return false;
        }
        return true;
    }
    static void printASCIIHangman(int incorrectGuesses) {
        // ascii -> https://gist.github.com/chrishorton/8510732aa9a80a03c829b09f12e20d9c
        String[] ascii = {
            "  +---+\n  |   |\n      |\n      |\n      |\n      |\n=========", 
            "  +---+\n  |   |\n  O   |\n      |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |\n=========", 
            "  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n========="
        };

        System.out.println(ascii[incorrectGuesses]);
    }
    static void playGame(String wordToGuess, char[] guessed) {
        Scanner sc = new Scanner(System.in);
        int incorrectGuesses = 0;
        while (incorrectGuesses < 6) {
            // print state + flush terminal
            System.out.print("\033[H\033[2J");
            System.out.println("Uhádni slovo: " + String.valueOf(guessed) + "(" + wordToGuess.length() + ")");
            printASCIIHangman(incorrectGuesses);
            // get letter
            System.out.print("Hádaj: ");
            char guess = sc.next().charAt(0);

            // check if it is in the array
            boolean isGuessCorrect = updateStateArray(wordToGuess, guessed, guess);

            if (!isGuessCorrect)
                incorrectGuesses++;
            
            
            if (isWordGuessed(guessed)) {
                
                System.out.println("Uhádol si! " + wordToGuess);
                break;
            }

        }

        if (incorrectGuesses >= 6) {
            System.out.print("\033[H\033[2J");
            printASCIIHangman(incorrectGuesses);
            System.out.println("Neuhádol si! Hľadané slovo bolo :  " + wordToGuess);
        }

    }
    
    public static void main(String[] args) {
        String[] words = new String[] {"informatika", "programovanie", "monitor", "ruka", "rodina", "mobil", "obal", "jedlo", "pero", "peniaze"};

        String wordToGuess = getRandomWord(words);
        char[] guessed = setupInitialState(wordToGuess); // "word" -> ['_', '_', '_', '_']
        playGame(wordToGuess, guessed);
    


    }
}
