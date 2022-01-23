package pointofsale;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Scanner;
import javax.sql.rowset.CachedRowSet;
import middleware.authentication;
import library.output;
import model.menu;

public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // Exit
        boolean exit = false;
        
        // Output library
        output out = new output();
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
            out.println(auth.isLoggedIn ? "true" : "false");
            if(!auth.isLoggedIn)
            {
                // Show login and register menu                
                crs = menu.select_where("*", "menu_menu_id", "0", "and menu_class != 'home'");
                
                i = 0;
                
                // Label
                out.println("Silakan pilih salah satu menu yang ada di bawah ini");
                
                while(crs.next())
                {
                    menu_list[i+1] = crs.getString("menu_id");
                    i++;
                    
                    out.println(crs.getString("menu_index") + ". " + crs.getString("menu_name"));
                }
                
                out.println(i+1 + ". Keluar \n");
                
                out.println("Pilihan anda ?");
                
                select_menu = input.nextInt();
                
                // Check exit
                exit = select_menu == (i+1) ? true : false;
                
                // Clear screen
                out.cls();
                
                if(!exit && select_menu < i+1)
                {
                    crs = menu.select_where("*", "menu_id", menu_list[select_menu], "");
                    
                    while(crs.next())
                    {
                        Class classRef = Class.forName("controller." + crs.getString("menu_class"));
                        Object instance = classRef.newInstance();
                        Method method = classRef.getDeclaredMethod(crs.getString("menu_function"));
                        method.invoke(instance);
                    }
                }
                else if(select_menu > i+1)
                    out.println("Masukan yang anda masukan salah !! \n");
                else if(exit)
                    out.print("Aplikasi berhasil di tutup");
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
