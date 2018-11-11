import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    public int length = storage.length;

    void clear() {
        for(int i=0; i < length; i++) {
           storage[i]= null;
        }
        System.out.println("Cleared!");
    }

    void save(Resume r) {
        for (int i=0;i<length;i++) {
            if (storage[i] == null){
                storage[i] = r;
                System.out.println("saved ");
                return;
            }
        }


    }

    String get(String uuid) {
        return uuid;
    }

    void delete(String uuid) {
       // for (int i=0; i< length; i++) {
                //   if (storage[i] = uuid){
        //       storage[i] = null;
        //   }
        //storage[uuid] = null;
            System.out.println("item deleted");
       // }

    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        String getAll;
        getAll = Arrays.toString(storage);
        System.out.println(getAll);
        return new Resume[0];
    }

    int size() {
        int lenArr = 0;
        for(int i=0; i < length; i++) {
            if (storage[i] != null)++lenArr;
        }
        System.out.println("size " + lenArr);
        return lenArr;
    }
}
