package refactoring.servlet;

import refactoring.database.Database;
import refactoring.query.AddProductHandler;
import refactoring.query.Handler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author akirakozov
 */
public class AddProductServlet extends HttpServlet {

    private final Database database;

    public AddProductServlet(Database database) {
        this.database = database;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        Handler handler = new AddProductHandler(database);

        try {
            handler.execute(request, response);
        } catch (NumberFormatException numberFormatException) {
            throw numberFormatException;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
