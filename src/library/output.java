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
        
        try
        {
            String operatingSystem = System.getProperty("os.name");
              
            if(operatingSystem.contains("Windows"))
            {        
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } 
            else 
            {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            } 
        }catch(Exception e){
            System.out.println(e);
        }  
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
