package refactoring.query;

import refactoring.database.Database;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static refactoring.database.DatabaseUtils.executeQuery;
import static refactoring.html.HtmlManager.printResponse;

public abstract class QueryHandler implements Handler {

    protected final Database database;

    protected QueryHandler(Database database) {
        this.database = database;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Database.QueryResponse queryResponse = executeQuery(getSelect(), database);
        Optional<String> title = getTitle();
        String body = convertToString(queryResponse.getResultSet());
        printResponse(title, body, response.getWriter());
        queryResponse.close();

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    protected abstract String convertToString(ResultSet resultSet) throws SQLException;

    protected abstract Optional<String> getTitle();

    protected abstract String getSelect();
}
