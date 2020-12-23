import org.junit.Assert;
import org.junit.Test;
import refactoring.servlet.QueryServlet;

import java.sql.SQLException;

import static org.mockito.Mockito.when;
import static refactoring.database.DatabaseUtils.addProductToTable;

public class QueryServletTest extends TestHelper {

    private final QueryServlet servlet = new QueryServlet(database);

    @Test
    public void EmptyTableMaxCommand() {
        when(request.getParameter("command")).thenReturn("max");
        servlet.doGet(request, response);
        Assert.assertEquals(maxResponse("", ""), stringWriter.toString());
    }

    @Test
    public void EmptyTableMinCommand() {
        when(request.getParameter("command")).thenReturn("min");
        servlet.doGet(request, response);
        Assert.assertEquals(minResponse("", ""), stringWriter.toString());
    }

    @Test
    public void EmptyTableSumCommand() {
        when(request.getParameter("command")).thenReturn("sum");
        servlet.doGet(request, response);
        Assert.assertEquals(sumResponse(0), stringWriter.toString());
    }

    @Test
    public void EmptyTableCountCommand() {
        when(request.getParameter("command")).thenReturn("count");
        servlet.doGet(request, response);
        Assert.assertEquals(numberOfProductsResponse(0), stringWriter.toString());
    }

    @Test
    public void EmptyTableUnknownCommand() {
        when(request.getParameter("command")).thenReturn("log");
        servlet.doGet(request, response);
        Assert.assertEquals(unknownCommandResponse("log"), stringWriter.toString());
    }

    @Test
    public void MaxCommand() throws SQLException {
        when(request.getParameter("command")).thenReturn("max");
        addExamples();

        servlet.doGet(request, response);
        Assert.assertEquals(maxResponse("Cup", "200"), stringWriter.toString());
    }

    @Test
    public void MinCommand() throws SQLException {
        when(request.getParameter("command")).thenReturn("min");
        addExamples();

        servlet.doGet(request, response);
        Assert.assertEquals(minResponse("Sugar", "70"), stringWriter.toString());
    }

    @Test
    public void SumCommand() throws SQLException {
        when(request.getParameter("command")).thenReturn("sum");
        addExamples();

        servlet.doGet(request, response);
        Assert.assertEquals(sumResponse(370), stringWriter.toString());
    }

    @Test
    public void CountCommand() throws SQLException {
        when(request.getParameter("command")).thenReturn("count");
        addExamples();

        servlet.doGet(request, response);
        Assert.assertEquals(numberOfProductsResponse(3), stringWriter.toString());
    }

    @Test
    public void UnknownCommand() throws SQLException {
        when(request.getParameter("command")).thenReturn("integral");
        addExamples();

        servlet.doGet(request, response);
        Assert.assertEquals(unknownCommandResponse("integral"), stringWriter.toString());
    }


    private void addExamples() throws SQLException {
        addProductToTable("Sugar", "70", database);
        addProductToTable("Cup", "200", database);
        addProductToTable("Tea", "100", database);
    }

    private String maxResponse(String name, String price) {
        return wrap("<h1>Product with max price: </h1>\n" + nameAndPrice(name, price));
    }

    private String minResponse(String name, String price) {
        return wrap("<h1>Product with min price: </h1>\n" + nameAndPrice(name, price));
    }

    private String sumResponse(int price) {
        return wrap("Summary price: \n" + price + "\n");
    }

    private String numberOfProductsResponse(int num) {
        return wrap("Number of products: \n" + num + "\n");
    }

    private String unknownCommandResponse(String s) {
        return "Unknown command: " + s + "\n";
    }

    private String nameAndPrice(String name, String price) {
        if (name != null && !name.isEmpty()) {
            return name + "\t" + price + "</br>\n";
        }
        return "";
    }
}
