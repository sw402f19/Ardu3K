package symbol.hashtable;

import java.util.ArrayList;

public class HashTable<K, V> {

    private int capacity;
    private ArrayList<HashElement<K, V>> table;
    private int size;

    public HashTable() {
        capacity = 10;
        table = new ArrayList<>();
        size = 0;
        populateNulls(table);
    }

    private int getIndex(K key) {
        return Math.floorMod(key.hashCode(), capacity);
    }

    public void add(K key, V value) {
        int index = getIndex(key);
        HashElement<K, V> element = table.get(index);

        while (element != null) {
            if(element.key.equals(key)) {
                element.value = value;
            }
            element = element.next;
        }
        size++;
        element = table.get(index);
        HashElement<K,V> newElement = new HashElement<>(key, value);
        newElement.next = element;
        table.set(index, newElement);

        updateCapacity();
    }
    public V remove(K key) {
        int index = getIndex(key);
        HashElement<K, V> headElement = table.get(index);
        HashElement<K, V> prevElement = null;

        while (headElement != null) {
            if(headElement.key.equals(key))
                break;

            prevElement = headElement;
            headElement = headElement.next;
        }
        if(headElement == null)
            return null;

        size--;

        if(prevElement != null)
            prevElement.next = headElement.next;
        else
            table.set(index, headElement.next);

        return headElement.value;
    }

    private void updateCapacity() {
        if((double)size / capacity >= 0.7) {
            ArrayList<HashElement<K, V>> temp = table;
            table = new ArrayList<>();
            capacity *= 2;
            size = 0;
            populateNulls(table);

            for(HashElement<K, V> element : temp) {
                while (element != null) {
                    add(element.key, element.value);
                    element = element.next;
                    size++;
                }
            }
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        System.out.println("index: "+index);
        HashElement<K, V> headElement = table.get(index);


        while (headElement != null) {
            if(headElement.key.equals(key))
                return headElement.value;
            headElement = headElement.next;
        }
        return null;
    }
    public V getNext(K key) {
        int index = getIndex(key);
        HashElement<K, V> headElement = table.get(index);
        return headElement.next != null ? headElement.next.value : null;
    }

    private void populateNulls(ArrayList<HashElement<K, V>> table) {
        for(int i = 0; i < capacity; i++)
            table.add(null);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size () {
        return size;
    }
}
