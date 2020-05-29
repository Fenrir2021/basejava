package com.javaops.webapp.storage;

import com.javaops.webapp.exception.ExistStorageException;
import com.javaops.webapp.exception.NotExistStorageException;
import com.javaops.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        String[] strArray = new String[10000];
        for (int i = 0; i < 10000; i++) {
            int j = i + 1;
            String id = "uuid" + j;
            strArray[i] = id;
        }
        storage.clear();
        for (String str : strArray) {
            storage.save(new Resume(str));
        }
    }

    @Test
    public void size() {
        Assert.assertEquals(10000, storage.size());
    }

    @Test
    public void clear() {
        Assert.assertEquals(10000, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void update() {
        storage.update(new Resume("uuid_4"));
    }

    @Test
    public void getAll() {
        System.out.println("\nGet All");
        for (Resume r : storage.getAll()) {
            System.out.println(r);
        }
    }

    @Test(expected = ExistStorageException.class)
    public void save() {
        storage.save(new Resume(UUID_4));
    }

    @Test
    public void delete() {
        storage.delete(UUID_2);
    }

    @Test
    public void get() {
        System.out.println(storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }
}