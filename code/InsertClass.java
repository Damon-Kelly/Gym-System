// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :			    05/03/2026
// Purpose : 		    Inserts a new class into the Class table.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertClass
{
    public static void insertClass(String title, String schedule, int capacity) 
    {
        Connection connection = null;
        PreparedStatement pstat = null;
        int i =0;
        try 
            {
                // establish connection to database
                connection = Database.getConnection();
                // create Prepared Statement for inserting data into table
                pstat = connection.prepareStatement("INSERT INTO Class (Title, Schedule, Capacity) VALUES (?,?,?)");
                pstat . setString (1, title);
                pstat . setString (2, schedule);
                pstat . setInt (3, capacity);
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