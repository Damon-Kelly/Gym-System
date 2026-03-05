// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :			    05/03/2026
// Purpose : 		    Query the booking table and display the details of all bookings in the database.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class QueryBooking 
{
    public static void queryBookings() 
    {
        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet resultSet = null;
        try 
            {
                // establish connection to database
                connection = Database.getConnection();
                // create Prepared Statement for inserting data into table
                pstat = connection.prepareStatement("SELECT BookingID, BookingDate, Status FROM Booking");
                // insert data into table
                resultSet = pstat .executeQuery();
                
                System.out.println( "Bookings Table :\n" );
                System.out.printf("%-10s %-15s %-25s %n",
                "BookingID", "BookingDate", "Status");
                System.out.println("---------------------------------------------");
                
                while (resultSet.next())
                    {
                        System.out.printf("%-10d %-15s %-25s %n",
                        resultSet.getInt("BookingID"),
                        resultSet.getString("BookingDate"),
                        resultSet.getString("Status"));
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