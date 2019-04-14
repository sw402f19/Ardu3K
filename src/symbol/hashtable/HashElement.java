package symbol.hashtable;

public class HashElement<K, V> {

    public K key;
    public V value;

    public HashElement<K, V> next;

    public HashElement(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
