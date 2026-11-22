package filestorage;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws Exception {
        Path dir = Files.createTempDirectory("filestorage-demo");

        FileStorage[] storages = {
                new LocalDiskStorageAdapter(new LocalDiskDriver(), dir),
                new S3StorageAdapter(new S3Client(), "my-bucket"),
                new MemoryStorageAdapter(new MemoryStore())
        };

        for (FileStorage storage : storages) {
            storage.save("greeting", "hello from " + storage.getClass().getSimpleName());
            System.out.println(storage.load("greeting"));
        }

        System.out.println("Disk files written to: " + dir);
    }
}
