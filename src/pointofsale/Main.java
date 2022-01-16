package pointofsale;

import java.lang.reflect.*;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;
import middleware.authentication;
import model.menu;

public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
    
        // Authentication middleware
        authentication auth = new authentication();
        
        // Menu show
        menu menu = new menu();
        
        CachedRowSet cst = menu.select_where("*", "menu_menu_id", "0");
        
        while(cst.next())
        {
            System.out.println(cst.getString("menu_name"));
        }
        
        // Class and method dynamic call
//        Class classRef = Class.forName("controller.menu");
//        Object instance = classRef.newInstance();
//        Method method = classRef.getDeclaredMethod("test");
//        method.invoke(instance);
        

    } 
}
