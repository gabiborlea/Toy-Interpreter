package model.adt;

import java.util.LinkedList;

public class Stack<TElement> implements StackInterface<TElement> {
    LinkedList<TElement> stack;

    public Stack() {
        stack = new LinkedList<>();
    }

    @Override
    public TElement pop() {
        return stack.pop();
    }

    @Override
    public void push(TElement element) {
        stack.push(element);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
