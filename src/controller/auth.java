/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Scanner;
import javax.sql.rowset.CachedRowSet;
import library.output;
import middleware.authentication;
/**
 *
 * @author AXIOO
 */
public class auth {
 
    output out = new output();
    authentication authentication = new authentication();
    Scanner input = new Scanner(System.in);
    CachedRowSet crs;
    
    public boolean exit = false;
    public int i;
    public String username, password;
    
    public void login() throws Exception
    {
        do
        {
            out.println("\n=====Point Of Sale=====");
            out.println("======== Login ========");
            out.print("Username : "); 
            this.username = input.next();
            out.print("Password : ");
            this.password = input.next();
            
            boolean status = authentication.auth(this.username, this.password);
            
            if(status)
            {
                // Clear screen
                out.cls();
                
                out.println("Login berhasil !! \n");
                this.exit = true;
            }
            else
            {
                // Clear screen
                out.cls();
                
                out.println("Username atau password yang anda masukan salah !! \n");
                out.println("Masukan angka 1 untuk login kembali");
                out.println("Atau masukan sembarang angka untuk kembali ke menu utama !!");
                
                out.println("Pilihan anda ?");
                
                int option = input.nextInt();
                if(option == 1)
                    this.exit = false;
                else
                    this.exit = true;
                
                // Clear screen
                out.cls();
            }
        }
        while(!exit);
    }
    
    public void register() throws Exception
    {
        do
        {
            out.println("=====Point Of Sale=====");
            out.println("======= Register ======");
            out.print("Username : "); 
            this.username = input.next();
            out.print("Password : ");
            this.password = input.next();
            
            boolean status = authentication.register(this.username, this.password);
            
            if(status)
            {
                // Clear screen
                out.cls();
                
                out.println("Registrasi berhasil !! Silahkan Login \n");
                this.exit = true;
            }
            else
            {
                // Clear screen
                out.cls();
                
                out.println("Username sudah dipakai !! \n");
            }
        }
        while(!exit);
    }
}
