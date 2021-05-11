package dao;

import models.DepNews;
import models.Departments;
import models.Users;
import models.dao.Sql2oDepNewsDao;
import models.dao.Sql2oDepartmentDao;
import models.dao.Sql2oUsersDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Sql2oDepNewsDaoTest {
    private static Connection conn;
    private static Sql2oDepartmentDao departmentDao;
    private static Sql2oDepNewsDao depNewsDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/news_portal_test";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", null);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        depNewsDao = new Sql2oDepNewsDao(sql2o);

        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        departmentDao.clearAll();
        depNewsDao.clearAll();
        System.out.println("clearing database");
    }

    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("connection closed");
    }
    @Test
    public void addingDepNewsSetsId() throws Exception {
        DepNews testNews = setupDepNews();
        int originalDepNewsId = testNews.getId();
        depNewsDao.add(testNews);

        assertNotEquals(originalDepNewsId,testNews.getId());
    }
    @Test
    public void addedDepNewsAreReturnedFromGetAll() throws Exception {
        DepNews testNews = setupDepNews();
        depNewsDao.add(testNews);
        assertEquals(1, depNewsDao.all().size());
    }
    @Test
    public void noDepNewsReturnsEmptyList() throws Exception {
        assertEquals(0, depNewsDao.all().size());
    }
    @Test
    public void deleteByIdDeletesCorrectDepNews() throws Exception {
        DepNews testNews = setupDepNews();
        depNewsDao.add(testNews);
        depNewsDao.deleteById(testNews.getId());
        assertEquals(0, depNewsDao.all().size());
    }
    @Test
    public void getAllDepartmentsOfNews() throws Exception {
        Departments testDepartment =setupDepartment();
        departmentDao.add(testDepartment);
        Departments altDepartment =setupAltDepartment();
        departmentDao.add(altDepartment);


        DepNews testNews = setupDepNews();
        depNewsDao.add(testNews);
        depNewsDao.addNewsToDepartment(testNews,testDepartment);
        depNewsDao.addNewsToDepartment(testNews,testDepartment);


        Departments[] departments = {testDepartment, altDepartment};

        assertEquals(Arrays.asList(departments), depNewsDao.getAllDepartmentsOfNews(testNews.getId()));
    }
    @Test
    public void clearAll() throws Exception {
        DepNews testNews = setupDepNews();
        depNewsDao.add(testNews);
        DepNews altNews = setupAltDepNews();
        depNewsDao.add(altNews);

        depNewsDao.clearAll();
        assertEquals(0, depNewsDao.all().size());
    }

    public DepNews setupDepNews(){
        return new DepNews(2,"Marketing 101","organisation's marketing stategies and policies..","Wacuka Davis");
    }
    public DepNews setupAltDepNews(){
        return new DepNews(2,"End Of Year meeting","End of year departmental meeting to be held on...","Wacuka Davis");
    }
    public Departments setupDepartment(){
        return new Departments("Marketing","manage organistations marketing strategies and sales",44);
    }
    public Departments setupAltDepartment(){
        return new Departments("Tech"," management relevant organiasation's tech facilities",20);
    }
}
