package model.adt;

import java.util.ArrayList;
import java.util.LinkedList;

public interface StackInterface<TElement> {
    TElement pop();
    void push(TElement element);
    boolean isEmpty();
    void clear();
    LinkedList<TElement> getContent();
    ArrayList<String> getElementsStrings();
}
