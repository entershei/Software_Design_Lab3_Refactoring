package refactoring.query;

import refactoring.database.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static refactoring.html.HtmlManager.printSingleInt;

public class CountHandler extends QueryHandler {

    protected CountHandler(DataBase dataBase) {
        super(dataBase);
    }

    @Override
    protected String convertToString(ResultSet rs) throws SQLException {
        return printSingleInt(rs);
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
