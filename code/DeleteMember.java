import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteMember 
{
    public static void deleteMember(int memberID) 
    {
        Connection connection = null;
        PreparedStatement pstat = null;
        int i =0;
        try 
            {
                // establish connection to database
                connection = Database.getConnection();
                // create Prepared Statement for inserting data into table
                pstat = connection.prepareStatement("DELETE FROM Member WHERE MemberID = ?");
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