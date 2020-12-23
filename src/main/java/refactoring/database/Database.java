package refactoring.database;

import java.sql.*;

public class Database {

    private final String databaseName;

    public Database(String databaseName) {
        this.databaseName = databaseName;
    }

    public void executeSql(String sql) throws SQLException {
        Connection c = DriverManager.getConnection(databaseName);
        Statement statement = c.createStatement();
        statement.executeUpdate(sql);
        statement.close();
    }

    public QueryResponse executeQuery(String sql) throws SQLException {
        Connection c = DriverManager.getConnection(databaseName);
        Statement statement = c.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        return new QueryResponse(statement, resultSet);
    }

    public static class QueryResponse {
        private final Statement statement;
        private final ResultSet resultSet;

        QueryResponse(Statement statement, ResultSet resultSet) {
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
