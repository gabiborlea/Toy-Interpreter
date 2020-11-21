package model.adt;

public interface HeapInterface<TValue> extends DictionaryInterface<Integer, TValue> {
    int add(TValue value);
}
