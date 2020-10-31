package model.adt;

import java.util.HashMap;

public class Dictionary<TKey, TElement> implements DictionaryInterface<TKey, TElement> {
    HashMap<TKey, TElement> dictionary;

    public Dictionary(){
        dictionary = new HashMap<>();
    }

    @Override
    public void add(TKey key, TElement element) {
        dictionary.put(key, element);
    }

    @Override
    public void remove(TKey key) {
        dictionary.remove(key);
    }

    @Override
    public void update(TKey key, TElement new_element) {
        dictionary.replace(key, new_element);
    }

    @Override
    public TElement get(TKey key) {
        return dictionary.get(key);
    }

    @Override
    public boolean isDefined(TKey key) {
        return dictionary.containsKey(key);
    }

    @Override
    public void clear() {
        dictionary.clear();
    }

    @Override
    public String toString() {
        return dictionary.toString();
    }
}