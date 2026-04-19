// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :			    05/03/2026
// Purpose : 		    Inserts a new booking into the Booking table.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertBooking
{
    public static void insertBooking(String bookingDate, String status, int memberID, int classID)
    {
        Connection connection = null;
        PreparedStatement pstat = null;
        int i =0;
        try 
            {
                // establish connection to database
                connection = Database.getConnection();
                // create Prepared Statement for inserting data into table
                pstat = connection.prepareStatement("INSERT INTO Booking (BookingDate, Status, MemberID, ClassID) VALUES (?,?,?,?)");
                pstat . setString (1, bookingDate);
                pstat . setString (2, status);
                pstat . setInt (3, memberID);
                pstat . setInt (4, classID);
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
    } // end insertBooking
} // end class
