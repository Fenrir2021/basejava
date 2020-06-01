package com.javaops.webapp.storage;

import com.javaops.webapp.exception.ExistStorageException;
import com.javaops.webapp.exception.NotExistStorageException;
import com.javaops.webapp.exception.StorageException;
import com.javaops.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.javaops.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_5 = "uuid5";
    private static final String UUID_10001 = "uuid10001";
    private static final Resume resume1 = new Resume(UUID_1);
    private static final Resume resume2 = new Resume(UUID_2);
    private static final Resume resume3 = new Resume(UUID_3);
    private static final Resume resume4 = new Resume(UUID_4);
    private static final Resume resume10001 = new Resume(UUID_10001);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
        storage.save(resume4);
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

    @Test(expected = AssertionError.class)
    public void update() {
        storage.update(new Resume(UUID_1));
        Assert.assertSame(resume1, storage.get(UUID_1));
    }

    @Test
    public void getAll() {
        Resume[] array = {resume1, resume2, resume3, resume4};
        Assert.assertArrayEquals(array, storage.getAll());
    }

    @Test
    public void save() {
        storage.save(new Resume(UUID_5));
        Assert.assertEquals(new Resume(UUID_5), storage.get(UUID_5));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_4));
    }

    @Test
    public void saveOverflow() {
        String[] strArray = new String[STORAGE_LIMIT];
        try {
            for (int i = 0; i < STORAGE_LIMIT; i++) {
                int j = i + 1;
                String id = "uuid" + j;
                strArray[i] = id;
            }
            storage.clear();
            for (String str : strArray) {
                storage.save(new Resume(str));
            }
        } catch (StorageException e) {
            storage.save(resume10001);
            fail("Expected StorageException");
            assertNotEquals("", e.getMessage());
        }
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteExist() {
        storage.delete(UUID_2);
        Assert.assertEquals(resume2, storage.get(UUID_2));
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