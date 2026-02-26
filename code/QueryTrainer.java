// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :			    26/02/2026
// Purpose : 		    Query the trainer table and display the details of all trainers in the database.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class QueryTrainer
{
    public static void queryTrainers() 
    {
        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet resultSet = null;
        try 
            {
                // establish connection to database
                connection = Database.getConnection();
                // create Prepared Statement for inserting data into table
                pstat = connection.prepareStatement("SELECT TrainerID, Name, Email FROM Trainer");
                // insert data into table
                resultSet = pstat .executeQuery();
                
                System.out.println( "Trainers Table :\n" );
                System.out.printf("%-10s %-15s %-25s%n",
                "TrainerID", "Name", "Email");
                System.out.println("------------------------------------------------------------");
                
                while (resultSet.next())
                    {
                        System.out.printf("%-10d %-15s %-25s%n",
                        resultSet.getInt("TrainerID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Email"));
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