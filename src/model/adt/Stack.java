package model.adt;

import java.util.ArrayList;
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
    public void clear() {
        stack.clear();
    }

    @Override
    public LinkedList<TElement> getContent() {
        return this.stack;
    }

    @Override
    public ArrayList<String> getElementsStrings() {
        ArrayList<String> elements = new ArrayList<>();
        for (var element: this.stack) {
            elements.add(element.toString());
        }
        return elements;
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
