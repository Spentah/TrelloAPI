package api.utils;

import java.sql.*;

public class DatabaseExecutor {
    private final static String USER = "postgres";
    private final static String PASS = "1";
    private final static String URL = "jdbc:postgresql://localhost:5432/trello_db";
    private static Connection connection;

    private DatabaseExecutor() {

    }

    public static void getConnect() {
       try {
           Class.forName("org.postgresql.Driver");
           connection = DriverManager.getConnection(URL, USER, PASS);
       } catch (SQLException e) {
           e.printStackTrace();
       } catch (ClassNotFoundException exception) {
           exception.printStackTrace();
       }
    }

    public static void closeConnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String executeValue(String value) {
        String result = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM trello")) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString(value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
