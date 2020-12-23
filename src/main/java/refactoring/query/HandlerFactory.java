package refactoring.query;

import refactoring.database.Database;

import javax.servlet.http.HttpServletRequest;

public class HandlerFactory {

    public static Handler getHandler(HttpServletRequest request, Database database) {
        String command = request.getParameter("command");

        if ("max".equals(command)) {
            return new MaxHandler(database);
        } else if ("min".equals(command)) {
            return new MinHandler(database);
        } else if ("sum".equals(command)) {
            return new SumHandler(database);
        } else if ("count".equals(command)) {
            return new CountHandler(database);
        } else {
            return new UnknownCommandHandler();
        }
    }
}
