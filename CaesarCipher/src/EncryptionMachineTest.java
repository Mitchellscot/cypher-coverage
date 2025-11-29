
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EncryptionMachineTest {

    // ============================================
    // Test Group 1: Standard and Boundary Encryption
    // ============================================

    /**
     * BBT-1.1: Standard flow - basic encryption with shift of 3
     */
    @Test
    public void testBBT_1_1_StandardFlow() throws Exception {
        // Key: csci -> fvfl
        assertEquals("fvfl", EncryptionMachine.encryptWord("csci"));
        // Words: hello -> khoor, world -> zruog
        assertEquals("khoor", EncryptionMachine.encryptWord("hello"));
        assertEquals("zruog", EncryptionMachine.encryptWord("world"));
    }

    /**
     * BBT-1.2: Full Alphabet Wrap-Around - tests x->a, y->b, z->c wrap logic
     */
    @Test
    public void testBBT_1_2_FullAlphabetWrapAround() throws Exception {
        // Key: xyz -> abc
        assertEquals("abc", EncryptionMachine.encryptWord("xyz"));
        // Word: zebra -> cheud
        assertEquals("cheud", EncryptionMachine.encryptWord("zebra"));
        // Verify individual wrap-around letters
        assertEquals('a', EncryptionMachine.encryptLetter('x'));
        assertEquals('b', EncryptionMachine.encryptLetter('y'));
        assertEquals('c', EncryptionMachine.encryptLetter('z'));
    }

    /**
     * BBT-1.3: Start of Alphabet - encryption for characters at start (no wrap)
     */
    @Test
    public void testBBT_1_3_StartOfAlphabet() throws Exception {
        // Key: abc -> def
        assertEquals("def", EncryptionMachine.encryptWord("abc"));
        // Word: apple -> dssoh
        assertEquals("dssoh", EncryptionMachine.encryptWord("apple"));
    }

    /**
     * BBT-1.4: Middle of Alphabet - basic functionality for non-boundary characters
     */
    @Test
    public void testBBT_1_4_MiddleOfAlphabet() throws Exception {
        // Key: mid -> plg
        assertEquals("plg", EncryptionMachine.encryptWord("mid"));
        // Word: program -> surjudp
        assertEquals("surjudp", EncryptionMachine.encryptWord("program"));
    }

    /**
     * BBT-1.5: Single Character Input - minimum valid length (1 character)
     */
    @Test
    public void testBBT_1_5_SingleCharacterInput() throws Exception {
        // Key: a -> d
        assertEquals("d", EncryptionMachine.encryptWord("a"));
        // Word: z -> c
        assertEquals("c", EncryptionMachine.encryptWord("z"));
    }

    /**
     * BBT-1.6: Long Input - handling of very long strings
     */
    @Test
    public void testBBT_1_6_LongInput() throws Exception {
        // Key: antidisestablishmentarianism -> dqwlglvhvwdeolvkphqwduldqlvp
        assertEquals("dqwlglvhvwdeolvkphqwduldqlvp",
                EncryptionMachine.encryptWord("antidisestablishmentarianism"));
        // Word: pneumonoultramicroscopicsilicovolcanoconiosis ->
        // sqhxprqrxowudplfurvfrslfvlolfryrofdqrfrqlrvlv
        assertEquals("sqhxprqrxowudplfurvfrslfvlolfryrofdqrfrqlrvlv",
                EncryptionMachine.encryptWord("pneumonoultramicroscopicsilicovolcanoconiosis"));
    }

    // ============================================
    // Test Group 2: User Input Flow and Constraints
    // ============================================

    /**
     * BBT-2.1: Zero Words - program handles word count of 0
     * Note: This test only validates the key encryption, as the flow logic
     * is in the main method which is not directly unit testable
     */
    @Test
    public void testBBT_2_1_ZeroWords() throws Exception {
        // Key: test -> whvw
        assertEquals("whvw", EncryptionMachine.encryptWord("test"));
    }

    /**
     * BBT-2.2: Large Number of Words - verifies multiple word encryption
     */
    @Test
    public void testBBT_2_2_LargeNumberOfWords() throws Exception {
        // Key: hi -> kl
        assertEquals("kl", EncryptionMachine.encryptWord("hi"));
        // Words: one, two, three, four, five
        assertEquals("rqh", EncryptionMachine.encryptWord("one"));
        assertEquals("wzr", EncryptionMachine.encryptWord("two"));
        assertEquals("wkuhh", EncryptionMachine.encryptWord("three"));
        assertEquals("irxu", EncryptionMachine.encryptWord("four"));
        assertEquals("ilyh", EncryptionMachine.encryptWord("five"));
    }

    /**
     * BBT-2.3: Prompting and Output - verifies encryption logic for key and word
     * Note: Actual prompt verification would require integration testing
     */
    @Test
    public void testBBT_2_3_PromptingAndOutput() throws Exception {
        // Key: key -> nhb
        assertEquals("nhb", EncryptionMachine.encryptWord("key"));
        // Word: word -> zrug
        assertEquals("zrug", EncryptionMachine.encryptWord("word"));
    }

    // ============================================
    // Additional Test Cases for Branch Coverage
    // ============================================

    /**
     * Test invalid character - should throw IllegalArgumentException
     * This tests the branch in encryptLetter where charIndex == -1
     */
    @Test
    public void testInvalidCharacterUppercase() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            EncryptionMachine.encryptLetter('A');
        });
        assertEquals("Only standard English alphabet letters a-z are allowed", exception.getMessage());
    }

    /**
     * Test invalid character with number - should throw IllegalArgumentException
     */
    @Test
    public void testInvalidCharacterNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            EncryptionMachine.encryptLetter('5');
        });
        assertEquals("Only standard English alphabet letters a-z are allowed", exception.getMessage());
    }

    /**
     * Test invalid character with special character - should throw
     * IllegalArgumentException
     */
    @Test
    public void testInvalidCharacterSpecial() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            EncryptionMachine.encryptLetter('!');
        });
        assertEquals("Only standard English alphabet letters a-z are allowed", exception.getMessage());
    }

    /**
     * Test invalid word with uppercase letters - should throw
     * IllegalArgumentException
     */
    @Test
    public void testInvalidWordUppercase() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            EncryptionMachine.encryptWord("Hello");
        });
        assertEquals("Only standard English alphabet letters a-z are allowed", exception.getMessage());
    }

    /**
     * Test invalid word with numbers - should throw IllegalArgumentException
     */
    @Test
    public void testInvalidWordWithNumbers() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            EncryptionMachine.encryptWord("test123");
        });
        assertEquals("Only standard English alphabet letters a-z are allowed", exception.getMessage());
    }

    /**
     * Test invalid word with spaces - should throw IllegalArgumentException
     */
    @Test
    public void testInvalidWordWithSpaces() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            EncryptionMachine.encryptWord("hello world");
        });
        assertEquals("Only standard English alphabet letters a-z are allowed", exception.getMessage());
    }

    /**
     * Test empty string - should return empty string (loop never executes)
     */
    @Test
    public void testEmptyString() throws Exception {
        assertEquals("", EncryptionMachine.encryptWord(""));
    }

    // ============================================
    // Test Group 4: Console I/O Method Coverage
    // ============================================
    // Note: These tests require changing private methods to package-private
    // (removing 'private' keyword) to allow testing

    /**
     * Test outputStartMessage - verifies the method executes without error
     */
    @Test
    public void testOutputStartMessage() {
        // Redirect System.out to capture output
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        java.io.PrintStream originalOut = System.out;
        System.setOut(new java.io.PrintStream(outContent));

        try {
            EncryptionMachine.outputStartMessage();

            String output = outContent.toString();
            assertTrue(output.contains("Welcome to the CSCI717 Encryption Machine Construction"));
            assertTrue(output.contains("The program lets you encrypt a message"));
        } finally {
            System.setOut(originalOut);
        }
    }

    /**
     * Test outputEndMessage - verifies the method executes without error
     */
    @Test
    public void testOutputEndMessage() {
        // Redirect System.out to capture output
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        java.io.PrintStream originalOut = System.out;
        System.setOut(new java.io.PrintStream(outContent));

        try {
            EncryptionMachine.outputEndMessage();

            String output = outContent.toString();
            assertTrue(output.contains("Message fully encrypted"));
        } finally {
            System.setOut(originalOut);
        }
    }

    /**
     * Test outputEncryptedWord - verifies the method outputs correctly
     */
    @Test
    public void testOutputEncryptedWord() {
        // Redirect System.out to capture output
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        java.io.PrintStream originalOut = System.out;
        System.setOut(new java.io.PrintStream(outContent));

        try {
            EncryptionMachine.outputEncryptedWord("hello", "khoor");

            String output = outContent.toString();
            assertTrue(output.contains("\"hello\""));
            assertTrue(output.contains("has been encrypted to:"));
            assertTrue(output.contains("\"khoor\""));
        } finally {
            System.setOut(originalOut);
        }
    }

    /**
     * Test encryptKey - verifies the method reads input and encrypts correctly
     */
    @Test
    public void testEncryptKey() throws Exception {
        // Setup input and output streams
        String input = "test\n";
        java.io.ByteArrayInputStream inContent = new java.io.ByteArrayInputStream(input.getBytes());
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();

        java.io.InputStream originalIn = System.in;
        java.io.PrintStream originalOut = System.out;

        System.setIn(inContent);
        System.setOut(new java.io.PrintStream(outContent));

        try {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            EncryptionMachine.encryptKey(scanner);

            String output = outContent.toString();
            assertTrue(output.contains("Enter a key:"));
            assertTrue(output.contains("\"test\""));
            assertTrue(output.contains("\"whvw\""));
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

    // ============================================
    // Test Group 5: wordEncryptionLoop Coverage
    // ============================================

    /**
     * Test wordEncryptionLoop with valid input - positive word count
     */
    @Test
    public void testWordEncryptionLoopValidInput() throws Exception {
        // Setup: 2 words
        String input = "2\nhello\nworld\n";
        java.io.ByteArrayInputStream inContent = new java.io.ByteArrayInputStream(input.getBytes());
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();

        java.io.InputStream originalIn = System.in;
        java.io.PrintStream originalOut = System.out;

        System.setIn(inContent);
        System.setOut(new java.io.PrintStream(outContent));

        try {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            EncryptionMachine.wordEncryptionLoop(scanner);

            String output = outContent.toString();
            assertTrue(output.contains("How many words in your message?:"));
            assertTrue(output.contains("Next word:"));
            assertTrue(output.contains("\"hello\""));
            assertTrue(output.contains("\"khoor\""));
            assertTrue(output.contains("\"world\""));
            assertTrue(output.contains("\"zruog\""));
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

    /**
     * Test wordEncryptionLoop with zero words
     */
    @Test
    public void testWordEncryptionLoopZeroWords() throws Exception {
        String input = "0\n";
        java.io.ByteArrayInputStream inContent = new java.io.ByteArrayInputStream(input.getBytes());
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();

        java.io.InputStream originalIn = System.in;
        java.io.PrintStream originalOut = System.out;

        System.setIn(inContent);
        System.setOut(new java.io.PrintStream(outContent));

        try {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            EncryptionMachine.wordEncryptionLoop(scanner);

            String output = outContent.toString();
            assertTrue(output.contains("How many words in your message?:"));
            assertTrue(output.contains("There were no words in the message."));
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

    /**
     * Test wordEncryptionLoop with negative word count
     */
    @Test
    public void testWordEncryptionLoopNegativeWords() throws Exception {
        String input = "-5\n";
        java.io.ByteArrayInputStream inContent = new java.io.ByteArrayInputStream(input.getBytes());
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();

        java.io.InputStream originalIn = System.in;
        java.io.PrintStream originalOut = System.out;

        System.setIn(inContent);
        System.setOut(new java.io.PrintStream(outContent));

        try {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            EncryptionMachine.wordEncryptionLoop(scanner);

            String output = outContent.toString();
            assertTrue(output.contains("How many words in your message?:"));
            assertTrue(output.contains("There were no words in the message."));
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

    /**
     * Test wordEncryptionLoop with invalid input (non-numeric)
     */
    @Test
    public void testWordEncryptionLoopInvalidInput() throws Exception {
        String input = "abc\n";
        java.io.ByteArrayInputStream inContent = new java.io.ByteArrayInputStream(input.getBytes());
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();

        java.io.InputStream originalIn = System.in;
        java.io.PrintStream originalOut = System.out;

        System.setIn(inContent);
        System.setOut(new java.io.PrintStream(outContent));

        try {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            EncryptionMachine.wordEncryptionLoop(scanner);

            String output = outContent.toString();
            assertTrue(output.contains("How many words in your message?:"));
            assertTrue(output.contains("A number was expected to be entered."));
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

    /**
     * Test wordEncryptionLoop with exception thrown during word encryption
     */
    @Test
    public void testWordEncryptionLoopWithInvalidWord() {
        // Setup: 1 word, but the word contains invalid characters
        String input = "1\nHELLO\n";
        java.io.ByteArrayInputStream inContent = new java.io.ByteArrayInputStream(input.getBytes());
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();

        java.io.InputStream originalIn = System.in;
        java.io.PrintStream originalOut = System.out;

        System.setIn(inContent);
        System.setOut(new java.io.PrintStream(outContent));

        try {
            java.util.Scanner scanner = new java.util.Scanner(System.in);

            // This should throw an exception when trying to encrypt "HELLO"
            assertThrows(IllegalArgumentException.class, () -> {
                EncryptionMachine.wordEncryptionLoop(scanner);
            });
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

    /**
     * Test wordEncryptionLoop with single word - ensures all paths in loop are
     * covered
     */
    @Test
    public void testWordEncryptionLoopSingleWord() throws Exception {
        // Setup: exactly 1 word
        String input = "1\ntest\n";
        java.io.ByteArrayInputStream inContent = new java.io.ByteArrayInputStream(input.getBytes());
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();

        java.io.InputStream originalIn = System.in;
        java.io.PrintStream originalOut = System.out;

        System.setIn(inContent);
        System.setOut(new java.io.PrintStream(outContent));

        try {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            EncryptionMachine.wordEncryptionLoop(scanner);

            String output = outContent.toString();
            assertTrue(output.contains("How many words in your message?:"));
            assertTrue(output.contains("Next word:"));
            assertTrue(output.contains("\"test\""));
            assertTrue(output.contains("\"whvw\""));
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

    // ============================================
    // Test Group 6: Main Method Coverage
    // ============================================

    /**
     * Test main method - full integration test
     */
    @Test
    public void testMainMethod() {
        // Simulate complete user interaction: key + 1 word
        String input = "test\n1\nhello\n";
        java.io.ByteArrayInputStream inContent = new java.io.ByteArrayInputStream(input.getBytes());
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();

        java.io.InputStream originalIn = System.in;
        java.io.PrintStream originalOut = System.out;

        System.setIn(inContent);
        System.setOut(new java.io.PrintStream(outContent));

        try {
            EncryptionMachine.main(new String[] {});

            String output = outContent.toString();
            // Verify start message
            assertTrue(output.contains("Welcome to the CSCI717 Encryption Machine Construction"));
            // Verify key encryption
            assertTrue(output.contains("Enter a key:"));
            assertTrue(output.contains("\"test\""));
            assertTrue(output.contains("\"whvw\""));
            // Verify word loop
            assertTrue(output.contains("How many words in your message?:"));
            assertTrue(output.contains("Next word:"));
            assertTrue(output.contains("\"hello\""));
            assertTrue(output.contains("\"khoor\""));
            // Verify end message
            assertTrue(output.contains("Message fully encrypted"));
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

    /**
     * Test main method with exception handling - invalid character in key
     */
    @Test
    public void testMainMethodWithException() {
        // Simulate user entering invalid key
        String input = "TEST\n";
        java.io.ByteArrayInputStream inContent = new java.io.ByteArrayInputStream(input.getBytes());
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();

        java.io.InputStream originalIn = System.in;
        java.io.PrintStream originalOut = System.out;

        System.setIn(inContent);
        System.setOut(new java.io.PrintStream(outContent));

        try {
            Exception exception = assertThrows(RuntimeException.class, () -> {
                EncryptionMachine.main(new String[] {});
            });

            // Verify it's wrapping an IllegalArgumentException
            assertTrue(exception.getCause() instanceof IllegalArgumentException);
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

    // ============================================
    // Test Group 3: Constant Modification (Hypothetical)
    // ============================================
    // Note: These tests cannot be directly implemented without modifying
    // the SHIFT constant or refactoring the code to accept shift as a parameter.
    // They are documented here for completeness but would require code changes
    // to make SHIFT configurable for testing purposes.

    /*
     * BBT-3.1: Shift = 1 (Standard, no wrap)
     * With SHIFT = 1: a -> b, play -> qmbz
     * 
     * BBT-3.2: Shift = 1 (Wrap-around)
     * With SHIFT = 1: z -> a, zebra -> afcsb
     * 
     * BBT-3.3: Shift = 25 (Reverse Shift)
     * With SHIFT = 25: a -> z, zulu -> yult
     * 
     * To implement these tests, consider refactoring encryptLetter and encryptWord
     * to accept a shift parameter, or create separate methods for testing purposes.
     */

}
