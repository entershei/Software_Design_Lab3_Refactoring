import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import refactoring.database.Database;
import refactoring.utils.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.List;

import static org.mockito.Mockito.when;
import static refactoring.database.DatabaseUtils.createTableIfNotExist;

public class TestHelper {

    protected final StringWriter stringWriter = new StringWriter();
    protected final Database database = new Database("jdbc:sqlite:test.db");
    @Mock
    protected HttpServletRequest request;
    @Mock
    protected HttpServletResponse response;

    @Before
    public void setUp() throws IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        clearTable();
        createTableIfNotExist(database);
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
    }

    @After
    public void clearTable() throws SQLException {
        database.executeSql("DELETE FROM PRODUCT");
    }

    protected String expectedGetResponse(List<Product> products) {
        StringBuilder res = new StringBuilder();
        for (Product product : products) {
            res.append(product.getName()).append("\t").append(product.getPrice()).append("</br>\n");
        }

        return wrap(res.toString());
    }

    protected String wrap(String s) {
        return "<html><body>\n" + s + "</body></html>\n";
    }
}
