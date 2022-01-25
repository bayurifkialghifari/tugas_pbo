/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library;

/**
 *
 * @author mac
 */
public class strings {
    
    public String clear_string(String value, int length)
    {
        String final_value = "";
        
        if(value.length() > length)
        {
            final_value = " " + value.substring(0, length - 4) + "..|";
        }
        else
        {
            String space = "";
            int final_length = length - value.length();
            
            for(int i = 0;i < final_length - 2; i++)
            {
                space += " ";
            }
            
            final_value = " " + value + space + "|";
        }
        
        
        return final_value;
    }
    
}
