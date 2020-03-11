package math;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasicMathTest {

    public static Stream<Arguments> provideMultiplicationArguments() {
        return Stream.of(
                Arguments.of(0, 0, 0),
                Arguments.of(4, 2, 2),
                Arguments.of(-6, 2, -3),
                Arguments.of(8, -2, -4),
                Arguments.of(0, -2, 0)
        );
    }

    @ParameterizedTest(name = "{0} = {1} * {2}")
    @MethodSource("provideMultiplicationArguments")
    public void spotTest(int expectedProduct, int multiplier, int multiplicand) {
        BasicMath math = new BasicMath();

        assertEquals(expectedProduct, math.multiply(multiplier, multiplicand));
    }
}
