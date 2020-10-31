package model.adt;

public interface DictionaryInterface<TKey, TElement> {
    void add(TKey key, TElement element);
    void remove(TKey key);
    void update(TKey key, TElement new_element);
    TElement get(TKey key);
    boolean isDefined(TKey key);
    void clear();
}