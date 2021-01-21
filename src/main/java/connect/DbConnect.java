package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
    protected Connection connection;
    public Connection getConnection(){
        final String connectionString="jdbc:mysql://localhost:3307/wypozyczalnia_rowerow";
        try {
            connection = DriverManager.getConnection(connectionString,"root", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
