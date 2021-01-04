package model.adt;

import java.util.ArrayList;
import java.util.Map;

public interface DictionaryInterface<TKey, TElement> {
    void add(TKey key, TElement element);
    void remove(TKey key);
    void update(TKey key, TElement new_element);
    TElement get(TKey key);
    boolean isDefined(TKey key);
    void clear();
    void setContent(Map<TKey, TElement> newContent);
    Map<TKey, TElement> getContent();
    ArrayList<ArrayList<String>> getElementsStrings();
    DictionaryInterface<TKey, TElement> copy();
}
