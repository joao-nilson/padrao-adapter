package filestorage;

import java.util.HashMap;
import java.util.Map;

public class S3Client {

    private final Map<String, byte[]> objects = new HashMap<>();

    public void putObject(String bucket, String objectKey, byte[] body) {
        objects.put(bucket + "/" + objectKey, body);
    }

    public byte[] getObject(String bucket, String objectKey) {
        return objects.get(bucket + "/" + objectKey);
    }
}
