package refactoring.database;

import java.sql.SQLException;

public class DatabaseUtils {

    public static void createTableIfNotExist(Database database) throws SQLException {
        database.executeSql("CREATE TABLE IF NOT EXISTS PRODUCT" +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " NAME           TEXT    NOT NULL, " +
                " PRICE          INT     NOT NULL)");
    }

    public static void addProductToTable(String name, String price, Database database) throws SQLException {
        database.executeSql("INSERT INTO PRODUCT " +
                "(NAME, PRICE) VALUES (\"" + name + "\"," + price + ")");
    }

    public static Database.QueryResponse executeQuery(String sql, Database database) throws SQLException {
        return database.executeQuery(sql);
    }
}
