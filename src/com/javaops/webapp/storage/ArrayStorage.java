package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void clear() {
        Arrays.fill(storage, 0, size,null);// ++
        size = 0;
    }

    public void update(Resume resume) {
        int getIndex = getIndex(resume.getUuid());

        if (getIndex != -1) {
            storage[getIndex] = resume;
        } else System.out.println("Resume " + resume.getUuid() + "Not found in resume");
    }

    public void save(Resume resume) {
        if (getIndex(resume.getUuid()) != -1) {
            System.out.println(resume.getUuid() + " Already in resume");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            storage[size] = resume;
            size++;
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
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals((storage[i].getUuid()))) {
                return i;
            }
        }
        return -1;
    }
}
