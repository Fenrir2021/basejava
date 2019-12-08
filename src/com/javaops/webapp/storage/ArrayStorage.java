package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {                                                             // ++
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void update(Resume resume) { //TODO getIndex if resume present           //++
        int check = getIndex(resume.getUuid());
        if (check != -1) {
            save(storage[check]);
        } else System.out.println("Not found in resume");

    }

    public void save(Resume resume) {     //TODO getIndex if resume not present     //++
        int check = getIndex(resume.getUuid());
        int length = storage.length;

        if (check == -1) {
            storage[size] = resume;
            size++;
        } else if (size == length) {
            System.out.println("Storage full");
        } else {
            System.out.println(resume.getUuid() + " Already in resume");
        }

    }

    public Resume get(String uuid) {                                               // ++
        int getIndex = getIndex(uuid);
        if (getIndex != -1) {
            return storage[getIndex];
        } else {
            System.out.println(" Not found ");
            return null;
        }
    }

    public void delete(String uuid) {     //TODO getIndex if resume present           //
        int getIndex = getIndex(uuid);
        if (getIndex != -1) {
            System.arraycopy(storage, getIndex + 1, storage, getIndex, size - 1 - getIndex);
            size--;
        } else {
            System.out.println("ERROR");
        }

    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {                                                          // ++
        Resume [] getAll = new Resume[size];
        System.arraycopy(storage, 0, getAll, 0, size);
        return getAll;
    }

    public int size() {                                                                 // ++
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if ((storage[i].getUuid()).equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
