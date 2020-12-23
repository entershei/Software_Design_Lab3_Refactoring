package refactoring.database;

import java.sql.*;

public class DataBase {
    private final String dataBaseName;

    public DataBase(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public void executeSql(String sql) throws SQLException {
        Connection c = DriverManager.getConnection(dataBaseName);
        Statement stmt = c.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    public ToClose executeQuery(String sql) throws SQLException {
        Connection c = DriverManager.getConnection(dataBaseName);
        Statement stmt = c.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);
        return new ToClose(stmt, resultSet);
    }

    public static class ToClose {
        private final Statement statement;
        private final ResultSet resultSet;

        ToClose(Statement statement, ResultSet resultSet) {
            this.statement = statement;
            this.resultSet = resultSet;
        }

        public ResultSet getResultSet() {
            return resultSet;
        }

        public void close() throws SQLException {
            statement.close();
            resultSet.close();
        }
    }
}
