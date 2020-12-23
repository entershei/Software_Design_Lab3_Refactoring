package refactoring.html;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Optional;

public class HtmlManager {

    public static String printSingleInt(ResultSet rs) {
        try {
            if (rs.next()) {
                return rs.getInt(1) + "\n";
            } else {
                return "";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String printAllEntries(ResultSet rs) {
        StringBuilder sb = new StringBuilder();

        try {
            while (rs.next()) {
                String name = rs.getString("name");
                int price = rs.getInt("price");
                sb.append(name).append("\t").append(price).append("</br>\n");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
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
}
