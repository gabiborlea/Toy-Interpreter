package model.adt;

import java.util.ArrayList;
import java.util.List;

public interface ListInterface<TElement> {
    void add(TElement element);
    TElement get(int index);
    void set(int index, TElement element);
    void remove(int index);
    void clear();
    boolean isEmpty();
    java.util.List<TElement> getContent();
    void setContent(java.util.List<TElement> newContent);
    ArrayList<String> getElementsStrings();
}
