package refactoring.query;

import refactoring.database.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static refactoring.html.HtmlManager.printAllEntries;

public class GetProductsHandler extends QueryHandler {

    public GetProductsHandler(Database database) {
        super(database);
    }

    @Override
    protected String convertToString(ResultSet resultSet) throws SQLException {
        return printAllEntries(resultSet);
    }

    @Override
    protected Optional<String> getTitle() {
        return Optional.empty();
    }

    @Override
    protected String getSelect() {
        return "SELECT * FROM PRODUCT";
    }
}
