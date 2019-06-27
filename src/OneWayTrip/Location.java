

package OneWayTrip;
import java.util.Random;

/**
 * This class will generate a location that is not hostile
 * to the player. 
 * @author Alex Conway
 * @version 1.0 
 */
public class Location extends Event {
    
    private String locationType;
    private Shop[] shops;
    
    public Location(int playerLevel) {
        int shopAmount;
        Random rando = new Random();
        int randNum = rando.nextInt(20);
        if(randNum >= 15) {
            this.locationType = "City";
            shopAmount = 4 + rando.nextInt(2);
            shops = new Shop[shopAmount];
            shops[1] = new Shop("General Store", 15);
            for(int i = 1; i < shopAmount; i++) {
                randNum = rando.nextInt(100);
                if(randNum > 95) {
                    shops[i] = new Shop("Witch", 10);
                } else if(randNum > 90) {
                    shops[i] = new Shop("Cobbler", 10);
                } else if(randNum > 85) {
                    shops[i] = new Shop("Tanner", 10);
                } else if(randNum > 75) {
                    shops[i] = new Shop("Tailor", 10);
                } else if(randNum > 60) {
                    shops[i] = new Shop("Arcanist", 10);
                } else if(randNum > 45) {
                    shops[i] = new Shop("Alchemist", 10);
                } else if(randNum > 20) {
                    shops[i] = new Shop("Fletcher", 10);
                } else {
                    shops[i] = new Shop("Blacksmith", 10);
                }
                
            }
        } else if(randNum >= 10) {
            this.locationType = "Town";
            shopAmount = 2 + rando.nextInt(2);
            shops = new Shop[shopAmount];
            shops[1] = new Shop("General Store", 15);
            for(int i = 1; i < shopAmount; i++) {
                
            }
        } else if (randNum >= 5) {
            this.locationType = "Village";
            shopAmount = 1 + rando.nextInt(2);
            shops = new Shop[shopAmount];
            shops[1] = new Shop("General Store", 15);
            for(int i = 1; i < shopAmount; i++) {
                
            }
        } else {
            this.locationType = "Hamlet";
            shops = new Shop[1];
            shops[0] = new Shop("General Store", 15);
        }
        
    }
    
}
