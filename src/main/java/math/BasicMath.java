package math;

public class BasicMath {

    public int multiply(int a, int b) {
        boolean posA = a >= 0;
        boolean posB = b >= 0;
        boolean negativeProduct = posA != posB;

        a = posA ? a : -a;
        b = posB ? b : -b;

        int product = 0;
        while (a-- > 0)
            product = product + b;

        return negativeProduct ? -product : product;
    }
}
