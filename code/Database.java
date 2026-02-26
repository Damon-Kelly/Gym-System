// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :			    26/02/2026
// Purpose : 		    Database connection class

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database 
{
    public static Connection getConnection() throws SQLException 
    {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:gym.db");
        conn.createStatement().execute("PRAGMA foreign_keys = ON");
        return conn;
    }
}