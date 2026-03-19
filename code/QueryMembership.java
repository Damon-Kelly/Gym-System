// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :			    26/02/2026
// Purpose : 		    Checks if members have an active membership and displays their details if they do.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class QueryMembership 
{
    public static void queryMembership() 
    {
        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet resultSet = null;
        try 
            {
                // establish connection to database
                connection = Database.getConnection();
                // create Prepared Statement for inserting data into table
                pstat = connection.prepareStatement("SELECT Member.Name, Member.Email, Membership.PlanType, Membership.IsActive FROM Member INNER JOIN Membership ON Member.MemberID = Membership.MemberID WHERE Membership.IsActive = true AND Member.DeletedFlag != 1");
                    
                // insert data into table
                resultSet = pstat .executeQuery();
                
                System.out.println( "Active Memberships :\n" );
                System.out.printf("%-15s %-25s %-15s %-10s%n",
                "Name", "Email", "PlanType", "IsActive");
                System.out.println("---------------------------------------------------------------");
                
                while (resultSet.next())
                    {
                        System.out.printf("%-15s %-25s %-15s %-10b%n",
                        resultSet.getString("Name"),
                        resultSet.getString("Email"),
                        resultSet.getString("PlanType"),
                        resultSet.getBoolean("IsActive"));
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