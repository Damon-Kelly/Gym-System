import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database 
{
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:gym.db");
        conn.createStatement().execute("PRAGMA foreign_keys = ON");
        return conn;
    }
}