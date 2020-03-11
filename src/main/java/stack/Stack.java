package stack;

public interface Stack {
    boolean isEmpty();

    int size();

    void push(int element);

    int pop();

    class OverflowException extends RuntimeException {
    }

    class UnderflowException extends RuntimeException {
    }

    class IllegalCapacity extends RuntimeException {
    }
}
