package primegenerator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class PrimesTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private PrintStream oldOut;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(output));
        oldOut = System.out;
    }

    @AfterEach
    void tearDown() {
        System.setOut(oldOut);
    }

    @Test
    public void outputShouldMatchGold() throws IOException {
        Primes.main(new String[]{});

        ByteArrayInputStream in = new ByteArrayInputStream(output.toByteArray());
        try (BufferedReader gold = new BufferedReader(new FileReader("gold.txt"));
             BufferedReader lead = new BufferedReader(new InputStreamReader(in))) {
            String expectedLine;
            while ((expectedLine = gold.readLine()) != null)
                assertEquals(expectedLine, lead.readLine());
            assertNull(lead.readLine());
        }
    }
}