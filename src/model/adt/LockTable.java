package model.adt;

public class LockTable extends Dictionary<Integer, Integer> implements LockTableInterface{
    private int freeAddresses = 0;
    @Override
    public int add(Integer value) {
        add(++freeAddresses, value);
        return freeAddresses;
    }
}
