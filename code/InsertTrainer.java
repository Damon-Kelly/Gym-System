// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :			    26/02/2026
// Purpose : 		    Inserts a new trainer into the Trainer table.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTrainer 
{
    public static void insertTrainer(String name, String email, String password) 
    {
        Connection connection = null;
        PreparedStatement pstat = null;
        int i =0;
        try 
            {
                // establish connection to database
                connection = Database.getConnection();
                // create Prepared Statement for inserting data into table
                pstat = connection.prepareStatement("INSERT INTO Trainer (Name, Email, Password) VALUES (?,?,?)");
                pstat . setString (1, name);
                pstat . setString (2, email);
                pstat . setString (3, password);
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