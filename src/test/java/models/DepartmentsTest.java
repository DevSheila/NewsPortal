package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DepartmentsTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void getNameReturnsCorrectName() throws Exception {
        Departments testDepartment = setupDepartment();
        assertEquals("Marketing", testDepartment.getName());
    }
    @Test
    public void getDescriptionReturnsCorrectDescritption() throws Exception {
        Departments testDepartment = setupDepartment();

        assertEquals("manage organistations marketing strategies and sales", testDepartment.getDescription());
    }
    @Test
    public void getEmploeeesNumReturnsCorrectNoEmployees() throws Exception {
        Departments testDepartment = setupDepartment();
        assertEquals(22, testDepartment.getNumEmployees());
    }

    @Test
    public void setNameSetsCorrectName() throws Exception {
        Departments testDepartment = setupDepartment();

        testDepartment.setName("Sales and Marketing");
        assertNotEquals("Marketing",testDepartment.getName());
    }

    @Test
    public void setDescritptionSetsCorrectDescritption() throws Exception {
        Departments testDepartment = setupDepartment();

        testDepartment.setDescription("Manages relevant organiasation's tech facilities");
        assertNotEquals("manage organistations marketing strategies and sales",testDepartment.getDescription());
    }
    @Test
    public void setNumEmployeesSetsCorrectNumEmployees() throws Exception {
        Departments testDepartment = setupDepartment();

        testDepartment.setNumEmployees(40);
        assertNotEquals(22,testDepartment.getNumEmployees());
    }

    public Departments setupDepartment (){
        return new Departments("Marketing","manage organistations marketing strategies and sales",22);
    }
    public Departments setupAltDepartment (){
        return new Departments("IT departments", "Manages relevant organiasation's tech facilities", 13);
    }
}
