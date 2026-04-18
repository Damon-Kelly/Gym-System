// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :			    26/02/2026
// Purpose : 		    Updates a member's email address in the database.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateMembership 
{
    public static void updateMembership(int memberID, String planType, String startDate, String endDate, int isActive) 
    {
        Connection connection = null;
        PreparedStatement pstat = null;
        int i =0;
        try 
            {
                // establish connection to database
                connection = Database.getConnection();
                // create Prepared Statement for inserting data into table
                pstat = connection.prepareStatement("UPDATE Membership SET PlanType = ?, StartDate = ?, EndDate = ?, IsActive = ? WHERE MemberID = ?");
                pstat . setString (1, planType);
                pstat . setString (2, startDate);
                pstat . setString (3, endDate);
                pstat . setInt (4, isActive);
                pstat . setInt (5, memberID);
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