package refactoring.query;

import refactoring.database.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static refactoring.html.HtmlManager.printAllEntries;
import static refactoring.html.HtmlManager.wrap;

public class MaxHandler extends QueryHandler {

    protected MaxHandler(Database database) {
        super(database);
    }

    @Override
    protected String convertToString(ResultSet resultSet) throws SQLException {
        return printAllEntries(resultSet);
    }

    @Override
    protected Optional<String> getTitle() {
        return Optional.of(wrap("Product with max price: "));
    }

    @Override
    protected String getSelect() {
        return "SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1";
    }
}
