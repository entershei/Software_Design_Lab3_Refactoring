package refactoring.servlet;

import refactoring.database.Database;
import refactoring.query.GetProductsHandler;
import refactoring.query.QueryHandler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends HttpServlet {

    private final Database database;

    public GetProductsServlet(Database database) {
        this.database = database;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        QueryHandler handler = new GetProductsHandler(database);
        try {
            handler.execute(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
