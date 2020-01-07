package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int getIndex = getIndex(uuid);

        if (getIndex != -1) {
            return storage[getIndex];
        } else {
            System.out.println(" Not found ");
            return null;
        }
    }

    protected abstract int getIndex(String uuid);
}
