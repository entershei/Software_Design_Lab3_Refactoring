package refactoring.servlet;

import refactoring.database.DataBase;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static refactoring.database.DataBaseUtils.addProductToTable;
import static refactoring.html.HtmlManager.printOK;

/**
 * @author akirakozov
 */
public class AddProductServlet extends HttpServlet {

    private final DataBase database;

    public AddProductServlet(DataBase database) {
        this.database = database;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        long price = Long.parseLong(request.getParameter("price"));

        try {
            addProductToTable(name, Long.toString(price), database);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        printOK(response.getWriter());
    }
}
