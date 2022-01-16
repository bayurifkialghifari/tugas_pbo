package pointofsale;

import java.lang.reflect.*;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;

public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
    
        Class classRef = Class.forName("controller.menu");
        Object instance = classRef.newInstance();
        Method method = classRef.getDeclaredMethod("test");
        method.invoke(instance);
        

    } 
}
