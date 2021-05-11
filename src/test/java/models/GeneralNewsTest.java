package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GeneralNewsTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void getitleReturnsCorrectNewsTitle() throws Exception {
        GeneralNews testNews = setupGeneralNews();
        assertEquals("Marketing Stategies 101", testNews.getTitle());
    }
    @Test
    public void getBodyReturnsCorrectNewsBody() throws Exception {
        GeneralNews testNews = setupGeneralNews();
        assertEquals("Revisit on organisation's marketing polcies...", testNews.getBody());
    }
    @Test
    public void getWrittenByNumReturnsCorrectWrittenBy() throws Exception {
        GeneralNews testNews = setupGeneralNews();
        assertEquals("head of Marketing Department", testNews.getWrittenBy());
    }

    @Test
    public void setTitleSetsCorrectNewsTitle() throws Exception {
        GeneralNews testNews = setupGeneralNews();
        testNews.setTitle("Organisation Marketing Policies");
        assertNotEquals("Marketing",testNews.getTitle());
    }

    @Test
    public void setBodySetsCorrectBody() throws Exception {
        GeneralNews testNews = setupGeneralNews();
        testNews.setBody("Optimaise management relevant organiasation's tech facilities.....");
        assertNotEquals("manage organistations marketing strategies and sales",testNews.getBody());
    }
    @Test
    public void setWrittenByEmployeesSetsCorrectWrittenBy() throws Exception {
        GeneralNews testNews = setupGeneralNews();

        testNews.setWrittenBy("head of Marketing Department");
        assertNotEquals("head of IT",testNews.getWrittenBy());
    }

    public GeneralNews setupGeneralNews (){
        return new GeneralNews("Marketing Stategies 101","Revisit on organisation's marketing polcies...","head of Marketing Department");
    }
    public GeneralNews setupAltDepNews (){
        return new GeneralNews("Firewall Breach","There has beena minor breach on organisation's firewall...","head of Tech Department");

    }
}
