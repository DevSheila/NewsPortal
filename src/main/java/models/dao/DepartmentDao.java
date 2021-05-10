package models.dao;

import models.DepNews;
import models.Departments;

import java.util.List;

public interface DepartmentDao {

    void add (Departments department);
    void addDepartmentToNews(Departments department, DepNews news);

    List<Departments> all();
    List<DepNews> getAllDepartmentNews();

    void deleteById(int id);
    void clearAll();
}
