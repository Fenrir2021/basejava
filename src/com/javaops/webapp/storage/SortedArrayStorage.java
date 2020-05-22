package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void fillElement(int index) {
        int fillElem = size - index - 1;

        if (fillElem > 0) {
            System.arraycopy(storage, index + 1, storage, index, fillElem);
        }
    }

    @Override
    protected void insertElement(Resume resume, int index) {
        int insertElem = -index - 1;

        System.arraycopy(storage, insertElem, storage, insertElem + 1, size - insertElem);
        storage[insertElem] = resume;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
