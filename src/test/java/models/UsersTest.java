package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UsersTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getName() {
        Users testUser = setupUser();
        assertEquals("Jane", testUser.getName());
    }
    @Test
    public void setName() {
        Users testUser = setupUser();
        testUser.setName("Jane");
        assertNotEquals("Diane", testUser.getName());
    }
    @Test
    public void setDepId() {
        Users testUser = setupUser();
        testUser.setDepId(6);
        assertNotEquals(4, testUser.getDepId());
    }
    @Test
    public void setId() {
        Users testUser = setupUser();
        testUser.setId(300);
        assertEquals(300, testUser.getId());
    }

    // helper
    public Users setupUser(){
        return new Users("Jane",1,"manager","heads marketing department");
    }
}
