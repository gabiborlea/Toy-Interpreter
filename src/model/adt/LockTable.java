package model.adt;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTable extends Dictionary<Integer, Integer> implements LockTableInterface {
    private int freeAddresses = 0;

    @Override
    public int add(Integer value) {
        Lock lock = new ReentrantLock();
        lock.lock();
        add(++freeAddresses, value);
        lock.unlock();
        return freeAddresses;

    }

    @Override
    public void updateLock(Integer key, Integer value) {
        Lock lock = new ReentrantLock();
        lock.lock();
        update(key, value);
        lock.unlock();
    }

    @Override
    public int lookUpLock(Integer key) {
        Lock lock = new ReentrantLock();
        int value;
        lock.lock();
        if (!isDefined(key))
            value = -2;
        else value = get(key);
        lock.unlock();
        return value;
    }
}
