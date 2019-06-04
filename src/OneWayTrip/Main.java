
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
        EventGenerator eg = new EventGenerator();
        
        for(int i = 0; i < 100; i++){
            eg.generateEvent();
        }
    }
}
