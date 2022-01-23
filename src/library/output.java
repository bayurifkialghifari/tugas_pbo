/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.io.IOException;

/**
 *
 * @author AXIOO
 */
public class output {
    
    public void cls() throws Exception 
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
        
        
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();  
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
