/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

/**
 *
 * @author AXIOO
 */
public class database {
    
    private String host = "sql6.freemysqlhosting.net";
    private String port = "3306";
    private String username = "sql6465737";
    // Windows private String password = "";
    // Mac private String password = "12345678910";
    // Online private String password = "FeI6VrQl8q";
    private String password = "FeI6VrQl8q";
    private String database = "sql6465737";
    
    protected String get_host()
    {
        return this.host;
    }
    
    protected String get_port()
    {
        return this.port;
    }
    
    protected String get_username()
    {
        return this.username;
    }
    
    protected String get_password()
    {
        return this.password;
    }
    
    protected String get_database()
    {
        return this.database;
    }
    
    protected void set_host(String host)       
    {
        this.host = host;
    }
    
    protected void set_port(String port)       
    {
        this.port = port;
    }
    
    protected void set_username(String username)       
    {
        this.username = username;
    }
    
    protected void set_password(String password)       
    {
        this.password = password;
    }
    
    protected void set_database(String database)       
    {
        this.database = database;
    }
     
}
