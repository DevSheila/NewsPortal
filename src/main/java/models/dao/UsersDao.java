package models.dao;

import models.DepNews;
import models.Departments;
import models.Users;

import java.util.List;

public interface UsersDao {

    void add (Users user);

    List<Users> all();

    Users findById(int id);
    void deleteById(int id);
    void clearAll();
}
