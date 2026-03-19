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

    public static Member authenticateMember(String email, String password) throws SQLException 
    {
        Connection conn = getConnection();
        var stmt = conn.prepareStatement("SELECT * FROM Member WHERE Email = ? AND Password = ?");
        stmt.setString(1, email);
        stmt.setString(2, password);
        var login = stmt.executeQuery();
        if (!login.next()) 
            {
                throw new SQLException("Invalid email or password.");
            }
        return new Member(login.getInt("MemberID"), login.getString("Name"), login.getString("Email"), login.getString("PhoneNumber"), login.getString("dateOfBirth"), login.getString("Password"), login.getInt("DeletedFlag"));

    }

    public static Trainer authenticateTrainer(String email, String password) throws SQLException 
    {
        Connection conn = getConnection();
        var stmt = conn.prepareStatement("SELECT * FROM Trainer WHERE Email = ? AND Password = ?");
        stmt.setString(1, email);
        stmt.setString(2, password);
        var login = stmt.executeQuery();
        if (!login.next()) 
            {
                throw new SQLException("Invalid email or password.");
            }
        return new Trainer(login.getInt("TrainerID"), login.getString("Name"), login.getString("Email"), login.getString("Password"), login.getInt("DeletedFlag"));
    }
}