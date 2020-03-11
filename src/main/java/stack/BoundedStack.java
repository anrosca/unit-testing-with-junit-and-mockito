package stack;

public class BoundedStack implements Stack {
    private int size;
    private int[] elements;

    private BoundedStack(int capacity) {
        elements = new int[capacity];
    }

    public static Stack make(int capacity) {
        if (capacity < 0)
            throw new IllegalCapacity();
        if (capacity == 0)
            return new ZeroCapacityStack();
        return new BoundedStack(capacity);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void push(int element) {
        if (size >= elements.length)
            throw new OverflowException();
        this.elements[size++] = element;
    }

    @Override
    public int pop() {
        if (isEmpty())
            throw new UnderflowException();
        return elements[--size];
    }

    public static class ZeroCapacityStack implements Stack {
        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public void push(int element) {
            throw new OverflowException();
        }

        @Override
        public int pop() {
            throw new UnderflowException();
        }
    }
}
