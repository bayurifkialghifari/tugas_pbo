package pointofsale;

import core.Connection;
import model.test;
import java.sql.SQLException;

public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        test tst = new test();
        String [] field = {"nama", "umur", "alamat"};
        String [] value = {"Ujang", "15", "Sekolah"};
        //tst.insert(field, value);
        //tst.update(field, value, "id", "1");
        tst.delete("id", "1");
        
//        Connection cc = new Connection();
//        
//        cc.connect();
//        
//        try
//        {
//            cc.ps = cc.conn.prepareStatement("select * from users");
//            cc.rs = cc.ps.executeQuery();
//            
//            while(cc.rs.next())
//            {
//                System.out.println("1");
//            }
//        }
//        catch(SQLException e)
//        {
//            System.out.print(e.toString());
//        }
    } 
}
