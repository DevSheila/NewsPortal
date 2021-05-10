package models.dao;

import models.DepNews;
import models.Departments;

import java.util.List;

public interface DepNewsDao {

    void add (DepNews news);
    void addNewsToDepartment(DepNews news ,Departments department);

    List<DepNews> all();
    List<DepNews> getAllNewsDepartments(int newsId);

    DepNews findById(int id);
    void deleteById(int id);
    void clearAll();
}
