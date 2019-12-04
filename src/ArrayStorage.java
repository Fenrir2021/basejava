/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {                                                             // ++
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        System.out.println("Cleared!");
        size = 0;
    }

    void save(Resume r) {                                                     // ++
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {                                       // ++
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                //System.out.println(storage[i]);
                // System.out.println("getting! ");
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {                                                  // ++
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                System.arraycopy(storage, i + 1, storage, i, size - 1 - i);
                System.out.println("item deleted");
                size--;
                break;
            }
        }
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {                                                          // ++
        Resume getAll[] = new Resume[size];
        System.arraycopy(storage, 0, getAll, 0, size);
        return getAll;
    }

    int size() {                                                                 // ++
        System.out.println("size " + size);
        return size;
    }
}
