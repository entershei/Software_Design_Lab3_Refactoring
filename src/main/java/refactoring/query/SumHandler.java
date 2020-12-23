package refactoring.query;

import refactoring.database.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static refactoring.html.HtmlManager.printSingleInt;

public class SumHandler extends QueryHandler {

    public SumHandler(Database database) {
        super(database);
    }

    @Override
    protected String convertToString(ResultSet resultSet) throws SQLException {
        return printSingleInt(resultSet);
    }

    @Override
    protected Optional<String> getTitle() {
        return Optional.of("Summary price: ");
    }

    @Override
    protected String getSelect() {
        return "SELECT SUM(price) FROM PRODUCT";
    }
}
