package math;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasicMathTest {

    static Stream<Arguments> provideMultiplicationArgument() {
        return Stream.of(
                Arguments.of(0, 0, 0),
                Arguments.of(4, 2, 2),
                Arguments.of(-6, 2, -3),
                Arguments.of(8, -2, -4)
        );
    }

    @ParameterizedTest(name = "{0} = {1} * {2}")
    @MethodSource("provideMultiplicationArgument")
    public void multiplicationTest(int product, int multiplier, int multiplicand) {
        BasicMath math = new BasicMath();

        assertEquals(product, math.multiply(multiplier, multiplicand));
    }

    @Test
    public void multiplicationSpyingTest() {
        BasicMath math = new BasicMath();
        AdderSpy spy = new AdderSpy();
        math.setAdder(spy);

        int result = math.multiply(6, 2);

        assertEquals("0+2,2+2,4+2,6+2,8+2,10+2", spy.getInvocations());
        assertEquals(12, result);
    }

    private class AdderSpy extends Adder {
        private List<String> invocations = new ArrayList<>();

        @Override
        public int add(int a, int b) {
            invocations.add("" + a + "+" + b);
            return super.add(a, b);
        }

        public String getInvocations() {
            return String.join(",", invocations);
        }
    }
}
