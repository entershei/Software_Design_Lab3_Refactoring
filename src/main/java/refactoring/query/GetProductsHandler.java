package refactoring.query;

import refactoring.database.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static refactoring.html.HtmlManager.printAllEntries;

public class GetProductsHandler extends QueryHandler {

    public GetProductsHandler(DataBase dataBase) {
        super(dataBase);
    }

    @Override
    protected String convertToString(ResultSet rs) throws SQLException {
        return printAllEntries(rs);
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
