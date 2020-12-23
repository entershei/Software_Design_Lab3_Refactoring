package refactoring.query;

import refactoring.database.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static refactoring.html.HtmlManager.printSingleInt;

public class CountHandler extends QueryHandler {

    protected CountHandler(Database database) {
        super(database);
    }

    @Override
    protected String convertToString(ResultSet resultSet) throws SQLException {
        return printSingleInt(resultSet);
    }

    @Override
    protected Optional<String> getTitle() {
        return Optional.of("Number of products: ");
    }

    @Override
    protected String getSelect() {
        return "SELECT COUNT(*) FROM PRODUCT";
    }
}
