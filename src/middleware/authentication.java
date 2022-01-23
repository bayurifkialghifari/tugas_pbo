/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware;


import java.sql.SQLException;
import model.user;
import middleware.password_hash;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author AXIOO
 */
public class authentication {
    
    password_hash hash = new password_hash();
    user user = new user();
    CachedRowSet crs;
    
    public static boolean isLoggedIn = false;
    public String saltvalue = "GGWP";
    
    public void setLogged(boolean loged)
    {
        this.isLoggedIn = loged;
    }
    
    public boolean auth(String username, String password) throws Exception
    {
        crs = user.select_where("*", "user_name", username, "");
        
        int i = 0;
        
        while(crs.next())
        {
            i++;
            
            this.setLogged(hash.verifyUserPassword(password, crs.getString("user_password"), saltvalue));
        }
        
        if(i == 0)
        {
            return false;
        }
        else
        {
            return this.isLoggedIn;
        }
        
    }
    
    public boolean register(String username, String password) throws Exception
    {
        // Check then username
        crs = user.select_where("*", "user_name", username, "");
        
        int i = 0;
        
        while(crs.next())
        {
            i++;
        }
        
        String encryptedpassword = hash.generateSecurePassword(password, saltvalue);
        String [] field = {"user_role_id", "user_name", "user_password"};
        String [] data = {"2", username, encryptedpassword};
        
        // Insert data user        
        if(i == 0)
        {
            user.insert(field, data);   
            
            return true;
        }
        else
        {
            return false;
        }
    }
}
