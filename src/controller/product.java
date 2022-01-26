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
import model.products;
import model.menu;
/**
 *
 * @author mac
 */
public class product {
    
    output out = new output();
    strings str = new strings();
    products prod = new products();
    menu menu = new menu();
    Scanner input = new Scanner(System.in).useDelimiter("\n");
    CachedRowSet crs;
    public boolean exit = false;
    public int i, select_menu;
    public String menu_list [] = new String [101];   
    
    public void index() throws Exception
    {
        do
        {
            // Show menu product
            crs = menu.select_where("*", "menu_menu_id", "8", "");
            
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
            prod.change_table();
            
            // Show data prodcut             
            crs = prod.select_where("*", "1", "1", "");
            
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
    
    public void create() throws Exception
    {
         do
        {
            prod.change_table();
            
            String nama;
            int price, qty;
            
            this.i = 0;
            
            // Label            
            out.println(" === Buat produk baru === ");
            out.print(" Nama Product  : ");
            nama = input.next();
            out.print("\n Harga Product : ");
            price = input.nextInt();
            out.print("\n Stok          : ");
            qty = input.nextInt();
            
            // Create data
            String [] field = {"prod_name", "prod_price", "prod_qty"};
            String [] data = {nama, String.valueOf(price), String.valueOf(qty)};
            
            prod.insert(field, data);
            
            // Label
            out.println("Data berhasil di buat !");
            out.println("Masukan sembarang angka untuk kembali ke menu product !");
            input.nextInt();
            
            this.exit = true;
        }
        while(!this.exit);
    }
     
    public void update() throws Exception
    {
        do
        {
            prod.change_table();
            
            boolean isFind = false;
            String nama;
            int price, qty, id;
            
            this.i = 0;
            
            // Find the data
            do
            {   
                // Label            
                out.println(" === Ubah produk === ");
                out.print(" Id Produk       : "); 
                id = input.nextInt();
                
                // Find data by id
                crs = prod.select_where("*", "prod_id", String.valueOf(id), "");
                
                // Print data
                this.print_data(crs);
                
                // Check is find or not
                if(this.i > 0)
                {
                    this.i = 0;
                    out.println("Apakah data ini yang ingin anda ubah ? Masukan 1 untuk benar dan sembarang angka untuk tidak !");
                    out.println("Pilihan anda ?");
                    
                    // Select menu
                    this.select_menu = input.nextInt();
                    
                    if(this.select_menu == 1)
                    {
                        isFind = true;
                    }
                    else
                    {
                        out.cls();
                        isFind = false;
                    }
                }
                else
                {
                    this.i = 0;
                    out.cls();
                    
                    out.println("Data tidak tidak di temukan !! \n");
                    
                    isFind = false;
                }
            }
            while(!isFind);
            
            out.print("\n Nama Produk   : ");
            nama = input.next();
            out.print("\n Harga Produk  : ");
            price = input.nextInt();
            out.print("\n Stok          : ");
            qty = input.nextInt();
            
            // Create data
            String [] field = {"prod_name", "prod_price", "prod_qty"};
            String [] data = {nama, String.valueOf(price), String.valueOf(qty)};
            
            prod.update(field, data, "prod_id", String.valueOf(id));
            
            // Label
            out.println("Data berhasil di ubah ! \n");
            out.println("Masukan sembarang angka untuk kembali ke menu product !");
            input.nextInt();
            
            this.exit = true;
        }
        while(!this.exit); 
    }
    
    public void delete() throws Exception
    {
        do
        {
            prod.change_table();
            
            boolean isFind = false;
            String nama;
            int price, qty, id;
            
            this.i = 0;
            
            // Find the data
            do
            {   
                // Label            
                out.println(" === Hapus produk === ");
                out.print(" Id Produk       : "); 
                id = input.nextInt();
                
                // Find data by id
                crs = prod.select_where("*", "prod_id", String.valueOf(id), "");
                
                // Print data
                this.print_data(crs);
                
                // Check is find or not
                if(this.i > 0)
                {
                    this.i = 0;
                    out.println("Apakah data ini yang ingin anda hapus ? Masukan 1 untuk benar dan sembarang angka untuk tidak !");
                    out.println("Pilihan anda ?");
                    
                    // Select menu
                    this.select_menu = input.nextInt();
                    
                    if(this.select_menu == 1)
                    {
                        isFind = true;
                    }
                    else
                    {
                        out.cls();
                        isFind = false;
                    }
                }
                else
                {
                    this.i = 0;
                    out.cls();
                    
                    out.println("Data tidak tidak di temukan !! \n");
                    
                    isFind = false;
                }
            }
            while(!isFind);
 
            
            // Create data
            prod.delete( "prod_id", String.valueOf(id));
            
            // Label
            out.println("Data berhasil di hapus ! \n");
            out.println("Masukan sembarang angka untuk kembali ke menu product !");
            input.nextInt();
            
            this.exit = true;
        }
        while(!this.exit); 
    }
     
    public void search() throws Exception
    {
        do
        {
            prod.change_table();
            
            // Show data prodcut           
            out.println(" === Cari produk === ");
            out.print(" Cari : ");
            String value = input.next();
            
            crs = prod.select_like("*", " prod_name like '%" + value +"%' or prod_id like '%" + value + "%'");
            
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
        out.println(" =============================================================");
        out.println(" |                        List Produk                        |");            
        out.println(" =============================================================");
        out.println(" | No |  Id  |   Nama Produk   |  Harga Produk  |  Quantity  |");
            
        while(crs.next())
        {
            this.i++;
                    
            String no = this.i < 10 ? String.valueOf(this.i) + " " : String.valueOf(this.i);
            int length_id = "  Id  |".length();
            int length_name = "   Nama Produk   |".length();
            int length_price = "  Harga Produk  |".length();
            int length_qty = "  Quantity  |".length();
                
            String id = str.clear_string(crs.getString("prod_id"), length_id);
            String name = str.clear_string(crs.getString("prod_name"), length_name);
            String price = str.clear_string(crs.getString("prod_price"), length_price);
            String qty = str.clear_string(crs.getString("prod_qty"), length_qty);
                
            out.println(" | "+no+" |"+id+name+price+qty);
        }
            
        out.println(" ============================================================= \n");
    }
}
