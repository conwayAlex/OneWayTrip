/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OneWayTrip;
import java.util.Random;

/**
 *
 * @author Carson
 */
public class Shop {
    
    private String storeName;
    private String storeType;
    private Item[] shopItems;
    
    public Shop(String storeType, int itemAmount) {
        Random rando = new Random();
        this.storeType = storeType;
        if(storeType == "General Store") {
            generateItems(storeType, 5 + rando.nextInt(10));
        }
    }
    
    private void generateItems(String storeType, int itemAmounts) {
        ItemGenerator gen = new ItemGenerator();
        gen.generateStoreItems(storeType, itemAmounts);
    }
    
}
