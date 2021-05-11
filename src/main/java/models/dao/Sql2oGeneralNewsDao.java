package models.dao;

import models.GeneralNews;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oGeneralNewsDao implements GeneralNewsDao {
    private final Sql2o sql2o;
    public Sql2oGeneralNewsDao(Sql2o sql2o){
        this.sql2o=sql2o;
    }


    @Override
    public void add(GeneralNews generalNews) {
        String sql = "INSERT INTO news (dep_id,title,body,written_by,type) VALUES (:dep_id,:title,:body,:written_by,:type)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .addParameter("dep_id", generalNews.getDep_id())
                    .addParameter("title", generalNews.getTitle())
                    .addParameter("body", generalNews.getBody())
                    .addParameter("written_by", generalNews.getWrittenBy())
                    .addParameter("type", "general")
                    .executeUpdate()
                    .getKey();
            generalNews.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }



    @Override
    public List<GeneralNews> all() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM news WHERE type ='general'")
                    .executeAndFetch(GeneralNews.class);
        }
    }




    @Override
    public GeneralNews findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM news WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(GeneralNews.class);
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
        String sql = "DELETE from news ";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
