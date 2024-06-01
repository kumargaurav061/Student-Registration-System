import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection con;

    public static Connection getConnection(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // Step 2: Create the connection object  
            con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "system", "kumar");
        }
        catch(SQLException sqe){
            System.out.println(sqe);
        }
        catch(ClassNotFoundException cse){
            System.out.println(cse);
        }
        return con;
    }
}
