package filestorage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class S3StorageAdapterTest extends FileStorageContractTest {

    private S3Client client;

    @Override
    FileStorage createStorage() {
        client = new S3Client();
        return new S3StorageAdapter(client, "my-bucket");
    }

    @Test
    void shouldPutObjectInConfiguredBucket() {
        storage.save("note", "hello");

        assertEquals("hello", new String(client.getObject("my-bucket", "note")));
        assertNull(client.getObject("other-bucket", "note"));
    }
}
