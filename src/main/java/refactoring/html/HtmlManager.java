package refactoring.html;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class HtmlManager {

    public static String printSingleInt(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return rs.getInt(1) + "\n";
        } else {
            return "";
        }
    }

    public static String printAllEntries(ResultSet rs) throws SQLException {
        StringBuilder sb = new StringBuilder();

        while (rs.next()) {
            String name = rs.getString("name");
            int price = rs.getInt("price");
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

    public static void printOK(PrintWriter printWriter) {
        printWriter.println("OK");
    }

    public static String wrap(String s) {
        return "<h1>" + s + "</h1>";
    }
}
