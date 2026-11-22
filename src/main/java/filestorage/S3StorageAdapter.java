package filestorage;

import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;

public class S3StorageAdapter implements FileStorage {

    private final S3Client client;
    private final String bucket;

    public S3StorageAdapter(S3Client client, String bucket) {
        this.client = client;
        this.bucket = bucket;
    }

    @Override
    public void save(String key, String data) {
        client.putObject(bucket, key, data.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String load(String key) {
        byte[] body = client.getObject(bucket, key);
        if (body == null) {
            throw new NoSuchElementException("Key not found: " + key);
        }
        return new String(body, StandardCharsets.UTF_8);
    }
}
