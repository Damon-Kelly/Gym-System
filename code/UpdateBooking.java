// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :			    05/03/2026
// Purpose : 		    Updates a booking in the database.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateBooking
{
    public static void updateBooking(int bookingID, String bookingDate, String status) 
    {
        Connection connection = null;
        PreparedStatement pstat = null;
        int i =0;
        try 
            {
                // establish connection to database
                connection = Database.getConnection();
                // create Prepared Statement for inserting data into table
                pstat = connection.prepareStatement("UPDATE Booking SET bookingDate = ?, status=? WHERE BookingID = ?");
                pstat . setString (1, bookingDate);
                pstat . setString (2, status);
                pstat.setInt(3, bookingID);
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