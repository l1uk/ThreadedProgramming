package ES1;

public interface PriorityStorage {
    void insert(String s, Priority p) throws StorageFull;

    String get(Priority p);

    void remove(Priority p) throws StorageEmpty;

    int num(Priority p);

    class StorageFull extends Exception {
    }

    class StorageEmpty extends Exception {
        public StorageEmpty(String message) {
            super(message);
        }
    }
}