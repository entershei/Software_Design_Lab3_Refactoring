import org.junit.Assert;
import org.junit.Test;
import refactoring.servlet.AddProductServlet;
import refactoring.utils.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

public class AddProductServletTest extends TestHelper {

    private final AddProductServlet servlet = new AddProductServlet(database);

    private void addProducts(List<Product> products) {
        for (Product product : products) {
            when(request.getParameter("name")).thenReturn(product.getName());
            when(request.getParameter("price")).thenReturn(Long.toString(product.getPrice()));
        }
        servlet.doGet(request, response);
    }

    @Test
    public void OneProduct() {
        addProducts(Collections.singletonList(new Product("Book", "300")));
        Assert.assertEquals("OK\n", stringWriter.toString());
    }

    @Test
    public void ManyProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Book", "300"));
        products.add(new Product("Book2", "400"));
        addProducts(products);

        Assert.assertEquals("OK\n", stringWriter.toString());
    }

    @Test(expected = NumberFormatException.class)
    public void IncorrectPrice() {
        addProducts(Collections.singletonList(new Product("Book", "300.2")));

        Assert.assertEquals("OK\n", stringWriter.toString());
    }

    @Test(expected = NumberFormatException.class)
    public void NullNameAndPrice() {
        addProducts(Collections.singletonList(new Product(null, null)));
        Assert.assertEquals("OK\n", stringWriter.toString());
    }
}
