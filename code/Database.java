// Student Name :      Damon Kelly
// Student Id Number : C00307057
// Date :              12/04/2026
// Purpose :           Database connection class with proper resource management.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        Connection conn         = null;
        PreparedStatement stmt  = null;
        ResultSet login         = null;

        try
            {
                conn  = getConnection();
                stmt  = conn.prepareStatement(
                    "SELECT * FROM Member WHERE Email = ? AND Password = ? AND DeletedFlag = 0"
                );
                stmt.setString(1, email);
                stmt.setString(2, password);
                login = stmt.executeQuery();

                if (!login.next())
                    {
                        throw new SQLException("Invalid email or password.");
                    }

                return new Member(
                    login.getInt("MemberID"),
                    login.getString("Name"),
                    login.getString("Email"),
                    login.getString("PhoneNumber"),
                    login.getString("dateOfBirth"),
                    login.getString("Password"),
                    login.getInt("DeletedFlag")
                );
            }
        finally
            {
                if (login != null) try { login.close(); } catch (SQLException e) { e.printStackTrace(); }
                if (stmt  != null) try { stmt.close();  } catch (SQLException e) { e.printStackTrace(); }
                if (conn  != null) try { conn.close();  } catch (SQLException e) { e.printStackTrace(); }
            }
    }

    public static Trainer authenticateTrainer(String email, String password) throws SQLException
    {
        Connection conn         = null;
        PreparedStatement stmt  = null;
        ResultSet login         = null;

        try
            {
                conn  = getConnection();
                stmt  = conn.prepareStatement(
                    "SELECT * FROM Trainer WHERE Email = ? AND Password = ? AND DeletedFlag = 0"
                );
                stmt.setString(1, email);
                stmt.setString(2, password);
                login = stmt.executeQuery();

                if (!login.next())
                    {
                        throw new SQLException("Invalid email or password.");
                    }

                return new Trainer(
                    login.getInt("TrainerID"),
                    login.getString("Name"),
                    login.getString("Email"),
                    login.getString("Password"),
                    login.getInt("DeletedFlag")
                );
            }
        finally
            {
                if (login != null) try { login.close(); } catch (SQLException e) { e.printStackTrace(); }
                if (stmt  != null) try { stmt.close();  } catch (SQLException e) { e.printStackTrace(); }
                if (conn  != null) try { conn.close();  } catch (SQLException e) { e.printStackTrace(); }
            }
    }

    public static Booking authenticateBooking(int bookingID) throws SQLException
    {
        Connection conn         = null;
        PreparedStatement stmt  = null;
        ResultSet login         = null;

        try
            {
                conn  = getConnection();
                stmt  = conn.prepareStatement(
                    "SELECT * FROM Booking WHERE BookingID = ? AND DeletedFlag = 0"
                );
                stmt.setInt(1, bookingID);
                login = stmt.executeQuery();

                if (!login.next())
                    {
                        throw new SQLException("Invalid email or password.");
                    }

                return new Booking(
                    login.getInt("BookingID"),
                    login.getString("BookingDate"),
                    login.getString("Status"),
                    login.getInt("MemberID"),
                    login.getInt("ClassID"),
                    login.getInt("DeletedFlag")
                );
            }
        finally
            {
                if (login != null) try { login.close(); } catch (SQLException e) { e.printStackTrace(); }
                if (stmt  != null) try { stmt.close();  } catch (SQLException e) { e.printStackTrace(); }
                if (conn  != null) try { conn.close();  } catch (SQLException e) { e.printStackTrace(); }
            }
    }

} // end class