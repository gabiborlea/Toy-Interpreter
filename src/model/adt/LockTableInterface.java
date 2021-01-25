package model.adt;

public interface LockTableInterface extends DictionaryInterface<Integer, Integer>{
    int add(Integer value);
    void updateLock(Integer key, Integer value);
    int lookUpLock(Integer key);
}
