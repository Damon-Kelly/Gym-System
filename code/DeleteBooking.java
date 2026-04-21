// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :			    05/03/2026
// Purpose : 		    Deletes a booking from the Booking table.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteBooking
{
    public static void deleteBooking(int bookingID)
    {
        String sql = "UPDATE Booking SET DeletedFlag = 1, Status = 'Deleted' WHERE BookingID = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement pstat = connection.prepareStatement(sql)) {

            pstat.setInt(1, bookingID);
            int updated = pstat.executeUpdate();
            System.out.println(updated + " booking(s) marked deleted.");

        } 
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}