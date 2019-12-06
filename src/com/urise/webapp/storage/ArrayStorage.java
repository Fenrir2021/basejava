package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;
    private int length = storage.length;

    public void clear() {                                                             // ++
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        //System.out.println("Cleared!");
        size = 0;
    }

    public void update(Resume r) { //TODO check if resume present           //++
        int check = check(r.getUuid());
        if (check == 0) {
            save(storage[check]);
        } else System.out.println("Not found in resume");

    }

    public void save(Resume r) {     //TODO check if resume not present     //++
        int check = check(r.getUuid());
        if (check != 0) {
            System.out.println(r.getUuid() + "Already in resume");
        } else if (size == length) {
            System.out.println("Storage full");
        } else {
            storage[size] = r;
            size++;
        }

    }

    public Resume get(String uuid) {                                               // ++
        /*for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;*/
        int check = check(uuid);
        if (check != 1) {
            return storage[check];
        } else {
            System.out.println("Not found");
            return null;
        }
    }

    public void delete(String uuid) {     //TODO check if resume present           //
        int check = check(uuid);
        if (check != 1) {
            System.arraycopy(storage, check + 1, storage, check, size - 1 - check);
            size--;

        } else {
            System.out.println("ERROR");
        }

    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {                                                          // ++
        Resume getAll[] = new Resume[size];
        System.arraycopy(storage, 0, getAll, 0, size);
        return getAll;
    }

    public int size() {                                                                 // ++
        System.out.println("size " + size);
        return size;
    }

    private int check(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return 1;
    }
}
