package models.dao;

import models.DepNews;
import models.Departments;

import java.util.List;

public interface DepNewsDao {

    void add (DepNews news);
    void addDepartmentToNews(Departments department, DepNews news);

    List<Departments> all();
    List<DepNews> getAllNewsDepartments(int newsId);

    Departments findById(int id);
    void deleteById(int id);
    void clearAll();
}
