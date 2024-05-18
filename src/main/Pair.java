package main;

import java.util.ArrayList;

public class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void setPair(K key, V value)
    {
        this.key = key;
        this.value = value;
    }

    public void setPair(Pair<K, V> pair) {
        this.key = pair.getKey();
        this.value = pair.getValue();
    }

    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }

    public static <K, V> Pair<K, V> getPair(ArrayList<Pair<K, V>> list, int index) {
        if (index >= 0 && index < list.size()) {
            return list.get(index);
        } else {
            throw new IndexOutOfBoundsException("Indeks di luar batas: " + index);
        }
    }
}
