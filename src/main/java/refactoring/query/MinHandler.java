package refactoring.query;

import refactoring.database.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static refactoring.html.HtmlManager.printAllEntries;
import static refactoring.html.HtmlManager.wrap;

public class MinHandler extends QueryHandler {

    protected MinHandler(DataBase dataBase) {
        super(dataBase);
    }

    @Override
    protected String convertToString(ResultSet rs) throws SQLException {
        return printAllEntries(rs);
    }

    @Override
    protected Optional<String> getTitle() {
        return Optional.of(wrap("Product with min price: "));
    }

    @Override
    protected String getSelect() {
        return "SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1";
    }
}
