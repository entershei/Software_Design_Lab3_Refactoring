package refactoring.servlet;

import refactoring.database.Database;
import refactoring.query.Handler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static refactoring.query.HandlerFactory.getHandler;

/**
 * @author akirakozov
 */
public class QueryServlet extends HttpServlet {

    private final Database database;

    public QueryServlet(Database database) {
        this.database = database;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        Handler handler = getHandler(request, database);
        try {
            handler.execute(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
