/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware;

/**
 *
 * @author AXIOO
 */
public class authentication {
    
    public boolean isLoggedIn = false;
    
    public void setLogged(boolean loged)
    {
        this.isLoggedIn = loged;
    }
}
