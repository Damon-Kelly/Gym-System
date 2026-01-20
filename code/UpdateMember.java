import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateMember 
{
    public static void main(String [] args) 
    {
        // database URL
        final String DATABASE_URL = "jdbc:sqlite:gym.db";
        Connection connection = null;
        PreparedStatement pstat = null;
        // test data to update
        String name = "Mark";
        String email = "mark@email.com";
        int i =0;
        try 
            {
                // establish connection to database
                connection = DriverManager.getConnection(DATABASE_URL, "root", "password");
                // create Prepared Statement for inserting data into table
                pstat = connection.prepareStatement("UPDATE Members SET Email = ? WHERE Name = ?");
                pstat . setString (1, email);
                pstat . setString (2, name);
                // insert data into table
                i = pstat .executeUpdate();
                System.out. println ( i + " record successfully modified in the table.");
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