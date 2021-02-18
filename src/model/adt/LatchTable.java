package model.adt;

public class LatchTable extends Dictionary<Integer, Integer> implements LatchTableInterface{
    int freeAddresses = 0;
    @Override
    public int add(int value) {
        add(freeAddresses, value);
        return freeAddresses;
    }
}
