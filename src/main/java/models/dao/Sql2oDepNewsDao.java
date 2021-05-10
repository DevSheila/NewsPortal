package models.dao;

import models.DepNews;
import models.Departments;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oDepNewsDao implements DepNewsDao {
    private final Sql2o sql2o;
    public Sql2oDepNewsDao(Sql2o sql2o){
        this.sql2o=sql2o;
    }


    @Override
    public void add(DepNews depNews) {
        String sql = "INSERT INTO news (dep_id,title,body,writtenBy,type) VALUES (:depId,title,body,writtenBy,type)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(depNews)
                    .executeUpdate()
                    .getKey();
            depNews.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addDepartmentToNews(Departments department, DepNews news) {

    }

    @Override
    public List<Departments> all() {
        return null;
    }

    @Override
    public List<DepNews> getAllNewsDepartments(int newsId) {
        return null;
    }

    @Override
    public Departments findById(int id) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }
}
