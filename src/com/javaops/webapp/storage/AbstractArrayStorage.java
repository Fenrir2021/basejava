package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.Arrays;

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

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index > -1) {
            storage[index] = resume;
        } else System.out.println("Resume " + resume.getUuid() + " Not found in resume");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (size == STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else if (index > -1) {
            System.out.println(resume.getUuid() + " Already in resume");
        } else {
            insertElement(resume, index);
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index > -1) {
            fillElement(index);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume " + uuid + " not exist");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index > -1) {
            return storage[index];
        } else {
            System.out.println("Resume " + uuid + " Not found ");
            return null;
        }
    }

    protected abstract void fillElement(int index);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract int getIndex(String uuid);
}
