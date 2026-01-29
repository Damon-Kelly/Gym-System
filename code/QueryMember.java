import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class QueryMember 
{
    public static void main(String [] args) 
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
                
                ResultSetMetaData metaData = resultSet.getMetaData();
                int numberOfColumns = metaData.getColumnCount();
                System.out.println( "Members Table :\n" );
                for (int i = 1; i <= numberOfColumns; i++)
                    {
                        System.out.print (metaData.getColumnName( i ) + "\t" );
                    }
                System.out.println ();
                while (resultSet.next())
                    {
                        for ( int j = 1; j <= numberOfColumns; j++ )
                            {
                                System.out. print(resultSet.getObject( j ) + "\t\t");
                            }
                        System.out. println ( );
                    }
                System.out.println();
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