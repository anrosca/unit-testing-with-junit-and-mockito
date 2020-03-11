package primegenerator;

public class PrimePrinter {

    private final int[] primes;
    private final int numberOfPrimes;

    public PrimePrinter(int[] primes, int numberOfPrimes) {
        this.primes = primes;
        this.numberOfPrimes = numberOfPrimes;
    }

    public void printPrimes(int rowsPerPage, int columnsPerPage) {
        int pageNumber = 1;
        int pageOffset = 1;
        while (pageOffset <= numberOfPrimes) {
            System.out.print("The First ");
            System.out.print(numberOfPrimes);
            System.out.print(" Prime Numbers --- Page ");
            System.out.println(pageNumber);
            System.out.println();
            for (int rowOffset = pageOffset; rowOffset <= pageOffset + rowsPerPage - 1; ++rowOffset) {
                for (int columnIndex = 0; columnIndex <= columnsPerPage - 1; ++columnIndex) {
                    if (rowOffset + columnIndex * rowsPerPage <= numberOfPrimes) {
                        System.out.printf("%10d", primes[rowOffset + columnIndex * rowsPerPage]);
                    }
                }
                System.out.println();
            }
            System.out.println();
            pageNumber++;
            pageOffset += rowsPerPage * columnsPerPage;
        }
    }
}
