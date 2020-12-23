package refactoring.query;

import refactoring.database.Database;
import refactoring.utils.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static refactoring.database.DatabaseUtils.addProductToTable;
import static refactoring.html.HtmlManager.printOK;
import static refactoring.html.HtmlManager.requestProduct;

public class AddProductHandler implements Handler {

    private final Database database;

    public AddProductHandler(Database database) {
        this.database = database;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        Product product = requestProduct(request);

        addProductToTable(product.getName(), Long.toString(product.getPrice()), database);

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        printOK(response.getWriter());
    }
}
