import org.junit.Assert;
import org.junit.Test;
import refactoring.servlet.GetProductsServlet;
import refactoring.utils.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static refactoring.database.DatabaseUtils.addProductToTable;

public class GetProductsServletTest extends TestHelper {

    private final GetProductsServlet servlet = new GetProductsServlet(database);

    @Test
    public void NoTableExist() {
        servlet.doGet(request, response);
        Assert.assertEquals(expectedGetResponse(new ArrayList<>()), stringWriter.toString());
    }

    @Test
    public void OneProduct() throws SQLException {
        List<Product> products = Collections.singletonList(new Product("Car", "1000000"));

        addProductToTable(products.get(0).getName(), Long.toString(products.get(0).getPrice()), database);
        servlet.doGet(request, response);

        Assert.assertEquals(expectedGetResponse(products), stringWriter.toString());
    }

    @Test
    public void ManyProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Car", "3000000"));
        products.add(new Product("Bicycle", "10000"));
        products.add(new Product("headphones", "7000"));

        for (Product product : products) {
            addProductToTable(product.getName(), Long.toString(product.getPrice()), database);
        }
        servlet.doGet(request, response);

        Assert.assertEquals(expectedGetResponse(products), stringWriter.toString());
    }
}
