/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    //private int length = storage.length;
    private int size = 0;
    //private Resume uuid;

    void clear() {                                                             // ++
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        System.out.println("Cleared!");
        size = 0;
    }

    void save(Resume r) {                                                     // ++
        // for (int i = 0; i < length; i++) {
        //    if (storage[i] == null) {
        storage[size] = r;
        // System.out.println("saved ");
        size++;
        //return;
        //}
        //}
    }

    Resume get(String uuid) {                                       // ++
        int a = 0;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                //System.out.println(storage[i]);
                // System.out.println("getting! ");
                return storage[i];
            }
            a = i;
        }
        if (a > size) {
            return null;
        }
        return storage[a];
    }

    void delete(String uuid) {                                                  // ++
        for (int i = 0; i < size; i++) {
            if (String.valueOf(storage[i]).equals(uuid)) {
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
