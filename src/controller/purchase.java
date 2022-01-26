/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.lang.reflect.*;
import java.util.Scanner;
import javax.sql.rowset.CachedRowSet;
import library.output;
import library.strings;
import model.purchases;
import model.menu;

/**
 *
 * @author mac
 */
public class purchase {
    
    output out = new output();
    strings str = new strings();
    purchases pur = new purchases();
    menu menu = new menu();
    Scanner input = new Scanner(System.in).useDelimiter("\n");;
    CachedRowSet crs;
    public boolean exit = false;
    public int i, select_menu;
    public String menu_list [] = new String [101];
    
    public void index() throws Exception
    {
        do
        {
            // Show menu product
            crs = menu.select_where("*", "menu_menu_id", "14", "");
            
            this.i = 0;
                
            // Label
            out.println("Silakan pilih salah satu menu yang ada di bawah ini");
                
            while(crs.next())
            {
                this.menu_list[this.i+1] = crs.getString("menu_id");
                this.i++;
                    
                out.println(crs.getString("menu_index") + ". " + crs.getString("menu_name"));
            }
                
            out.println(this.i+1 + ". Kembali \n");
                
            out.println("Pilihan anda ?");
                
            this.select_menu = input.nextInt();
                
            // Check exit
            this.exit = this.select_menu == (this.i+1) ? true : false;
                
            // Clear screen
            out.cls();
                
            if(!this.exit && this.select_menu < i+1)
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
            else if(this.select_menu > i+1)
                out.println("Masukan yang anda masukan salah !! \n");
        }
        while(!this.exit);
    }
    
    public void show_all() throws Exception
    {
        do
        {
            pur.change_table();
            
            // Show data prodcut             
            crs = pur.select_join_all("*", " left join user on pruch_user_id = user_id left join product on pruch_prod_id = prod_id ");
            
            this.i = 0;
            
            // Print data
            this.print_data(crs);
                
            // Label
            out.println("Masukan sembarang angka untuk kembali !");
            input.nextInt();
            this.exit = true;
        }
        while(!this.exit);
    }
    
    public void print_data(CachedRowSet crs) throws Exception
    {
        // Label
        out.println(" =====================================================================================================");
        out.println(" |                                       List Pembelian                                              |");            
        out.println(" =====================================================================================================");
        out.println(" | No |  Id  |  User  | Kode Pembelian |   Produk   |  Jumlah  |  Harga  |  Total Harga  |  Tanggal  |");
            
        while(crs.next())
        {
            this.i++;
                    
            String no = this.i < 10 ? String.valueOf(this.i) + " " : String.valueOf(this.i);
            int length_id = "  Id  |".length();
            int length_user = "  User  |".length();
            int length_code = " Kode Pembelian |".length();
            int length_product = "   Produk   |".length();
            int length_qty = "  Jumlah  |".length();
            int length_price = "  Harga  |".length();
            int length_total = "  Total Harga  |".length();
            int length_date = "  Tanggal  |".length();
                
            String id = str.clear_string(crs.getString("purch_id"), length_id);
            String name = str.clear_string(crs.getString("user_name"), length_user);
            String code = str.clear_string(crs.getString("pruch_code"), length_code);
            String prod = str.clear_string(crs.getString("prod_name"), length_product);
            String qty = str.clear_string(crs.getString("purch_qty"), length_qty);
            String price = str.clear_string(crs.getString("purch_price"), length_price);
            String total = str.clear_string(crs.getString("purch_total"), length_total);
            String date = str.clear_string(crs.getString("purch_date"), length_date);
                
            out.println(" | "+no+" |"+id+name+code+prod+qty+price+total+date);
        }
            
        out.println(" =====================================================================================================\n");
    }
}
