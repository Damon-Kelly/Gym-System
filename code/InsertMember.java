import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertMember 
{
    public static void main(String [] args) 
    {
        // database URL
        final String DATABASE_URL = "jdbc:sqlite:gym.db";
        Connection connection = null;
        PreparedStatement pstat = null;
        // test data to insert
        String name = "Mark";
        String email = "mark@example.com";
        String phoneNumber = "1234567890";
        String dateOfBirth = "1990-01-01";
        int i =0;
        try 
            {
                // establish connection to database
                connection = DriverManager.getConnection(DATABASE_URL, "root", "password");
                // create Prepared Statement for inserting data into table
                pstat = connection.prepareStatement("INSERT INTO Members (Name, Email, PhoneNumber, dateOfBirth) VALUES (?,?,?,?)");
                pstat . setString (1, name);
                pstat . setString (2, email);
                pstat . setString (3, phoneNumber);
                pstat . setString (4, dateOfBirth);
                // insert data into table
                i = pstat .executeUpdate();
                System.out. println ( i + " record successfully added to the table .");
            }
            catch(SQLException sqlException)
                {
                sqlException . printStackTrace ();
                }
            finally 
                {
                try 
                    {
                        pstat . close ();
                        connection. close ();
                    }
                catch (Exception exception)
                    {
                        exception . printStackTrace ();
                    }
                }
    } // end main
} // end class