package filestorage;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.NoSuchElementException;

public class LocalDiskStorageAdapter implements FileStorage {

    private final LocalDiskDriver driver;
    private final Path baseDir;

    public LocalDiskStorageAdapter(LocalDiskDriver driver, Path baseDir) {
        this.driver = driver;
        this.baseDir = baseDir.toAbsolutePath().normalize();
    }

    @Override
    public void save(String key, String data) {
        try {
            driver.writeFile(resolve(key), data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public String load(String key) {
        try {
            return new String(driver.readFile(resolve(key)), StandardCharsets.UTF_8);
        } catch (NoSuchFileException e) {
            throw new NoSuchElementException("Key not found: " + key);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private String resolve(String key) {
        Path file = baseDir.resolve(key).normalize();
        if (!file.startsWith(baseDir) || file.equals(baseDir)) {
            throw new IllegalArgumentException("Invalid key: " + key);
        }
        return file.toString();
    }
}
