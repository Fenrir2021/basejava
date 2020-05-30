package com.javaops.webapp.storage;

import com.javaops.webapp.exception.ExistStorageException;
import com.javaops.webapp.exception.NotExistStorageException;
import com.javaops.webapp.exception.StorageException;
import com.javaops.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.javaops.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_5 = "uuid5";
    private static final String UUID_10001 = "uuid10001";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
        storage.save(new Resume(UUID_4));
    }

    @Test
    public void size() {
        Assert.assertEquals(4, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("uuid_4"));
    }

    @Test
    public void update() {
        storage.update(new Resume(UUID_1));
    }

    @Test
    public void getAll() {
        Resume[] array = new Resume[]{new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3),
                new Resume(UUID_4)};
        for (int i = 0; i < array.length; i++) {
            Assert.assertEquals(array[i], storage.get("uuid" + (i + 1)));
        }
    }

    @Test
    public void save() {
        int checkSize = storage.size();
        storage.save(new Resume(UUID_5));
        if (checkSize == storage.size()) {
            System.out.println("Wrong size after save: " + checkSize + " " + storage.size());
        }
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_4));
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        String[] strArray = new String[10000];
        for (int i = 0; i < STORAGE_LIMIT; i++) {
            int j = i + 1;
            String id = "uuid" + j;
            strArray[i] = id;
        }
        storage.clear();
        for (String str : strArray) {
            storage.save(new Resume(str));
        }
        storage.save(new Resume(UUID_10001));
    }

    @Test
    public void deleteExist() {
        storage.delete(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_5);
    }

    @Test
    public void get() {
        Assert.assertEquals(new Resume(UUID_1), storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }


}