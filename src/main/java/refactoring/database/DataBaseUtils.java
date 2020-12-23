package refactoring.database;

import java.sql.SQLException;

public class DataBaseUtils {

    public static void createTableIfNotExist(DataBase dataBase) throws SQLException {
         dataBase.executeSql("CREATE TABLE IF NOT EXISTS PRODUCT" +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " NAME           TEXT    NOT NULL, " +
                " PRICE          INT     NOT NULL)");
    }

    public static void addProductToTable(String name, String price, DataBase dataBase) throws SQLException {
        dataBase.executeSql("INSERT INTO PRODUCT " +
                "(NAME, PRICE) VALUES (\"" + name + "\"," + price + ")");
    }

    public static DataBase.ToClose executeQuery(String sql, DataBase dataBase) throws SQLException {
        return dataBase.executeQuery(sql);
    }
}
