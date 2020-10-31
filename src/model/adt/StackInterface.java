package model.adt;

public interface StackInterface<TElement> {
    TElement pop();
    void push(TElement element);
    boolean isEmpty();
}
