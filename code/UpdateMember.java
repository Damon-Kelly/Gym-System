// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :			    26/02/2026
// Purpose : 		    Updates a member's email address in the database.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateMember 
{
    public static void updateMember(int memberID, String name, String email, String phoneNumber, String dateOfBirth, String password) 
    {
        Connection connection = null;
        PreparedStatement pstat = null;
        int i =0;
        try 
            {
                // establish connection to database
                connection = Database.getConnection();
                // create Prepared Statement for inserting data into table
                pstat = connection.prepareStatement("UPDATE Member SET Name = ?, Email = ?, PhoneNumber = ?, dateOfBirth = ?, Password = ? WHERE MemberID = ?");
                pstat . setString (1, name);
                pstat . setString (2, email);
                pstat . setString (3, phoneNumber);
                pstat . setString (4, dateOfBirth);
                pstat . setString (5, password);
                pstat . setInt (6, memberID);
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