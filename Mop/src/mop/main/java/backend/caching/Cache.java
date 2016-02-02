package mop.main.java.backend.caching;

import java.util.HashMap;

public abstract class Cache<K, V> {

    private final HashMap<K, V> map = new HashMap<>();

    public void set(K key, V value) {

        map.put(key, value);
    }

    public V get(K key) {

        return map.get(key);
    }
}
