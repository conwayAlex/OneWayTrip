
package OneWayTrip;

import java.util.Random;

/*
 * Main.java
 * Purpose: 
 *
 * @author Alex Conway
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
            Instance inst = new Instance("medium");
            inst.generateDungeon();
            inst.displayMap();
    }
}
