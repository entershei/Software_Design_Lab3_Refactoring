package refactoring.query;

import refactoring.database.DataBase;

import javax.servlet.http.HttpServletRequest;

public class HandlerFactory {

    public static Handler getHandler(HttpServletRequest request, DataBase dataBase) {
        String command = request.getParameter("command");

        if ("max".equals(command)) {
            return new MaxHandler(dataBase);
        } else if ("min".equals(command)) {
            return new MinHandler(dataBase);
        } if ("sum".equals(command)) {
            return new SumHandler(dataBase);
        } if ("count".equals(command)) {
            return new CountHandler(dataBase);
        } else {
            return new UnknownCommandHandler();
        }
    }
}
