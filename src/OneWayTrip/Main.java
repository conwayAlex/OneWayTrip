
package OneWayTrip;

import java.io.File;

/*
 * Main.java
 * Purpose: 
 *
 * @author Alex Conway
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        File f = new File("saves/");
        File[] fa = new File[f.listFiles().length];
        fa = f.listFiles();
        System.out.println(fa[0].lastModified());
        System.out.println(fa[1].lastModified());
        
        String[] menuItems = new String[f.listFiles().length];
        menuItems = f.list();
        for(int i = 0; i < menuItems.length; i++){
            menuItems[i] = menuItems[i].replace(".txt", "");
            System.out.println(menuItems[i]);
        }
        
    }
}
