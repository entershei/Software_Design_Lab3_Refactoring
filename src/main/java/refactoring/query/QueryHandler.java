package refactoring.query;

import refactoring.database.DataBase;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static refactoring.database.DataBaseUtils.executeQuery;
import static refactoring.html.HtmlManager.printResponse;

public abstract class QueryHandler {

    protected final DataBase dataBase;

    protected QueryHandler(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void execute(HttpServletResponse response) throws SQLException, IOException {
        DataBase.ToClose rs = executeQuery(getSelect(), dataBase);
        Optional<String> title = getTitle();
        String body = convertToString(rs.getResultSet());
        printResponse(title, body, response.getWriter());
        rs.close();

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    protected abstract String convertToString(ResultSet rs) throws SQLException;

    protected abstract Optional<String> getTitle();

    protected abstract String getSelect();
}
