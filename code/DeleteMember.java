import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteMember 
{
    public static void main(String [] args) 
    {
        // database URL
        final String DATABASE_URL = "jdbc:sqlite:gym.db";
        Connection connection = null;
        PreparedStatement pstat = null;
        // test data to insert
        int memberID = 1;
        int i =0;
        try 
            {
                // establish connection to database
                connection = DriverManager.getConnection(DATABASE_URL, "root", "password");
                // create Prepared Statement for inserting data into table
                pstat = connection.prepareStatement("DELETE FROM Members WHERE MemberID = ?");
                pstat . setInt (1, memberID);
                // insert data into table
                i = pstat .executeUpdate();
                System.out. println ( i + " record successfully deleted from the table.");
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