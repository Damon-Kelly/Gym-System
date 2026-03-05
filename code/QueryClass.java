// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :			    05/03/2026
// Purpose : 		    Query the class table and display the details of all classes in the database.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class QueryClass 
{
    public static void queryClasses() 
    {
        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet resultSet = null;
        try 
            {
                // establish connection to database
                connection = Database.getConnection();
                // create Prepared Statement for inserting data into table
                pstat = connection.prepareStatement("SELECT ClassID, Title, Schedule, Capacity FROM Class");
                // insert data into table
                resultSet = pstat .executeQuery();
                
                System.out.println( "Classes Table :\n" );
                System.out.printf("%-10s %-15s %-25s %-15s%n",
                "ClassID", "Title", "Schedule", "Capacity");
                System.out.println("--------------------------------------------------------------------");
                
                while (resultSet.next())
                    {
                        System.out.printf("%-10d %-15s %-25s %-15s%n",
                        resultSet.getInt("ClassID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Schedule"),
                        resultSet.getString("Capacity"));
                    }
                System.out.println();
            }
            catch(SQLException sqlException)
                {
                    sqlException . printStackTrace();
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