import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertMember 
{
    public static void insertTest() 
    {
        Connection connection = null;
        PreparedStatement pstat = null;
        // test data to insert
        String name = "Damon";
        String email = "damon@gmail.com";
        String phoneNumber = "78910";
        String dateOfBirth = "2005-08-08";
        int i =0;
        try 
            {
                // establish connection to database
                connection = Database.getConnection();
                // create Prepared Statement for inserting data into table
                pstat = connection.prepareStatement("INSERT INTO Member (Name, Email, PhoneNumber, dateOfBirth) VALUES (?,?,?,?)");
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