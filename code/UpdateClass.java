// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :			    05/03/2026
// Purpose : 		    Updates a class in the database.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateClass
{
    public static void updateClass(int classID, String title, String schedule, int capacity) 
    {
        Connection connection = null;
        PreparedStatement pstat = null;
        int i =0;
        try 
            {
                // establish connection to database
                connection = Database.getConnection();
                // create Prepared Statement for inserting data into table
                pstat = connection.prepareStatement("UPDATE Class SET Title = ?, Schedule=?, Capacity=? WHERE ClassID = ?");
                pstat . setString (1, title);
                pstat . setString (2, schedule);
                pstat.setInt(3, capacity);
                pstat.setInt(4, classID);
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