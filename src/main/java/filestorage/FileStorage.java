package filestorage;

public interface FileStorage {
    void save(String key, String data);
    String load(String key);
}
