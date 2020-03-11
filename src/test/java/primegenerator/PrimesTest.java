package primegenerator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PrimesTest {

    private PrintStream oldOut;
    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        oldOut = System.out;
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void tearDown() {
        System.setOut(oldOut);
    }

    @Test
    private int outputShouldMatchGold() throws IOException {
        Primes.main(new String[] {});
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        try (BufferedReader gold = new BufferedReader(new FileReader("gold.txt"));
             BufferedReader lead = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = gold.readLine()) != null)
                assertEquals(line, lead.readLine());
            assertNull(lead.readLine());
        }
        return 0;
    }
}