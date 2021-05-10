package models.dao;


import models.DepNews;
import models.Departments;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oDepartmentDao implements  DepartmentDao{
    private final Sql2o sql2o;
    public Sql2oDepartmentDao(Sql2o sql2o){
        this.sql2o=sql2o;
    }

    @Override
    public void add(Departments department) {
        String sql = "INSERT INTO departments (name,description,num_employees) VALUES (:name)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(department)
                    .executeUpdate()
                    .getKey();
            department.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addDepartmentToNews(Departments department, DepNews news) {
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
    public List<Departments> all() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM departments")
                    .executeAndFetch(Departments.class);
        }
    }

    @Override
    public List<DepNews> getAllDepartmentNews(int depId) {
        List<DepNews> news = new ArrayList();
        String joinQuery = "SELECT news_id FROM departments_news WHERE dep_id = :depId";

        try (Connection con = sql2o.open()) {
            List<Integer> allNewsId = con.createQuery(joinQuery)
                    .addParameter("depId", depId)
                    .executeAndFetch(Integer.class);
            for (Integer  newsId: allNewsId){
                String newsQuery = "SELECT * FROM news WHERE id = :newsId";
                news.add(
                        con.createQuery(newsQuery)
                                .addParameter("newsId", newsId)
                                .executeAndFetchFirst(DepNews.class));
            } //why are we doing a second sql query - set?
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return news;
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
