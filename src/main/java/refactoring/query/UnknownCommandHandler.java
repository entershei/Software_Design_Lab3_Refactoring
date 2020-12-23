package refactoring.query;

import refactoring.database.DataBase;

import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UnknownCommandHandler extends QueryHandler {

    private final String command;

    protected UnknownCommandHandler(DataBase dataBase, String command) {
        super(dataBase);
        this.command = command;
    }

    @Override
    public void execute(HttpServletResponse response) {
        try {
            response.getWriter().println("Unknown command: " + command);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected String convertToString(ResultSet rs) {
        return null;
    }

    @Override
    protected Optional<String> getTitle() {
        return Optional.empty();
    }

    @Override
    protected String getSelect() {
        return null;
    }
}
