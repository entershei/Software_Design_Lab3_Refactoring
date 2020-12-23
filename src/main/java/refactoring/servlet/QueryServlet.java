package refactoring.servlet;

import refactoring.database.DataBase;
import refactoring.query.QueryHandler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static refactoring.query.HandlerFactory.getHandler;

/**
 * @author akirakozov
 */
public class QueryServlet extends HttpServlet {

    private final DataBase database;

    public QueryServlet(DataBase database) {
        this.database = database;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        QueryHandler handler = getHandler(request, database);
        try {
            handler.execute(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
