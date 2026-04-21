// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :			    05/03/2026
// Purpose : 		    Inserts a new class into the Class table.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertClass
{
    public static void insertClass(String title, String schedule, int capacity, int trainerID)
    {
        String sql = "INSERT INTO Class (Title, Schedule, Capacity, TrainerID, DeletedFlag) " +
                     "VALUES (?, ?, ?, ?, 0)";

        try (Connection connection = Database.getConnection();
             PreparedStatement pstat = connection.prepareStatement(sql)) {

            pstat.setString(1, title);
            pstat.setString(2, schedule);
            pstat.setInt(3, capacity);
            pstat.setInt(4, trainerID);

            int rows = pstat.executeUpdate();
            System.out.println(rows + " class(es) successfully added.");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
