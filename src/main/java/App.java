import com.google.gson.Gson;
import exceptions.ApiException;
import models.DepNews;
import models.Departments;
import models.GeneralNews;
import models.Users;
import models.dao.Sql2oDepNewsDao;
import models.dao.Sql2oDepartmentDao;
import models.dao.Sql2oGeneralNewsDao;
import models.dao.Sql2oUsersDao;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        Sql2oDepartmentDao departmentDao;
        Sql2oDepNewsDao depNewsDao;
        Sql2oGeneralNewsDao generalNewsDao;
        Sql2oUsersDao usersDao;

        Connection conn;
        Gson gson = new Gson();

        staticFileLocation("/public");

        String connectionString = "jdbc:postgresql://localhost:5432/news_portal";   //connect to jadle, not jadle_test! try not to copy paste
        Sql2o sql2o = new Sql2o(connectionString, "postgres", null);

        departmentDao = new Sql2oDepartmentDao(sql2o);
        depNewsDao = new Sql2oDepNewsDao(sql2o);
        generalNewsDao = new Sql2oGeneralNewsDao(sql2o);
        usersDao = new Sql2oUsersDao(sql2o);
        //CREATE
        post("/users/new", "application/json", (req, res) -> {
            Users user = gson.fromJson(req.body(), Users.class);
            usersDao.add(user);
            res.status(201);
            return gson.toJson(user);
        });

        post("/departments/new", "application/json", (req, res) -> {
            Departments department = gson.fromJson(req.body(), Departments.class);
            departmentDao.add(department);
            res.status(201);
            return gson.toJson(department);
        });


        post("/generalNews/new", "application/json", (req, res) -> {
            GeneralNews news = gson.fromJson(req.body(), GeneralNews.class);
            generalNewsDao.add(news);
            res.status(201);
            return gson.toJson(generalNewsDao);
        });

        post("/departments/:id/news/new", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("id"));
            DepNews news= gson.fromJson(req.body(), DepNews.class);
            depNewsDao.add(news);
            res.status(201);
            return gson.toJson(news);
        });

        //READ
        get("/departments", "application/json", (req, res) -> {
            System.out.println(departmentDao.all());

            if(departmentDao.all().size() > 0){
                return gson.toJson(departmentDao.all());
            }

            else {
                return "{\"message\":\"I'm sorry, but no departments are currently listed in the database.\"}";
            }

        });
        get("/users", "application/json", (req, res) -> {
            System.out.println(usersDao.all());

            if(usersDao.all().size() > 0){
                return gson.toJson(usersDao.all());
            }

            else {
                return "{\"message\":\"I'm sorry, but no users are currently listed in the database.\"}";
            }

        });
        get("/generalNews", "application/json", (req, res) -> {
            System.out.println(generalNewsDao.all());

            if(generalNewsDao.all().size() > 0){
                return gson.toJson(generalNewsDao.all());
            }

            else {
                return "{\"message\":\"I'm sorry, but there is no general news currently listed in the database.\"}";
            }

        });

        get("/departments/:id", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("id"));
            Departments departmentToFind = departmentDao.findById(departmentId);
            if (departmentToFind == null){
                throw new ApiException(404, String.format("No departments with the id: \"%s\" exists", req.params("id")));
            }
            return gson.toJson(departmentToFind);

        });

        get("/users/:id", "application/json", (req, res) -> {
            int userId = Integer.parseInt(req.params("id"));
            Users userToFind = usersDao.findById(userId);
            if (userToFind == null){
                throw new ApiException(404, String.format("No user with the id: \"%s\" exists", req.params("id")));
            }
            return gson.toJson(userToFind);

        });
        get("/generalNews/:id", "application/json", (req, res) -> {
            int newsId = Integer.parseInt(req.params("id"));
            GeneralNews newsToFind = generalNewsDao.findById(newsId);
            if (newsToFind == null){
                throw new ApiException(404, String.format("No general news with the id: \"%s\" exists", req.params("id")));
            }
            return gson.toJson(newsToFind);

        });

        //FILTERS
        exception(ApiException.class, (exception, req, res) -> {
            ApiException err = exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });


        after((req, res) ->{
            res.type("application/json");
        });
    }
}
