import org.junit.Assert;
import org.junit.Test;
import refactoring.servlet.GetProductsServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetProductsServletTest extends TestHelper {

    private final GetProductsServlet servlet = new GetProductsServlet();

    @Test
    public void NoTableExist() throws IOException {
        servlet.doGet(request, response);
        Assert.assertEquals(expectedGetResponse(new ArrayList<>()), stringWriter.toString());
    }

    @Test
    public void OneProduct() throws SQLException, IOException {
        List<Product> products = Collections.singletonList(new Product("Car", "1000000"));

        dataBase.addProductToTable(products.get(0).name, products.get(0).price);
        servlet.doGet(request, response);

        Assert.assertEquals(expectedGetResponse(products), stringWriter.toString());
    }

    @Test
    public void ManyProducts() throws SQLException, IOException {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Car", "3000000"));
        products.add(new Product("Bicycle", "10000"));
        products.add(new Product("headphones", "7000"));

        for (Product product : products) {
            dataBase.addProductToTable(product.name, product.price);
        }
        servlet.doGet(request, response);

        Assert.assertEquals(expectedGetResponse(products), stringWriter.toString());
    }
}
