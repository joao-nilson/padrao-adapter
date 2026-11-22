package filestorage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

abstract class FileStorageContractTest {

    protected FileStorage storage;

    abstract FileStorage createStorage();

    @BeforeEach
    void setUp() {
        storage = createStorage();
    }

    @Test
    void shouldLoadWhatWasSaved() {
        storage.save("note", "hello");

        assertEquals("hello", storage.load("note"));
    }

    @Test
    void shouldOverwriteExistingKey() {
        storage.save("note", "old");
        storage.save("note", "new");

        assertEquals("new", storage.load("note"));
    }

    @Test
    void shouldKeepKeysIndependent() {
        storage.save("a", "1");
        storage.save("b", "2");

        assertEquals("1", storage.load("a"));
        assertEquals("2", storage.load("b"));
    }

    @Test
    void shouldPreserveSpecialCharacters() {
        storage.save("note", "olá, mundo ✓");

        assertEquals("olá, mundo ✓", storage.load("note"));
    }

    @Test
    void shouldFailWhenKeyDoesNotExist() {
        assertThrows(NoSuchElementException.class, () -> storage.load("missing"));
    }
}
