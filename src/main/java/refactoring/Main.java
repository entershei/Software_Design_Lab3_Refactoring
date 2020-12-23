package refactoring;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import refactoring.database.DataBase;
import refactoring.servlet.AddProductServlet;
import refactoring.servlet.GetProductsServlet;
import refactoring.servlet.QueryServlet;

import static refactoring.database.DataBaseUtils.createTableIfNotExist;

/**
 * @author akirakozov
 */
public class Main {

    public static void main(String[] args) throws Exception {
        final DataBase dataBase = new DataBase("jdbc:sqlite:test.db");

        createTableIfNotExist(dataBase);

        Server server = new Server(8081);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(new ServletHolder(new AddProductServlet(dataBase)), "/add-product");
        context.addServlet(new ServletHolder(new GetProductsServlet(dataBase)),"/get-products");
        context.addServlet(new ServletHolder(new QueryServlet(dataBase)),"/query");

        server.start();
        server.join();
    }
}
