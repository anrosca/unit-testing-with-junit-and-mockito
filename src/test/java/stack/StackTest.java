package stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {

    private Stack stack;

    @BeforeEach
    void setUp() {
        stack = BoundedStack.make(2);
    }

    @Test
    public void newlyCreatedStack_ShouldBeEmpty() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    public void afterOnePush_StackSizeShouldBeOne() {
        //Arrange
        stack = BoundedStack.make(2);

        //Act
        stack.push(10);

        //Assert
        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());

        //Annihilate
    }

    @Test
    public void afterOnePushAndOnePop_StackShouldBeEmpty() {
        stack.push(10);
        stack.pop();

        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void whenOneIsPushed_OneIsPopped() {
        stack.push(1);

        assertEquals(1, stack.pop());
    }

    @Test
    public void whenOneAndTwoArePushed_TwoAndOneArePopped() {
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    public void whenPushedPastCapacity_ItOverflows() {
        assertThrows(BoundedStack.OverflowException.class, () -> {
            stack.push(1);
            stack.push(2);
            stack.push(3);
        });
    }

    @Test
    public void whenPoppingAndEmptyStack_ItUnderflows() {
        assertThrows(BoundedStack.UnderflowException.class, () -> stack.pop());
    }

    @Test
    public void whenCreatingStackWithIllegalCapacity_ItThrowsIllegalCapacity() {
        assertThrows(BoundedStack.IllegalCapacity.class, () -> BoundedStack.make(-1));
    }

    @Test
    public void whenCreatingZeroCapacityStack_AnyPushOverflows() {
        stack = BoundedStack.make(0);
        stack.size();
        stack.isEmpty();
        assertThrows(BoundedStack.OverflowException.class, () -> stack.push(1));
    }
}
