package pointofsale;

import java.sql.*;

public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String username = "root";
        String password = "";
        String database = "worketic";
        String url = "jdbc:mysql://localhost:3306/";
        
        try
        {
            
            Class.forName("com.mysql.jdbc.Driver");
        
            url += database;
            
            Connection conn = DriverManager.getConnection(url, username, password);
            
            PreparedStatement sql = null;
            sql = conn.prepareStatement("select * from users");
            
            ResultSet rs = sql.executeQuery();
            
            while(rs.next())
            {
                System.out.println("1");
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.toString());
        }
        catch(ClassNotFoundException e)
        {
            System.out.print("JDBC Class Not Found");
        }
    } 
}
