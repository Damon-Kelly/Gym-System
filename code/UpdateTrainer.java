// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :			    26/02/2026
// Purpose : 		    Updates a trainer's details in the database.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTrainer
{
    public static void updateTrainer(int trainerID, String name, String email, String password) 
    {
        Connection connection = null;
        PreparedStatement pstat = null;
        int i =0;
        try 
            {
                // establish connection to database
                connection = Database.getConnection();
                // create Prepared Statement for inserting data into table
                pstat = connection.prepareStatement("UPDATE Trainer SET Name = ?, Email = ?, Password = ? WHERE TrainerID = ?");
                pstat . setString (1, name);
                pstat . setString (2, email);
                pstat . setString (3, password);
                pstat . setInt (4, trainerID);
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