/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.sql.SQLException;
/**
 *
 * @author AXIOO
 */
public class ORM extends Connection {
    
    private String sql_builder = "";
    private int i;
    
    protected String get_table()
    {
        String child = this.getClass().getName();
        String[] childs = sql_builder.split("\\.", 0);
        
        return childs[1];
    }
    
    protected String insert(String[] field, String[] data)
    {
        this.connect();
        
        try
        {            
            this.sql_builder = "insert into "+this.get_table();
            
            // Set field name           
            for(this.i = 0;this.i < field.length;this.i++)
            {
                if(this.i == 0)
                    this.sql_builder += " ("+field[this.i];
                else if(this.i < (field.length - 1))
                    this.sql_builder += ", "+field[this.i];
                else
                    this.sql_builder += ", "+field[this.i]+" ) values (";
            }
            
            // Set field to fill
            for(this.i = 0;this.i < field.length;this.i++)
            {
                if(this.i == (field.length - 1))
                    this.sql_builder += "?)";
                else
                    this.sql_builder += "?,";
            }
            
            // Prepare the query            
            this.ps = this.conn.prepareStatement(this.sql_builder);
            
            // fill the field
            for(this.i = 0;this.i < data.length;this.i++)
            {
                this.ps.setString(this.i + 1, data[i]);
            }
            
            return this.sql_builder;
        }
        catch(SQLException e)
        {
            return e.toString();
        }
    }
}
