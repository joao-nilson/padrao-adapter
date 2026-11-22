package filestorage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemoryStorageAdapterTest extends FileStorageContractTest {

    private MemoryStore store;

    @Override
    FileStorage createStorage() {
        store = new MemoryStore();
        return new MemoryStorageAdapter(store);
    }

    @Test
    void shouldDelegateToMemoryStore() {
        storage.save("note", "hello");

        assertEquals("hello", store.get("note"));
    }
}
