package dao;

import models.DepNews;
import models.Departments;

import models.GeneralNews;
import models.dao.Sql2oDepNewsDao;
import models.dao.Sql2oDepartmentDao;

import models.dao.Sql2oGeneralNewsDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Sql2oGeneralNewsDaoTest {
    private static Connection conn;

    private static Sql2oGeneralNewsDao generalNewsDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/news_portal_test";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", null);

        generalNewsDao = new Sql2oGeneralNewsDao(sql2o);

        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        generalNewsDao.clearAll();

        System.out.println("clearing database");
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void addingGeneralNewsSetsId() throws Exception {
        GeneralNews testNews = setupGeneralNews();
        int originalGeneralNewsId = testNews.getId();
        generalNewsDao.add(testNews);

        assertNotEquals(originalGeneralNewsId, testNews.getId());
    }

    @Test
    public void addedGeneralNewsAreReturnedFromGetAll() throws Exception {
        GeneralNews testNews = setupGeneralNews();
        generalNewsDao.add(testNews);
        assertEquals(1, generalNewsDao.all().size());
    }

    @Test
    public void noGeneralNewsReturnsEmptyList() throws Exception {
        assertEquals(0, generalNewsDao.all().size());
    }

    @Test
    public void deleteByIdDeletesCorrectGeneralNews() throws Exception {
        GeneralNews testNews = setupGeneralNews();
        generalNewsDao.add(testNews);

        generalNewsDao.deleteById(testNews.getId());
        assertEquals(0, generalNewsDao.all().size());
    }

    @Test
    public void clearAll() throws Exception {
        GeneralNews testNews = setupGeneralNews();
        generalNewsDao.add(testNews);
        GeneralNews altNews = setupAltGeneralNews();
        generalNewsDao.add(altNews);

        generalNewsDao.clearAll();
        assertEquals(0,generalNewsDao.all().size());
    }

    public GeneralNews setupGeneralNews() {
        return new GeneralNews("Marketing 101", "organisation's marketing stategies and policies..", "Wacuka Davis");
    }

    public GeneralNews setupAltGeneralNews() {
        return new GeneralNews("Marketing policies", "organisation's marketing policies..", "Wacuka Davis");
    }
}


