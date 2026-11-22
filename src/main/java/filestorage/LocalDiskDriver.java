package filestorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LocalDiskDriver {

    public void writeFile(String path, byte[] content) throws IOException {
        Path file = Paths.get(path);
        Files.createDirectories(file.getParent());
        Files.write(file, content);
    }

    public byte[] readFile(String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }
}
