package refactoring.html;

import refactoring.utils.Product;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class HtmlManager {

    public static String printSingleInt(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return resultSet.getInt(1) + "\n";
        } else {
            return "";
        }
    }

    public static String printAllEntries(ResultSet resultSet) throws SQLException {
        StringBuilder sb = new StringBuilder();

        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int price = resultSet.getInt("price");
            sb.append(name).append("\t").append(price).append("</br>\n");
        }

        return sb.toString();
    }

    public static void printResponse(Optional<String> title, String body, PrintWriter printWriter) {
        printWriter.println("<html><body>");
        title.ifPresent(printWriter::println);
        printWriter.print(body);
        printWriter.println("</body></html>");
    }

    public static Product requestProduct(HttpServletRequest request) {
        return new Product(request.getParameter("name"), request.getParameter("price"));
    }

    public static void printOK(PrintWriter printWriter) {
        printWriter.println("OK");
    }

    public static String wrap(String s) {
        return "<h1>" + s + "</h1>";
    }
}
