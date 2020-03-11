package primegenerator;

public class Primes {
    private static final int numberOfPrimes = 1000;
    private static final int rowsPerPage = 50;
    private static final int columnsPerPage = 4;

    public static void main(String[] args) {
        PrimeGenerator primeGenerator = new PrimeGenerator();
        int[] primes = primeGenerator.generatePrimes();
        PrimePrinter primePrinter = new PrimePrinter(primes, numberOfPrimes);
        primePrinter.printPrimes(rowsPerPage, columnsPerPage);
    }
}

class PrimeGenerator {
    private final int numberOfPrimes = 1000;
    private final int ordmax = 30;
    private final int[] primes = new int[numberOfPrimes + 1];
    private int primeCandidate = 1;
    private int primeIndex = 1;
    private int ord = 2;
    private int square = 9;
    private final int[] multiples = new int[ordmax + 1];

    public int[] generatePrimes() {
        primes[1] = 2;

        while (primeIndex < numberOfPrimes) {
            findNextPrime();
            primeIndex++;
            primes[primeIndex] = primeCandidate;
        }
        return primes;
    }

    private void findNextPrime() {
        boolean probablyPrime;
        do {
            primeCandidate += 2;
            if (primeCandidate == square) {
                ord++;
                square = primes[ord] * primes[ord];
                multiples[ord - 1] = primeCandidate;
            }
            int multipleIndex = 2;
            probablyPrime = true;
            while (multipleIndex < ord && probablyPrime) {
                while (multiples[multipleIndex] < primeCandidate) {
                    multiples[multipleIndex] += primes[multipleIndex] + primes[multipleIndex];
                }
                if (multiples[multipleIndex] == primeCandidate) {
                    probablyPrime = false;
                }
                multipleIndex++;
            }
        } while (!probablyPrime);
    }
}