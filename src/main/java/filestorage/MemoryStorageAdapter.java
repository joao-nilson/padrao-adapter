package filestorage;

import java.util.NoSuchElementException;

public class MemoryStorageAdapter implements FileStorage {

    private final MemoryStore store;

    public MemoryStorageAdapter(MemoryStore store) {
        this.store = store;
    }

    @Override
    public void save(String key, String data) {
        store.set(key, data);
    }

    @Override
    public String load(String key) {
        String value = store.get(key);
        if (value == null) {
            throw new NoSuchElementException("Key not found: " + key);
        }
        return value;
    }
}
