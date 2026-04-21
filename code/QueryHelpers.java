// Student Name :      Damon Kelly
// Student Id Number : C00307057
// Date :              18/04/2026
// Purpose :           Helper methods that return GUI-friendly arrays from database queries.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryHelpers
{
    // Returns the active membership for a member.
    public static String[] getMembershipByMemberID(int memberID)
    {
        Connection connection       = null;
        PreparedStatement pstat     = null;
        ResultSet resultSet         = null;
        String[] membership         = null;

        try
        {
            connection = Database.getConnection();
            pstat = connection.prepareStatement(
                "SELECT PlanType, StartDate, EndDate, IsActive " +
                "FROM Membership " +
                "WHERE MemberID = ? AND IsActive = 1"
            );
            pstat.setInt(1, memberID);
            resultSet = pstat.executeQuery();

            if (resultSet.next())
            {
                membership = new String[]
                {
                    resultSet.getString("PlanType"),
                    resultSet.getString("StartDate"),
                    resultSet.getString("EndDate"),
                    String.valueOf(resultSet.getInt("IsActive"))
                };
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            try
            {
                if (resultSet  != null) resultSet.close();
                if (pstat      != null) pstat.close();
                if (connection != null) connection.close();
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
        }

        return membership;
    }

    // Returns all gym classes as a simple array including remaining seats.
    public static String[][] getAllClasses()
    {
        Connection connection       = null;
        PreparedStatement pstat     = null;
        ResultSet resultSet         = null;
        String[][] temp             = new String[50][];
        int count                   = 0;

        try
        {
            connection = Database.getConnection();
            pstat = connection.prepareStatement(
                "SELECT c.ClassID, c.Title, c.Schedule, c.Capacity, " +
                "COUNT(b.BookingID) AS BookedCount " +
                "FROM Class c " +
                "LEFT JOIN Booking b ON c.ClassID = b.ClassID AND b.DeletedFlag != 1 AND b.Status = 'Confirmed' " +
                "WHERE c.DeletedFlag != 1 " +
                "GROUP BY c.ClassID, c.Title, c.Schedule, c.Capacity"
            );
            resultSet = pstat.executeQuery();

            while (resultSet.next())
            {
                int classID = resultSet.getInt("ClassID");
                String title = resultSet.getString("Title");
                String schedule = resultSet.getString("Schedule");
                int capacity = resultSet.getInt("Capacity");
                int booked = resultSet.getInt("BookedCount");
                int remaining = Math.max(0, capacity - booked);
                String remainingText = remaining == 0 ? "Full" : String.valueOf(remaining);

                String[] row = new String[]
                {
                    String.valueOf(classID),
                    title,
                    schedule,
                    String.valueOf(capacity),
                    remainingText
                };

                if (count == temp.length)
                {
                    String[][] expanded = new String[temp.length * 2][];
                    for (int i = 0; i < temp.length; i++)
                    {
                        expanded[i] = temp[i];
                    }
                    temp = expanded;
                }

                temp[count++] = row;
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            try
            {
                if (resultSet  != null) resultSet.close();
                if (pstat      != null) pstat.close();
                if (connection != null) connection.close();
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
        }

        String[][] classes = new String[count][];
        for (int i = 0; i < count; i++)
        {
            classes[i] = temp[i];
        }

        return classes;
    }

    // Returns confirmed booking count for a class.
    public static int getConfirmedBookingCount(int classID)
    {
        Connection connection       = null;
        PreparedStatement pstat     = null;
        ResultSet resultSet         = null;
        int count                   = 0;

        try
        {
            connection = Database.getConnection();
            pstat = connection.prepareStatement(
                "SELECT COUNT(*) AS count FROM Booking " +
                "WHERE ClassID = ? AND DeletedFlag != 1 AND Status = 'Confirmed'"
            );
            pstat.setInt(1, classID);
            resultSet = pstat.executeQuery();

            if (resultSet.next())
            {
                count = resultSet.getInt("count");
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            try
            {
                if (resultSet  != null) resultSet.close();
                if (pstat      != null) pstat.close();
                if (connection != null) connection.close();
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
        }

        return count;
    }

    // Returns all bookings joined with member and class data.
    public static String[][] getAllBookings()
    {
        Connection connection       = null;
        PreparedStatement pstat     = null;
        ResultSet resultSet         = null;
        String[][] temp             = new String[50][];
        int count                   = 0;

        try
        {
            connection = Database.getConnection();
            pstat = connection.prepareStatement(
                "SELECT Member.Name, Class.Title, Class.Schedule, " +
                "Booking.BookingDate, Booking.Status " +
                "FROM Booking " +
                "INNER JOIN Member ON Booking.MemberID = Member.MemberID " +
                "INNER JOIN Class  ON Booking.ClassID  = Class.ClassID " +
                "WHERE Booking.DeletedFlag != 1 " +
                "ORDER BY Booking.BookingDate DESC"
            );
            resultSet = pstat.executeQuery();

            while (resultSet.next())
            {
                String[] row = new String[]
                {
                    resultSet.getString("Name"),
                    resultSet.getString("Title"),
                    resultSet.getString("Schedule"),
                    resultSet.getString("BookingDate"),
                    resultSet.getString("Status")
                };

                if (count == temp.length)
                {
                    String[][] expanded = new String[temp.length * 2][];
                    for (int i = 0; i < temp.length; i++)
                    {
                        expanded[i] = temp[i];
                    }
                    temp = expanded;
                }

                temp[count++] = row;
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            try
            {
                if (resultSet  != null) resultSet.close();
                if (pstat      != null) pstat.close();
                if (connection != null) connection.close();
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
        }

        String[][] bookings = new String[count][];
        for (int i = 0; i < count; i++)
        {
            bookings[i] = temp[i];
        }

        return bookings;
    }

    // Returns bookings for a specific member.
    public static String[][] getBookingsByMemberID(int memberID)
    {
        Connection connection       = null;
        PreparedStatement pstat     = null;
        ResultSet resultSet         = null;
        String[][] temp             = new String[50][];
        int count                   = 0;

        try
        {
            connection = Database.getConnection();
            pstat = connection.prepareStatement(
                "SELECT Booking.BookingID, Class.Title, Class.Schedule, " +
                "Booking.BookingDate, Booking.Status " +
                "FROM Booking " +
                "INNER JOIN Class ON Booking.ClassID = Class.ClassID " +
                "WHERE Booking.MemberID = ? AND Booking.DeletedFlag != 1 " +
                "ORDER BY Booking.BookingDate DESC"
            );
            pstat.setInt(1, memberID);
            resultSet = pstat.executeQuery();

            while (resultSet.next())
            {
                String[] row = new String[]
                {
                    String.valueOf(resultSet.getInt("BookingID")),
                    resultSet.getString("Title"),
                    resultSet.getString("Schedule"),
                    resultSet.getString("BookingDate"),
                    resultSet.getString("Status")
                };

                if (count == temp.length)
                {
                    String[][] expanded = new String[temp.length * 2][];
                    for (int i = 0; i < temp.length; i++)
                    {
                        expanded[i] = temp[i];
                    }
                    temp = expanded;
                }

                temp[count++] = row;
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            try
            {
                if (resultSet  != null) resultSet.close();
                if (pstat      != null) pstat.close();
                if (connection != null) connection.close();
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
        }

        String[][] bookings = new String[count][];
        for (int i = 0; i < count; i++)
        {
            bookings[i] = temp[i];
        }

        return bookings;
    }

    // Returns trainer classes with confirmed booking counts.
    public static String[][] getTrainerClasses(int trainerID)
    {
        Connection connection       = null;
        PreparedStatement pstat     = null;
        ResultSet resultSet         = null;
        String[][] temp             = new String[50][];
        int count                   = 0;

        try
        {
            connection = Database.getConnection();
            pstat = connection.prepareStatement(
                "SELECT c.ClassID, c.Title, c.Schedule, COUNT(b.BookingID) as ConfirmedCount " +
                "FROM Class c " +
                "LEFT JOIN Booking b ON c.ClassID = b.ClassID AND b.Status = 'Confirmed' AND b.DeletedFlag != 1 " +
                "WHERE c.TrainerID = ? AND c.DeletedFlag != 1 " +
                "GROUP BY c.ClassID, c.Title, c.Schedule, c.TrainerID"
            );
            pstat.setInt(1, trainerID);
            resultSet = pstat.executeQuery();

            while (resultSet.next())
            {
                String[] row = new String[]
                {
                    String.valueOf(resultSet.getInt("ClassID")),
                    resultSet.getString("Title"),
                    resultSet.getString("Schedule"),
                    String.valueOf(resultSet.getInt("ConfirmedCount"))
                };

                if (count == temp.length)
                {
                    String[][] expanded = new String[temp.length * 2][];
                    for (int i = 0; i < temp.length; i++)
                    {
                        expanded[i] = temp[i];
                    }
                    temp = expanded;
                }

                temp[count++] = row;
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            try
            {
                if (resultSet  != null) resultSet.close();
                if (pstat      != null) pstat.close();
                if (connection != null) connection.close();
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
        }

        String[][] classes = new String[count][];
        for (int i = 0; i < count; i++)
        {
            classes[i] = temp[i];
        }

        return classes;
    }

    // Checks if a member already has a booking for a class.
    public static boolean hasBookingForClass(int memberID, int classID)
    {
        Connection connection       = null;
        PreparedStatement pstat     = null;
        ResultSet resultSet         = null;
        boolean hasBooking          = false;

        try
        {
            connection = Database.getConnection();
            pstat = connection.prepareStatement(
                "SELECT COUNT(*) as count FROM Booking " +
                "WHERE MemberID = ? AND ClassID = ? AND DeletedFlag != 1"
            );
            pstat.setInt(1, memberID);
            pstat.setInt(2, classID);
            resultSet = pstat.executeQuery();

            if (resultSet.next())
            {
                hasBooking = resultSet.getInt("count") > 0;
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            try
            {
                if (resultSet  != null) resultSet.close();
                if (pstat      != null) pstat.close();
                if (connection != null) connection.close();
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
        }

        return hasBooking;
    }
}
