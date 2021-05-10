package models.dao;


import models.DepNews;
import models.Departments;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oDepartmentDao implements  DepartmentDao{
    private final Sql2o sql2o;
    public Sql2oDepartmentDao(Sql2o sql2o){
        this.sql2o=sql2o;
    }

    @Override
    public void add(Departments department) {

    }

    @Override
    public void addDepartmentToNews(Departments department, DepNews news) {

    }

    @Override
    public List<Departments> all() {
        return null;
    }

    @Override
    public List<DepNews> getAllDepartmentNews() {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }
}
