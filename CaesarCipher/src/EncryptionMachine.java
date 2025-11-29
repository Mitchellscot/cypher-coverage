/**
 * A class that implements a simple Caesar cipher encryption machine for encrypting words
 * using a fixed alphabet and shift value. It provides functionality to encrypt individual
 * characters and words, and includes a console-based interface for user interaction.
 */
import java.util.Scanner;

public class EncryptionMachine {
    /** The standard English alphabet used for encryption. */
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    /** Message template used when outputting encrypted words. */
    private static final String ENCRYPT_MESSAGE = " has been encrypted to: ";

    /** The number of positions to shift each character during encryption. */
    public static final int SHIFT = 3;

    /**
     * Encrypts a given word by applying a Caesar cipher with a fixed shift to each character.
     *
     * @param word the word to encrypt, containing only lowercase letters from the alphabet
     * @return the encrypted word
     * @throws Exception if the word contains characters outside the standard alphabet (a-z)
     */
    public static String encryptWord(String word) throws Exception {
        StringBuilder sb = new StringBuilder();

        for (char c : word.toCharArray()) {
            sb.append(EncryptionMachine.encryptLetter(c));
        }

        return sb.toString();
    }

    /**
     * Encrypts a single letter using a Caesar cipher with a fixed shift.
     *
     * @param letter the letter to encrypt, must be a lowercase letter (a-z)
     * @return the encrypted letter
     * @throws IllegalArgumentException if the letter is not a lowercase letter in the alphabet
     */
    public static char encryptLetter(char letter) throws Exception {
        int charIndex = ALPHABET.indexOf(letter);
        if (charIndex == -1) {
            throw new IllegalArgumentException("Only standard English alphabet letters a-z are allowed");
        }

        // Stay within the bounds of the given alphabet
        int wantedChar = (charIndex + SHIFT) % ALPHABET.length();

        return ALPHABET.charAt(wantedChar);
    }

    /**
     * Outputs the original word and its encrypted version to the console.
     *
     * @param originalWord the original word before encryption
     * @param encryptedWord the encrypted word
     */
    public static void outputEncryptedWord(String originalWord, String encryptedWord) {
        System.out.println("\"" + originalWord + "\"" + ENCRYPT_MESSAGE + "\"" + encryptedWord + "\"");
    }

    /**
     * Outputs a welcome message to the console, introducing the encryption machine.
     */
    public static void outputStartMessage() {
        System.out.println("""
                    Welcome to the CSCI717 Encryption Machine Construction
                    The program lets you encrypt a message
                    with a key for your recipient to decrypt!
                    """);
    }

    /**
     * Outputs a completion message to the console after encryption is finished.
     */
    public static void outputEndMessage() {
        System.out.println("Message fully encrypted. Happy secret Messaging!");
    }

    /**
     * Prompts the user to enter a key and outputs its encrypted version.
     *
     * @param scanner the Scanner object for reading user input
     * @throws Exception if the key contains invalid characters
     */
    public static void encryptKey(Scanner scanner) throws Exception {
        System.out.println("Enter a key:");
        String key = scanner.nextLine();
        String encryptedKey = EncryptionMachine.encryptWord(key);
        EncryptionMachine.outputEncryptedWord(key, encryptedKey);
    }

    /**
     * Handles the encryption of multiple words based on user input.
     * Prompts the user for the number of words and each word to encrypt.
     *
     * @param scanner the Scanner object for reading user input
     * @throws Exception if any word contains invalid characters
     */
    public static void wordEncryptionLoop(Scanner scanner) throws Exception {
        System.out.println("\nHow many words in your message?:");
        int words = 0;

        try {
            // Attempt to parse the input as an integer
            words = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println("A number was expected to be entered.");
            return;
        }

        if (words <= 0) {
            System.out.println("There were no words in the message.");
        } else {
            for (int i = 0; i < words; i++) {
                System.out.println("\nNext word:");
                String word = scanner.nextLine();
                String encryptedWord = EncryptionMachine.encryptWord(word);
                EncryptionMachine.outputEncryptedWord(word, encryptedWord);
            }
        }
    }

    /**
     * The main entry point for the program.
     *
     * @param args command-line arguments (not used)
     * @throws RuntimeException if an error occurs during execution
     */
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            EncryptionMachine.outputStartMessage();
            EncryptionMachine.encryptKey(scanner);
            EncryptionMachine.wordEncryptionLoop(scanner);
            EncryptionMachine.outputEndMessage();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}