package refactoring.query;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public interface Handler {

    void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException;
}
