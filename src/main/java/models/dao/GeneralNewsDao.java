package models.dao;



import models.GeneralNews;

import java.util.List;

public interface GeneralNewsDao {

    void add (GeneralNews news);

    List<GeneralNews> all();

    GeneralNews findById(int id);
    void deleteById(int id);
    void clearAll();
}
