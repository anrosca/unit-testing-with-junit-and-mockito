package math;

public class BasicMath {

    private Adder adder = new Adder();

    public int multiply(int a, int b) {
        boolean posA = a >= 0;
        boolean posB = b >= 0;
        boolean negativeProduct = posA != posB;

        a = posA ? a : -a;
        b = posB ? b : -b;

        int product = 0;
        for (; b > 0; a <<= 1, b >>= 1) {
            if ((b & 1) == 1)
                product = adder.add(product, a);
        }
//        while (a-- > 0)
//            product = adder.add(product, b);

        return negativeProduct ? -product : product;
    }

    void setAdder(Adder adder) {
        this.adder = adder;
    }
}
