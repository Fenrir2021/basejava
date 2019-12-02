/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int length = storage.length;
    private int size = 0;
    //private Resume uuid;

    void clear() {                                                             // ++
        for (int i = 0; i < length; i++) {
            storage[i] = null;
        }
        System.out.println("Cleared!");
        size = 0;
    }

    void save(Resume r) {                                                     // ++
        for (int i = 0; i < length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                System.out.println("saved ");
                size++;
                return;
            }
        }


    }

    String get(String uuid) {                                                    // ++


        int a = 0;
        for (int i = 0; i < length; i++) {
            if (String.valueOf(storage[i]).equals(uuid)) {

                //System.out.println(storage[i]);
                // System.out.println("getting! ");
                a = i;
               // size++;
            }else return uuid;

        }
        return String.valueOf(storage[a]); //storage[uuid];
    }

    void delete(String uuid) {                                                  // ++
        for (int i = 0; i < length; i++) {
            if (String.valueOf(storage[i]).equals(uuid)) {
                storage[i] = null;
                System.out.println("item deleted");
                size--;
            }
        }

    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {                                                          // ++
        //String getAll;
        //getAll = Arrays.toString(storage);
        for (Resume s : storage
        ) {
            if (s != null) {
                System.out.print(s + " ");

            }


        }
        System.out.println();

        return new Resume[0];
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
