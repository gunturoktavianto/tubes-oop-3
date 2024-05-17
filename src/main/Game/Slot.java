package main.Game;

public class Slot<T> {
    private T item;

    public Slot() {
        this.item = null;
    }

    public Slot(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public boolean isEmpty() {
        return item == null;
    }
}
