/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.lang.reflect.*;
import java.text.*;
import java.util.*;
import javax.sql.rowset.CachedRowSet;
import library.output;
import library.strings;
import middleware.authentication;
import model.sales;
import model.products;
import model.menu;
/**
 *
 * @author mac
 */
public class sale {
    
    output out = new output();
    strings str = new strings();
    sales sales  = new sales();
    products prod = new products();
    menu menu = new menu();
    Scanner input = new Scanner(System.in).useDelimiter("\n");
    authentication auth = new authentication();
    CachedRowSet crs;
    public boolean exit = false;
    public int i, select_menu;
    public String menu_list [] = new String [101];

    public void index() throws Exception
    {
        do
        {
            // Show menu product
            crs = menu.select_where("*", "menu_menu_id", "20", "");
            
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
            sales.change_table();
            
            // Show data prodcut             
            crs = sales.select_join_all("*", " left join user on sale_user_id = user_id left join product on sale_prod_id = prod_id ");
            
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
            sales.change_table();
            
            boolean isFind = false;
            boolean isEnough = false;
            String product, product_name = "", code, date;
            int prod_id = 0, price = 0, qty = 0, bought = 0,total = 0;
            
            this.i = 0;
            
            do
            {
                // Label            
                out.println(" === Buat Penjualan === ");
                out.print(" Id Product / Nama Produk  : ");
                product = input.next();
                
                crs = prod.select_where("*", "prod_id", product, " or prod_name = '" + product + "'");
                
                while(crs.next())
                {
                    this.i++;
                    
                    prod_id = crs.getInt("prod_id");
                    product_name = crs.getString("prod_name");
                    price = crs.getInt("prod_price");
                    qty = crs.getInt("prod_qty");
                }
                
                if(this.i > 0)
                {
                    crs = prod.select_where("*", "prod_id", product, " or prod_name = '" + product + "'");
                    this.print_product(crs);
                    
                    this.i = 0;
                    out.println("Apakah produk ini yang anda maksud ? Masukan 1 untuk benar dan sembarang angka untuk tidak !");
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
                    
                    out.println("Product tidak di temukan !! \n");
                    
                    isFind = false;
                }
            }
            while(!isFind);
            
            // Cukup
            do
            {
                out.print("\n Product Name              : " + product_name);
                out.print("\n Harga Produk              : " + String.valueOf(price));
                out.print("\n Stok Tersedia             : " + String.valueOf(qty));
                out.print("\n Yang ingin di beli        : ");
                bought = input.nextInt();
                total = bought * price; 
                
                if(bought > qty)
                {
                    out.println("Stok produk tidak cukup !! Masukan angka sesuai stok yang ada");
                    
                    isEnough = false;
                }
                else
                {
                    this.check_qty(prod_id, qty - bought);
                    
                    out.println("\n Total harga               : " + String.valueOf(total));
                    isEnough = true;
                }
            }
            while(!isEnough);
            
            
            // Create data
            String purch_code = "sale-" + new SimpleDateFormat("HH-mm-ss").format(new Date());
            String date_now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String [] field = {"sale_user_id", "sale_prod_id", "sale_code", "sale_date", "sale_qty", "sale_price", "sale_total"};
            String [] data = {String.valueOf(auth.getId()), String.valueOf(prod_id), purch_code, date_now, String.valueOf(bought), String.valueOf(price), String.valueOf(total)};
            
            sales.insert(field, data);
            
            // Label
            out.println("Data berhasil di buat !");
            out.println("Masukan sembarang angka untuk kembali ke menu penjualan !");
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
            sales.change_table();
            
            boolean isFind1 = false;
            boolean isFind2 = false;
            boolean isEnough = false;
            String product, product_name = "", code, date, id = "";
            int prod_id = 0, price = 0, qty = 0, bought = 0,total = 0;
            
            this.i = 0;
            
            do
            {
                // Label            
                out.println(" === Ubah penjualan === ");
                out.print(" Id / Kode Penjualan       : "); 
                id = input.next();
                
                crs = sales.select_join_where("*", " left join user on sale_user_id = user_id left join product on sale_prod_id = prod_id ", "sale_id", id, " or sale_code = '" + id + "' ");
                
                this.print_data(crs);
                
                // Check is find or not
                if(this.i > 0)
                {
                    crs = sales.select_where("*", "sale_id", id, " or sale_code = '" + id + "' ");
                    
                    while(crs.next())
                    {
                        id = crs.getString("sale_id");
                    }
                    
                    this.i = 0;
                    out.println("Apakah data ini yang ingin anda ubah ? Masukan 1 untuk benar dan sembarang angka untuk tidak !");
                    out.println("Pilihan anda ?");
                    
                    // Select menu
                    this.select_menu = input.nextInt();
                    
                    if(this.select_menu == 1)
                    {
                        isFind1 = true;
                    }
                    else
                    {
                        out.cls();
                        isFind1 = false;
                    }
                }
                else
                {
                    this.i = 0;
                    out.cls();
                    
                    out.println("Data tidak di temukan !! \n");
                    
                    isFind1 = false;
                }
            }
            while(!isFind1);
            
            do
            {
                out.print("\n Id Product / Nama Produk  : ");
                product = input.next();
                
                crs = prod.select_where("*", "prod_id", product, " or prod_name = '" + product + "'");
                
                while(crs.next())
                {
                    this.i++;
                    
                    prod_id = crs.getInt("prod_id");
                    product_name = crs.getString("prod_name");
                    price = crs.getInt("prod_price");
                    qty = crs.getInt("prod_qty");
                }
                
                if(this.i > 0)
                {
                    crs = prod.select_where("*", "prod_id", product, " or prod_name = '" + product + "'");
                    this.print_product(crs);
                    
                    this.i = 0;
                    out.println("Apakah produk ini yang anda maksud ? Masukan 1 untuk benar dan sembarang angka untuk tidak !");
                    out.println("Pilihan anda ?");
                    
                    // Select menu
                    this.select_menu = input.nextInt();
                    
                    if(this.select_menu == 1)
                    {
                        isFind2 = true;
                    }
                    else
                    {
                        out.cls();
                        isFind2 = false;
                    }
                }
                else
                {
                    this.i = 0;
                    out.cls();
                    
                    out.println("Product tidak di temukan !! \n");
                    
                    isFind2 = false;
                }
            }
            while(!isFind2);
            
            // Cukup
            do
            {
                out.print("\n Product Name              : " + product_name);
                out.print("\n Harga Produk              : " + String.valueOf(price));
                out.print("\n Stok Tersedia             : " + String.valueOf(qty));
                out.print("\n Yang ingin di beli        : ");
                bought = input.nextInt();
                total = bought * price; 
                
                if(bought > qty)
                {
                    out.println("Stok produk tidak cukup !! Masukan angka sesuai stok yang ada");
                    
                    isEnough = false;
                }
                else
                {
                    this.check_qty(prod_id, qty - bought);
                    
                    out.println("\n Total harga               : " + String.valueOf(total));
                    isEnough = true;
                }
            }
            while(!isEnough);
            
            
            // Create data
            String purch_code = "sale-" + new SimpleDateFormat("HH-mm-ss").format(new Date());
            String date_now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String [] field = {"sale_user_id", "sale_prod_id", "sale_code", "sale_date", "sale_qty", "sale_price", "sale_total"};
            String [] data = {String.valueOf(auth.getId()), String.valueOf(prod_id), purch_code, date_now, String.valueOf(bought), String.valueOf(price), String.valueOf(total)};
            
            this.restoreStok(id);
            sales.update(field, data, "sale_id", id);
            
            // Label
            out.println("Data berhasil di buat !");
            out.println("Masukan sembarang angka untuk kembali ke menu pembelian !");
            input.nextInt();
            
            this.exit = true;
        }
        while(!this.exit);
    }
    
    public void delete() throws Exception
    {
        do
        {
            sales.change_table();
            
            boolean isFind = false;
            String id = "";
            
            this.i = 0;
            
            // Find the data
            do
            {   
                // Label            
                out.println(" === Hapus Pembelian === ");
                out.print(" Id / Kode Pembelian       : "); 
                id = input.next();
                
                // Find data by id
                crs = sales.select_join_where("*", " left join user on sale_user_id = user_id left join product on sale_prod_id = prod_id ", " sale_id", id, " or sale_code = '" + id + "' ");
                
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
                        crs = sales.select_where("*", "sale_id", id, " or sale_code = '" + id + "' ");
                        
                        while(crs.next())
                        {
                            id = crs.getString("sale_id");
                        }
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
                    
                    out.println("Data tidak di temukan !! \n");
                    
                    isFind = false;
                }
            }
            while(!isFind);
 
            // Restore Stok
            this.restoreStok(id);
            
            // Delete data
            sales.delete( "sale_id", id);
            
            // Label
            out.println("Data berhasil di hapus ! \n");
            out.println("Masukan sembarang angka untuk kembali ke menu penjualan !");
            input.nextInt();
            
            this.exit = true;
        }
        while(!this.exit); 
    }
    
    public void search() throws Exception
    {
        do
        {
            sales.change_table();
            
            // Show data prodcut           
            out.println(" === Cari pembelian === ");
            out.print(" Cari : ");
            String value = input.next();
            
            String like = " sale_id like '%"+value+"%' or user_name like '%"+value+"%' or prod_name like '%"+value+"%' or sale_code like '%"+value+"%'";
            crs = sales.select_join_like("*", " left join user on sale_user_id = user_id left join product on sale_prod_id = prod_id ", like);
            
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
        out.println(" |                                       List Penjualan                                              |");            
        out.println(" =====================================================================================================");
        out.println(" | No |  Id  |  User  | Kode Penjualan |   Produk   |  Jumlah  |  Harga  |  Total Harga  |  Tanggal  |");
            
        while(crs.next())
        {
            this.i++;
                    
            String no = this.i < 10 ? String.valueOf(this.i) + " " : String.valueOf(this.i);
            int length_id = "  Id  |".length();
            int length_user = "  User  |".length();
            int length_code = " Kode Penjualan |".length();
            int length_product = "   Produk   |".length();
            int length_qty = "  Jumlah  |".length();
            int length_price = "  Harga  |".length();
            int length_total = "  Total Harga  |".length();
            int length_date = "  Tanggal  |".length();
                
            String id = str.clear_string(crs.getString("sale_id"), length_id);
            String name = str.clear_string(crs.getString("user_name"), length_user);
            String code = str.clear_string(crs.getString("sale_code"), length_code);
            String prod = str.clear_string(crs.getString("prod_name"), length_product);
            String qty = str.clear_string(crs.getString("sale_qty"), length_qty);
            String price = str.clear_string(crs.getString("sale_price"), length_price);
            String total = str.clear_string(crs.getString("sale_total"), length_total);
            String date = str.clear_string(crs.getString("sale_date"), length_date);
                
            out.println(" | "+no+" |"+id+name+code+prod+qty+price+total+date);
        }
            
        out.println(" =====================================================================================================\n");
    }
    
    public void print_product(CachedRowSet crs) throws Exception
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
    
    public void check_qty(int id, int stok)
    {
        prod.change_table();
        
        String [] field = {"prod_qty"};
        String [] data = {String.valueOf(stok)};
        
        prod.update(field, data, "prod_id", String.valueOf(id));
    }
    
    public void restoreStok(String id) throws Exception
    {
        sales.change_table();
        
        int stok = 0, final_stok = 0;
        String product = "";
        
        crs = sales.select_where("*", "sale_id", id, "");
        
        // Get purchase data
        while(crs.next())
        {
            product = crs.getString("sale_prod_id");
            stok = crs.getInt("sale_qty");
        }
        
        crs = prod.select_where("*", "prod_id", product, "");
        
        // Get product data
        while(crs.next())
        {
            final_stok = crs.getInt("prod_qty") + stok;
        }
                
        // Restore product stok
        String [] field = {"prod_qty"};
        String [] data = {String.valueOf(final_stok)};
        prod.update(field, data, "prod_id", product);
    }
}
    
