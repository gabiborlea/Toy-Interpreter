package model.adt;

import java.util.ArrayList;

public interface StackInterface<TElement> {
    TElement pop();
    void push(TElement element);
    boolean isEmpty();
    void clear();
    ArrayList<String> getElementsStrings();
}
