package models.dao;

import models.DepNews;
import models.Departments;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
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
    public void addNewsToDepartment(DepNews news, Departments department) {
        String sql = "INSERT INTO departments_news (dep_id,news_id) VALUES (:depId, :newsId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("depId", department.getId())
                    .addParameter("newsId", news.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<DepNews> all() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM news WHERE type ='department'")
                    .executeAndFetch(DepNews.class);
        }
    }

    @Override
    public List<Departments> getAllDepartmentsOfNews(int newsId) {
        List<Departments> departments = new ArrayList();
        String joinQuery = "SELECT news_id FROM departments_news WHERE news_id = :newsId";

        try (Connection con = sql2o.open()) {
            List<Integer> allDepartmentId = con.createQuery(joinQuery)
                    .addParameter("newsId", newsId)
                    .executeAndFetch(Integer.class);
            for (Integer  depId: allDepartmentId ){
                String newsQuery = "SELECT * FROM departments WHERE id = :depId";
                departments.add(
                        con.createQuery(newsQuery)
                                .addParameter("depId", depId)
                                .executeAndFetchFirst(Departments.class));
            } //why are we doing a second sql query - set?
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return departments;
    }


    @Override
    public DepNews findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM news WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(DepNews.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from news WHERE id=:id";

        String deleteJoin = "DELETE from news WHERE news_id = :newsId";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();

            con.createQuery(deleteJoin)
                    .addParameter("newsId", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public void clearAll() {
        String sql = "DELETE from news";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
