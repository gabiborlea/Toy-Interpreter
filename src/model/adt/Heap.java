package model.adt;

public class Heap<TValue> extends Dictionary<Integer, TValue> implements HeapInterface<TValue> {
    private int freeAddresses = 0;
    @Override
    public int add(TValue value) {
        add(++freeAddresses, value);
        return freeAddresses;
    }
}
