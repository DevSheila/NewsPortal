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

public class Sql2oDepartmentsDaoTest {
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
    public void addingDepartmentSetsId() throws Exception {
       Departments testDepartment =setupDepartment();
        int originalDepId = testDepartment.getId();
        departmentDao.add(testDepartment);

        assertNotEquals(originalDepId,testDepartment.getId());
    }
    @Test
    public void addedDepartmentsAreReturnedFromGetAll() throws Exception {
        Departments testDepartment =setupDepartment();
        departmentDao.add(testDepartment);
        assertEquals(1, departmentDao.all().size());
    }
    @Test
    public void noDepartmenstReturnsEmptyList() throws Exception {
        assertEquals(0, departmentDao.all().size());
    }
    @Test
    public void deleteByIdDeletesCorrectDepartment() throws Exception {
        Departments testDepartment =setupDepartment();
        departmentDao.add(testDepartment);
       departmentDao.deleteById(testDepartment.getId());
        assertEquals(0, departmentDao.all().size());
    }
    @Test
    public void getAllDepartmentNews() throws Exception {
        DepNews testNews  = new DepNews(4,"Marketing101","Organisation's marketing principles ...","Wacuka Kimani");
        DepNews altNews  = new DepNews(4,"Tech policies","Organisation's software handling principles ...","Teddy Dulani");
        depNewsDao.add(testNews);
        depNewsDao.add(altNews);

        Departments testDepartment =setupDepartment();
        departmentDao.add(testDepartment);
        departmentDao.addDepartmentToNews(testDepartment,testNews);
        departmentDao.addDepartmentToNews(testDepartment,altNews);


        assertEquals(depNewsDao.all(), departmentDao.getAllDepartmentNews(testDepartment.getId()));
    }
    @Test
    public void clearAll() throws Exception {
        Departments testDepartment =setupDepartment();
        departmentDao.add(testDepartment);
        Departments altDepartment =setupAltDepartment();
        departmentDao.add(altDepartment);

        departmentDao.clearAll();
        assertEquals(0, departmentDao.all().size());
    }

    public Departments setupDepartment(){
        return new Departments("Marketing","manage organistations marketing strategies and sales",44);
    }
    public Departments setupAltDepartment(){
        return new Departments("Tech"," management relevant organiasation's tech facilities",20);
    }
}
