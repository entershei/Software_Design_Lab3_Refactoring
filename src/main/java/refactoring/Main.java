package refactoring;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import refactoring.database.Database;
import refactoring.servlet.AddProductServlet;
import refactoring.servlet.GetProductsServlet;
import refactoring.servlet.QueryServlet;

import static refactoring.database.DatabaseUtils.createTableIfNotExist;

/**
 * @author akirakozov
 */
public class Main {

    public static void main(String[] args) throws Exception {
        final Database database = new Database("jdbc:sqlite:test.db");

        createTableIfNotExist(database);

        Server server = new Server(8081);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(new ServletHolder(new AddProductServlet(database)), "/add-product");
        context.addServlet(new ServletHolder(new GetProductsServlet(database)), "/get-products");
        context.addServlet(new ServletHolder(new QueryServlet(database)), "/query");

        server.start();
        server.join();
    }
}
