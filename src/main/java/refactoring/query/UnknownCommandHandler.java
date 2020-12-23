package refactoring.query;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnknownCommandHandler implements Handler {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String command = request.getParameter("command");
        try {
            response.getWriter().println("Unknown command: " + command);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
