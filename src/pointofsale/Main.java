package pointofsale;

import java.lang.reflect.*;
import java.util.Arrays;
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
        
        // Menu list now        
        int i;
        int parent_menu;
        int select_menu;
        String menu_list [] = new String [101];       
        
        do
        {
            if(!auth.isLoggedIn)
            {
                // Show login and register menu                
                crs = menu.select_where("*", "menu_menu_id", "0", "and menu_class != 'home'");
                
                i = 0;
                
                // Label
                System.out.println("Silakan pilih salah satu menu yang ada di bawah ini");
                
                while(crs.next())
                {
                    menu_list[i+1] = crs.getString("menu_name");
                    i++;
                    
                    System.out.println(crs.getString("menu_index") + ". " + crs.getString("menu_name"));
                }
                
                System.out.println(i+1 + ". Keluar \n");
                
                System.out.println("Pilihan anda ?");
                
                select_menu = input.nextInt();
                
                // Check exit
                exit = select_menu == (i+1) ? true : false;
                
                if(!exit)
                    System.out.println(menu_list[select_menu]);
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
