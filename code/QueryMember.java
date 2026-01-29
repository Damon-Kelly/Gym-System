import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class QueryMember 
{
    public static void queryMembers() 
    {
        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet resultSet = null;
        try 
            {
                // establish connection to database
                connection = Database.getConnection();
                // create Prepared Statement for inserting data into table
                pstat = connection.prepareStatement("SELECT MemberID, Name, Email, PhoneNumber, DateOfBirth FROM Member");
                // insert data into table
                resultSet = pstat .executeQuery();
                
                System.out.println( "Members Table :\n" );
                System.out.printf("%-10s %-15s %-25s %-15s %-12s%n",
                "MemberID", "Name", "Email", "PhoneNumber", "DateOfBirth");
                System.out.println("--------------------------------------------------------------------------------");
                
                while (resultSet.next())
                    {
                        System.out.printf("%-10d %-15s %-25s %-15s %-12s%n",
                        resultSet.getInt("MemberID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber"),
                        resultSet.getString("DateOfBirth"));
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