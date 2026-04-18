// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :			    26/02/2026
// Purpose : 		    Inserts a new membership into the Membership table.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertMembership 
{
    public static void insertMembership(String planType, String startDate, String endDate, int isActive, int memberID) 
    {
        Connection connection = null;
        PreparedStatement pstat = null;
        int i =0;
        try 
            {
                // establish connection to database
                connection = Database.getConnection();
                // create Prepared Statement for inserting data into table
                pstat = connection.prepareStatement("INSERT INTO Membership (PlanType, StartDate, EndDate, IsActive, MemberID) VALUES (?,?,?,?,?)");
                pstat . setString (1, planType);
                pstat . setString (2, startDate);
                pstat . setString (3, endDate);
                pstat . setInt (4, isActive);
                pstat . setInt (5, memberID);
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