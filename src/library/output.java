/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

/**
 *
 * @author AXIOO
 */
public class output {
    
    public void cls()
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
    
    public void println(String message)
    {
        System.out.println(message);
    }
    
    public void print(String message)
    {
        System.out.print(message);
    }
}
