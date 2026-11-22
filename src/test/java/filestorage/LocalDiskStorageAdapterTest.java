package filestorage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class LocalDiskStorageAdapterTest extends FileStorageContractTest {

    @TempDir
    Path tempDir;

    @Override
    FileStorage createStorage() {
        return new LocalDiskStorageAdapter(new LocalDiskDriver(), tempDir);
    }

    @Test
    void shouldWriteFileInsideBaseDir() throws Exception {
        storage.save("note", "hello");

        assertEquals("hello", new String(Files.readAllBytes(tempDir.resolve("note"))));
    }

    @Test
    void shouldRejectKeyOutsideBaseDir() {
        assertThrows(IllegalArgumentException.class, () -> storage.save("../evil", "x"));
        assertThrows(IllegalArgumentException.class, () -> storage.load("../evil"));
    }
}
