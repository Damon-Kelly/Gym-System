
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseTest {
    public static void main(String[] args) {
        try 
            {
                Connection conn =
                    DriverManager.getConnection("jdbc:sqlite:gym.db");

                System.out.println("SQLite connected successfully");
                conn.close();
            } catch (Exception e) 
                {
                    e.printStackTrace();
                }
    }
}