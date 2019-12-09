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

    public void update(Resume resume) {
        int getIndex = getIndex(resume.getUuid());

        if (getIndex != -1) {
            storage[getIndex] = resume;
        } else System.out.println("Not found in resume");
    }

    public void save(Resume resume) {
        int length = storage.length;

        if (getIndex(resume.getUuid()) != -1) {
            System.out.println(resume.getUuid() + " Already in resume");
        } else if (size >= length) {
            System.out.println("Storage full");
        } else {
            storage[size] = resume;
            size++;
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

    public void delete(String uuid) {
        int getIndex = getIndex(uuid);

        if (getIndex != -1) {
            storage[getIndex] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume " + uuid + "not exist");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {                                                          // ++
        Resume[] getAll = new Resume[size];
        System.arraycopy(storage, 0, getAll, 0, size);
        return getAll;
    }

    public int size() {                                                                 // ++
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals((storage[i].getUuid()))) {
                return i;
            }
        }
        return -1;
    }
}
