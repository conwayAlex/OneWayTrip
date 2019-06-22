
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
        Instance inst = new Instance();
        inst.generateRoomLayout();
        inst.combineAdjacentRooms(10, 10);
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                System.out.print(inst.map[i][j]);
            }
            System.out.print('\n');
        }
        
        
        inst.generateRoomStruct(10, 10);
        inst.generateConnections();
        inst.generateMap();
        
        
        System.out.println(inst.rooms.length);
        for(int i = 0; i < inst.rooms.length; i++){
            System.out.print("Room num: " + inst.rooms[i].roomNumber + " Adj Rms: "
                    + inst.rooms[i].adjRooms[0] + " " + inst.rooms[i].adjRooms[1] + " "
                    + inst.rooms[i].adjRooms[2] + " " + inst.rooms[i].adjRooms[3] + "\n");
        }
        
        
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 3; j++){
                for(int k = 0; k < 10; k++){
                    for(int l = 0; l < 3; l++){
                        System.out.print(inst.mapGUI[i][k].tile[j][l]);
                    }
                }
                System.out.print('\n');
            }
        }
        
    }
}
