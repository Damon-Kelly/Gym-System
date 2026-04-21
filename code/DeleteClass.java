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
        String sql = "UPDATE Class SET DeletedFlag = 1 WHERE ClassID = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement pstat = connection.prepareStatement(sql)) {

            pstat.setInt(1, classID);
            int updated = pstat.executeUpdate();
            System.out.println(updated + " class(es) marked deleted.");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}

