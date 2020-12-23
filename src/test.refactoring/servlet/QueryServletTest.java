import org.junit.Assert;
import org.junit.Test;
import refactoring.servlet.QueryServlet;

import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.when;

public class QueryServletTest extends TestHelper {

    private final QueryServlet servlet = new QueryServlet();

    @Test
    public void EmptyTableMaxCommand() throws IOException {
        when(request.getParameter("command")).thenReturn("max");
        servlet.doGet(request, response);
        Assert.assertEquals(maxResponse("", ""), stringWriter.toString());
    }

    @Test
    public void EmptyTableMinCommand() throws IOException {
        when(request.getParameter("command")).thenReturn("min");
        servlet.doGet(request, response);
        Assert.assertEquals(minResponse("", ""), stringWriter.toString());
    }

    @Test
    public void EmptyTableSumCommand() throws IOException {
        when(request.getParameter("command")).thenReturn("sum");
        servlet.doGet(request, response);
        Assert.assertEquals(sumResponse(0), stringWriter.toString());
    }

    @Test
    public void EmptyTableCountCommand() throws IOException {
        when(request.getParameter("command")).thenReturn("count");
        servlet.doGet(request, response);
        Assert.assertEquals(numberOfProductsResponse(0), stringWriter.toString());
    }

    @Test
    public void EmptyTableUnknownCommand() throws IOException {
        when(request.getParameter("command")).thenReturn("log");
        servlet.doGet(request, response);
        Assert.assertEquals(unknownCommandResponse("log"), stringWriter.toString());
    }

    @Test
    public void MaxCommand() throws SQLException, IOException {
        when(request.getParameter("command")).thenReturn("max");
        addExamples();

        servlet.doGet(request, response);
        Assert.assertEquals(maxResponse("Cup", "200"), stringWriter.toString());
    }

    @Test
    public void MinCommand() throws SQLException, IOException {
        when(request.getParameter("command")).thenReturn("min");
        addExamples();

        servlet.doGet(request, response);
        Assert.assertEquals(minResponse("Sugar", "70"), stringWriter.toString());
    }

    @Test
    public void SumCommand() throws SQLException, IOException {
        when(request.getParameter("command")).thenReturn("sum");
        addExamples();

        servlet.doGet(request, response);
        Assert.assertEquals(sumResponse(370), stringWriter.toString());
    }

    @Test
    public void CountCommand() throws SQLException, IOException {
        when(request.getParameter("command")).thenReturn("count");
        addExamples();

        servlet.doGet(request, response);
        Assert.assertEquals(numberOfProductsResponse(3), stringWriter.toString());
    }

    @Test
    public void UnknownCommand() throws SQLException, IOException {
        when(request.getParameter("command")).thenReturn("log");
        addExamples();

        servlet.doGet(request, response);
        Assert.assertEquals(unknownCommandResponse("log"), stringWriter.toString());
    }


    private void addExamples() throws SQLException {
        dataBase.addProductToTable("Sugar", "70");
        dataBase.addProductToTable("Cup", "200");
        dataBase.addProductToTable("Tea", "100");
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
