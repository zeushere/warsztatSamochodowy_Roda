package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
    protected Connection connection;
    public Connection getConnection(){
        final String connectionString="jdbc:mysql://localhost:3306/warsztat_samochodowy";
        try {
            connection = DriverManager.getConnection(connectionString,"root", "");
            return connection;

        } catch (SQLException ex) {
        }
        return null;
    }
}
