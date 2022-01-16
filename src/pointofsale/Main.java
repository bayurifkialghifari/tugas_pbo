package pointofsale;

import java.lang.reflect.*;
import java.util.Scanner;
import javax.sql.rowset.CachedRowSet;
import middleware.authentication;
import model.menu;

public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // Exit
        boolean exit = false;
        
        // Authentication middleware
        authentication auth = new authentication();
        // Menu show
        menu menu = new menu();
        // Scanner
        Scanner input = new Scanner(System.in);
        // CachedRowSet
        CachedRowSet crs;
        do
        {
            if(!auth.isLoggedIn)
            {
                // Show login and register menu                
                crs = menu.select_where("*", "menu_menu_id", "0", "");
                
                while(crs.next())
                {
                    if(!crs.getString("menu_class").equals("home"))
                        System.out.println(crs.getString("menu_index") + " " + crs.getString("menu_name"));
                }
                input.nextInt();
            }
        }
        while(!exit);
                
        // Class and method dynamic call
//        Class classRef = Class.forName("controller.menu");
//        Object instance = classRef.newInstance();
//        Method method = classRef.getDeclaredMethod("test");
//        method.invoke(instance);
        
    } 
}
