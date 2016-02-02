package mop.main.java.backend.caching;

public final class DatabaseCache<K, V> extends Cache<K, V> {

    private static DatabaseCache cache;

    public static DatabaseCache getInstance() {

        synchronized (DatabaseCache.class) {

            if(cache == null) {

                cache = new DatabaseCache();
            }
        }
        return cache;
    }

    private DatabaseCache() {

    }
}
