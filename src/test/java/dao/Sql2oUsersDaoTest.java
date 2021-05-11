package dao;

import models.Users;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;

import models.dao.Sql2oUsersDao;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Sql2oUsersDaoTest {
    private static Connection conn;
    private static Sql2oUsersDao usersDao;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/news_portal_test";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", null);
       usersDao = new Sql2oUsersDao(sql2o);

        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        usersDao.clearAll();
        System.out.println("clearing database");
    }

    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("connection closed");
    }
    @Test
    public void addingUserSetsId() throws Exception {
        Users testUser = setupUser();
        int originalUserId = testUser.getId();
        usersDao.add(testUser);
        assertNotEquals(originalUserId,testUser.getId());
    }
    @Test
    public void addedUsersAreReturnedFromGetAll() throws Exception {
        Users testUser = setupUser();
        usersDao.add(testUser);
        assertEquals(1, usersDao.all().size());
    }
    @Test
    public void noUsersReturnsEmptyList() throws Exception {
        assertEquals(0, usersDao.all().size());
    }
    @Test
    public void deleteByIdDeletesCorrectUser() throws Exception {
        Users testUser = setupUser();
        usersDao.add(testUser);
        usersDao.deleteById(testUser.getId());
        assertEquals(0, usersDao.all().size());
    }

    @Test
    public void clearAll() throws Exception {
        Users testUser = setupUser();
        Users altUser = setupAltUser();
        usersDao.add(testUser);
        usersDao.add(altUser);
        usersDao.clearAll();
        assertEquals(0, usersDao.all().size());
    }
    public Users setupUser (){
        return new Users("Joan",4,"assistant manager","organising department employees roles, position and workflow");
    }
    public Users setupAltUser (){
        return new Users("Danny",3,"software developer","developing web apps for clients");
    }

}
