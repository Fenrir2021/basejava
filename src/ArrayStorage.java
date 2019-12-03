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
            if (String.valueOf(storage[i]).equals(uuid)) {
                //System.out.println(storage[i]);
                // System.out.println("getting! ");
                a = i;
            } //else return storage[a];
        }
        return storage[a];
    }

    void delete(String uuid) {                                                  // ++
        for (int i = 0; i < size; i++) {
            if (String.valueOf(storage[i]).equals(uuid)) {
                storage[i] = null;
                System.arraycopy(storage, i + 1, storage, i, size - 1);
                System.out.println("item deleted");
                size--;
            }
        }
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {                                                          // ++
        Resume getAll[] = new Resume[size];
        //getAll = Arrays.toString(storage);
        System.arraycopy(storage, 0, getAll, 0, size);
        //System.out.println();
        return getAll;
    }

    int size() {                                                                 // ++
        // int lenArr = 0;
        // for (int i = 0; i < length; i++) {
        //     if (storage[i] != null) ++lenArr;
        // }
        System.out.println("size " + size);

        return size;
    }
}
