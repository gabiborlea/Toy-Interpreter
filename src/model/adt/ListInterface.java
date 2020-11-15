package model.adt;

import java.util.ArrayList;

public interface ListInterface<TElement> {
    void add(TElement element);
    TElement get(int index);
    void set(int index, TElement element);
    void remove(int index);
    void clear();
    boolean isEmpty();
    ArrayList<String> getElementsStrings();
}
