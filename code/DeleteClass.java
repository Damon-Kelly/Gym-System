// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :			    05/03/2026
// Purpose : 		    Deletes a class from the Class table.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteClass
{
    public static void deleteClass(int classID) 
    {
        Connection connection = null;
        PreparedStatement pstat = null;
        int i =0;
        try 
            {
                // establish connection to database
                connection = Database.getConnection();
                // create Prepared Statement for inserting data into table
                pstat = connection.prepareStatement("DELETE FROM Class WHERE ClassID = ?");
                pstat . setInt (1, classID);
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