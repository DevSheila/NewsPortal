import com.google.gson.Gson;
import exceptions.ApiException;
import models.Users;
import models.dao.Sql2oDepNewsDao;
import models.dao.Sql2oDepartmentDao;
import models.dao.Sql2oGeneralNewsDao;
import models.dao.Sql2oUsersDao;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
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
