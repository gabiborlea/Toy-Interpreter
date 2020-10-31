package model.adt;

import java.util.ArrayList;

public class List<TElement> implements ListInterface<TElement> {
    ArrayList<TElement> list;

    public List(){
        list = new ArrayList<>();
    }

    @Override
    public void add(TElement element) {
        list.add(element);
    }

    @Override
    public TElement get(int index) {
        return list.get(index);
    }

    @Override
    public void set(int index, TElement element) {
        list.set(index, element);
    }

    @Override
    public void remove(int index) {
        list.remove(index);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
