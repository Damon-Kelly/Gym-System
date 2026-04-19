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
    /**
     * Establishes a connection to the SQLite database and enables foreign key constraints.
     * @return Connection object to the gym.db database
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException
    {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:gym.db");
        conn.createStatement().execute("PRAGMA foreign_keys = ON");
        return conn;
    }

    /**
     * Authenticates a member by checking email and password against the database.
     * @param email The member's email address
     * @param password The member's password
     * @return Member object if authentication successful
     * @throws SQLException if authentication fails or database error occurs
     */
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

    /**
     * Authenticates a trainer by checking email and password against the database.
     * @param email The trainer's email address
     * @param password The trainer's password
     * @return Trainer object if authentication successful
     * @throws SQLException if authentication fails or database error occurs
     */
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

    /**
     * Retrieves a booking by its ID for verification purposes.
     * @param bookingID The unique identifier of the booking
     * @return Booking object if found
     * @throws SQLException if booking not found or database error occurs
     */
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

    /**
     * Checks if an email address is already registered in the Member table.
     * @param email The email address to check
     * @return true if email exists, false otherwise
     * @throws SQLException if database error occurs
     */
    public static boolean emailExists(String email) throws SQLException
    {
        Connection conn         = null;
        PreparedStatement stmt  = null;
        ResultSet result        = null;

        try
            {
                conn  = getConnection();
                stmt  = conn.prepareStatement(
                    "SELECT COUNT(*) FROM Member WHERE Email = ? AND DeletedFlag = 0"
                );
                stmt.setString(1, email);
                result = stmt.executeQuery();

                if (result.next())
                    {
                        return result.getInt(1) > 0;
                    }
                return false;
            }
        finally
            {
                if (result != null) try { result.close(); } catch (SQLException e) { e.printStackTrace(); }
                if (stmt  != null) try { stmt.close();  } catch (SQLException e) { e.printStackTrace(); }
                if (conn  != null) try { conn.close();  } catch (SQLException e) { e.printStackTrace(); }
            }
    }

    public static Member registerMember(String name, String email, String phoneNumber, String dateOfBirth, String password) throws SQLException
    {
        Connection conn         = null;
        PreparedStatement stmt  = null;

        try
            {
                conn  = getConnection();
                stmt  = conn.prepareStatement(
                    "INSERT INTO Member (Name, Email, PhoneNumber, dateOfBirth, Password, DeletedFlag) VALUES (?, ?, ?, ?, ?, 0)"
                );
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setString(3, phoneNumber);
                stmt.setString(4, dateOfBirth);
                stmt.setString(5, password);
                stmt.executeUpdate();

                // Retrieve the newly created member
                return authenticateMember(email, password);
            }
        finally
            {
                if (stmt  != null) try { stmt.close();  } catch (SQLException e) { e.printStackTrace(); }
                if (conn  != null) try { conn.close();  } catch (SQLException e) { e.printStackTrace(); }
            }
    }

} // end class