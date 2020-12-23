import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import refactoring.database.DataBase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.List;

import static org.mockito.Mockito.when;

public class TestHelper {

    @Mock
    protected HttpServletRequest request;
    @Mock
    protected HttpServletResponse response;

    protected final StringWriter stringWriter = new StringWriter();
    protected final DataBase dataBase = new DataBase();

    @Before
    public void setUp() throws IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        clearTable();
        dataBase.createTableIfNotExist();
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
    }

    @After
    public void clearTable() throws SQLException {
        dataBase.executeSQL("DELETE FROM PRODUCT");
    }

    protected String expectedGetResponse(List<Product> products) {
        StringBuilder res = new StringBuilder();
        for (Product product : products) {
            res.append(product.name).append("\t").append(product.price).append("</br>\n");
        }

        return wrap(res.toString());
    }

    protected String wrap(String s) {
        return "<html><body>\n" + s + "</body></html>\n";
    }


    protected static class Product {
        public final String name;
        public final String price;

        public Product(String name, String price) {
            this.name = name;
            this.price = price;
        }
    }
}
