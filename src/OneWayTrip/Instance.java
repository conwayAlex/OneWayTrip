package OneWayTrip;

import java.util.Random;
import java.lang.Math;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * This will be used to generate instances for the player to
 * explore. They will be procedural and can be varying lengths 
 * and difficulties.
 * @author Alex Conway
 * @version 1.0 
 */
public class Instance extends Event {
    private int roomCount = 0;
    private int sizeLower;
    private int sizeUpper;
    private int sizeX;
    private int sizeY;
    private int[] s;
    private int[][] map;
    private MapTile[][] mapGUI;
    private Random rng = new Random();
    private Room[] rooms;

    public Instance(String size){
        //provide a size value, which will set the variables to the size values needed
        switch(size){
            case "small": {
                sizeLower = 3;
                sizeUpper = 6;
                sizeX = 5;
                sizeY = 5;
                map = new int[sizeX][sizeY];
                break;
            }
            case "medium": {
                sizeLower = 5;
                sizeUpper = 15;
                sizeX = 10;
                sizeY = 10;
                map = new int[sizeX][sizeY];
                break;
            }
            case "large": {
                sizeLower = 15;
                sizeUpper = 25;
                sizeX = 15;
                sizeY = 15;
                map = new int[sizeX][sizeY];
                break;
            }
        }
        
        
        
    } 
    public void generateDungeon(){
        boolean finished = false;
        while (!finished) {
            generateRoomLayout();
            combineAdjacentRooms();
            generateRoomStructure();
            generateConnections();
            initMap();
            finished = connectRooms();
            if(finished){
                placeRooms();
            }
        }  
    }
    public void displayMap() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < sizeY; k++) {
                    for (int l = 0; l < 3; l++) {
                        System.out.print(mapGUI[i][k].tile[j][l]);
                    }
                }
                System.out.print('\n');
            }
        }
    }
    public void printData(){
        //all rooms and adjacencies
        for (int i = 0; i < rooms.length; i++) {
                System.out.print("Room num: " + rooms[i].roomNumber + " Adj Rms: "
                        + rooms[i].adjRooms[0] + " " + rooms[i].adjRooms[1] + " "
                        + rooms[i].adjRooms[2] + " " + rooms[i].adjRooms[3] + "\n");
            }
    }
    private void generateRoomLayout(){
        int pick;
        boolean diag;
        while (roomCount < sizeLower) {
            for (int i = 1; i < sizeX-1; i++) {
                for (int j = 1; j < sizeY-1; j++) {
                    if (roomCount < sizeUpper) {
                        pick = 1 + rng.nextInt(1000);
                        if (map[i][j] == 0) {
                            if (pick >= 950) {
                                if(i-1 >= 0 && j-1 >= 0 && map[i-1][j-1] == 1){
                                    diag = true;
                                }
                                else if (i-1 >= 0 && j+1 < sizeY && map[i-1][j+1] == 1) {
                                    diag = true;
                                }
                                else if (i+1 < sizeX && j+1 < sizeY && map[i+1][j+1] == 1){
                                    diag = true;
                                }
                                else diag = i+1 < sizeX && j-1 >= 0 && map[i+1][j-1] == 1;
                                if(!diag){
                                    map[i][j] = 1;
                                    roomCount++;
                                }
                            } 
                            else {
                                map[i][j] = 0;
                            }
                        }
                    }
                    else {
                        map[i][j] = 0;
                    }
                }
            }
        }
    }
    private void combineAdjacentRooms(){
        int rC = 0;
        for(int i = 0; i < sizeX; i++){
            for(int j = 0; j < sizeY; j++){
                if(map[i][j] == 1){
                    //check all four wall sharing cells, make sure no out of bounds access
                    if(i-1 >= 0 && map[i-1][j] == 1){
                        map[i][j] += 1;
                        map[i-1][j] = 0;
                    }
                    if(i+1 <= sizeX-1 && map[i+1][j] == 1){
                        map[i][j] += 1;
                        map[i+1][j] = 0;
                    }
                    if(j-1 >= 0 && map[i][j-1] == 1){
                        map[i][j] += 1;
                        map[i][j-1] = 0;
                    }
                    if(j+1 <= sizeY-1 && map[i][j+1] == 1){
                        map[i][j] += 1;
                        map[i][j+1] = 0;
                    }
                    rC++;
                }
            }
        }
        rooms = new Room[rC];
    } 
    private void generateRoomStructure(){
        int rCount = 0;
        for(int i = 0; i < sizeX; i++){
            for(int j = 0; j < sizeY; j++){
                if(map[i][j] > 0) {
                    Room r = new Room();
                    r.roomNumber = rCount;
                    r.x = i;
                    r.y = j;
                    r.size = map[i][j];
                    rooms[rCount] = r;
                    rCount++;
                }
            }
        }
    }
    private void generateConnections(){
        //which rooms to connect to which other rooms
        //shortest path algorithm
        //not working properly
        //Lee algorithm may be the key
        int pick;
        int closestRoom = 0;
        int r = 0;
        int pair1 = 0,
            pair2 = 0;
        double distCurr,
               distPrev = 10000;
        boolean connected = false;
        s = new int[rooms.length]; // reachable array
        
        //pick some room 
        while(!connected){
            for(int i = 0; i < rooms.length; i++){
                if (i != r) {
                    distCurr = Math.sqrt(Math.pow(rooms[i].x - rooms[r].x, 2) + Math.pow(rooms[i].y - rooms[r].y, 2));
                    if (distCurr < distPrev) {
                        closestRoom = i;
                        distPrev = distCurr;
                    }
                } 
            }
            rooms[r].adjRooms[0] = closestRoom;
            distPrev = 10000;
            r++;
            if(r == rooms.length){
                connected = true;
            }
        }
        //notify rooms of connections it didnt make itself
        for(int i = 0; i < rooms.length; i++){
            for(int j = 0; j < rooms.length; j++){
                if(i != j && i == rooms[j].adjRooms[0]){
                    for(int k = 0; k < 4; k++){
                        if(rooms[i].adjRooms[k] == j){
                            break;
                        }
                        else if (rooms[i].adjRooms[k] == -1){
                            rooms[i].adjRooms[k] = j;
                            break;
                        }
                    }
                }
            }
        }     
        
        //arbitrarily pick a room to test what half of map not connected to other half
        pick = rng.nextInt(rooms.length);
        s[pick] = explore(pick);
        connected = true;
        for(int i = 0; i < s.length; i++){
            if(s[i] == 0){
                connected = false;
            }
        }
        if(!connected){
            while(!connected){
                //this needs to be arbitrary
                //closest pair that one is part of the unvisited section and one part of visited section
                for(int i = 0; i < s.length; i++){
                    if(s[i] == 0){
                        for(int j = 0; j < rooms.length; j++){
                            if (i != j && s[j] != 0) {
                                distCurr = Math.sqrt(Math.pow(rooms[i].x - rooms[j].x, 2) + Math.pow(rooms[i].y - rooms[j].y, 2));
                                if (distCurr < distPrev) {
                                    pair1 = i;
                                    pair2 = j;
                                    distPrev = distCurr;
                                }
                            }
                        }
                    }
                }
                distPrev = 10000;
                for (int j = 0; j < 4; j++) {
                    if (rooms[pair1].adjRooms[j] == -1) {
                        rooms[pair1].adjRooms[j] = pair2;
                        break;
                    }
                }
                for (int j = 0; j < 4; j++) {
                    if (rooms[pair2].adjRooms[j] == -1) {
                        rooms[pair2].adjRooms[j] = pair1;
                        break;
                    }
                }
                for (int i = 0; i < s.length; i++) {
                    s[i] = 0;
                }
                pick = rng.nextInt(rooms.length);
                s[pick] = explore(pick);
                connected = true;
                for (int i = 0; i < s.length; i++) {
                    if (s[i] == 0) {
                        connected = false;
                    }
                }
            }  
        }
        pick = rng.nextInt(rooms.length);
        s[pick] = explore(pick);
    }
    private int explore(int k) {
        s[k] = 1;
        if (s[rooms[k].adjRooms[0]] != -1 && s[rooms[k].adjRooms[0]] != 1) {
            s[rooms[k].adjRooms[0]] = explore(rooms[k].adjRooms[0]);
        }
        if (rooms[k].adjRooms[1] != -1 && s[rooms[k].adjRooms[1]] != 1) {
            s[rooms[k].adjRooms[1]] = explore(rooms[k].adjRooms[1]);
        }
        if (rooms[k].adjRooms[2] != -1 && s[rooms[k].adjRooms[2]] != 1) {
            s[rooms[k].adjRooms[2]] = explore(rooms[k].adjRooms[2]);
        }
        if (rooms[k].adjRooms[3] != -1 && s[rooms[k].adjRooms[3]] != 1) {
            s[rooms[k].adjRooms[3]] = explore(rooms[k].adjRooms[3]);
        }
        return 1;
    }
    private void initMap(){
        mapGUI = new MapTile[sizeX][sizeY];
        //create room tiles
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                mapGUI[i][j] = new MapTile();
            }
        }
    }
    private void placeRooms(){
        int k = 0;
        for(int i = 0; i < sizeX; i++){
            for(int j = 0; j < sizeY; j++){
                if (map[i][j] > 0){
                    mapGUI[i][j] = new MapTile();
                    mapGUI[i][j].isRoom = true;
                    mapGUI[i][j].tile[0][0] = '\u250f';
                    mapGUI[i][j].tile[0][1] = '\u2501';
                    mapGUI[i][j].tile[0][2] = '\u2513';
                    mapGUI[i][j].tile[1][0] = '\u2503';
                    mapGUI[i][j].tile[1][1] = (char)(rooms[k].roomNumber+'0');
                    mapGUI[i][j].tile[1][2] = '\u2503';
                    mapGUI[i][j].tile[2][0] = '\u2517';
                    mapGUI[i][j].tile[2][1] = '\u2501';
                    mapGUI[i][j].tile[2][2] = '\u251b';
                    k++;
                }
            }
        }
    }
    //this code adapted from geeksforgeeks.org/check-if-two-given...
    //returns true if they intersect
    private boolean checkIntersection(int p1, int p2, int q1, int q2){
        int o1, o2, o3, o4;
        int val;
        val = (rooms[q1].y - rooms[p1].y) * (rooms[p2].x - rooms[q1].x) -
                (rooms[q1].x - rooms[p1].x) * (rooms[p2].y - rooms[q1].y);
        o1 = (val > 0) ? 1:2;
        val = (rooms[q1].y - rooms[p1].y) * (rooms[q2].x - rooms[q1].x) -
                (rooms[q1].x - rooms[p1].x) * (rooms[q2].y - rooms[q1].y);
        o2 = (val > 0) ? 1:2;
        val = (rooms[q2].y - rooms[p2].y) * (rooms[p1].x - rooms[q2].x) -
                (rooms[q2].x - rooms[p2].x) * (rooms[p1].y - rooms[q2].y);
        o3 = (val > 0) ? 1:2;
        val = (rooms[q2].y - rooms[p2].y) * (rooms[q1].x - rooms[q2].x) -
                (rooms[q2].x - rooms[p2].x) * (rooms[q1].y - rooms[q2].y);
        o4 = (val > 0) ? 1:2;
        
        return o1 != o2 && o3 != o4;
    }
    private boolean connectRooms(){
        int[][] toBuild = new int[rooms.length*2][2];
        int adjC = 0;
        int x1, x2, y1, y2;
        int k = 0;
        boolean built = false;
        boolean collision = false;

        //initialize for control
        for(int i = 0; i < rooms.length*2; i++){
            for(int j = 0; j < 2; j++){
                toBuild[i][j] = -1;
            }
        }
        
        // <editor-fold defaultstate="collapsed" desc="BuildPathQueue">
        //for each possible amount of rooms leaving a room (4 max)
        //we start at 4 and work our way down to allow the room(s) with the
        //most outbound connections priority
        for(int i = 4; i > 0; i--){
            //for each room
            for(int j = 0; j < rooms.length; j++){
                //for each adj room
                for(int m = 0; m < 4; m++){
                    //calculate all the angles from current observed room to its connected rooms
                    if(rooms[j].adjRooms[m] != -1){
                        double ax = rooms[j].x,
                               ay = rooms[j].y,
                               bx = rooms[rooms[j].adjRooms[m]].x,
                               by = rooms[rooms[j].adjRooms[m]].y;
                        rooms[j].angle[m] = Math.toDegrees(Math.atan2(ay - by, ax - bx));
                        adjC++;
                        if (rooms[j].angle[m] < 0) {
                            rooms[j].angle[m] += 360;

                        }
                    }
                }
                //check if the priority is matched
                if (adjC == i) {
                    // <editor-fold defaultstate="collapsed" desc="DirectionHandling">
                    boolean northUsed = false,
                            southUsed = false,
                            eastUsed = false,
                            westUsed = false;
                    int prob1 = 0,
                        prob2 = 0;
                    for (int m = 0; m < 4; m++) {
                        if (rooms[j].angle[m] == 360.0) {
                            rooms[j].directions[m] = "north";
                            northUsed = true;
                        } else if (rooms[j].angle[m] == 90.0) {
                            rooms[j].directions[m] = "west";
                            westUsed = true;
                        } else if (rooms[j].angle[m] == 180.0) {
                            rooms[j].directions[m] = "south";
                            southUsed = true;
                        } else if (rooms[j].angle[m] == 270.0) {
                            rooms[j].directions[m] = "east";
                            eastUsed = true;
                        } else if (rooms[j].angle[m] > 45.0 && rooms[j].angle[m] <= 135.0) {
                            rooms[j].directions[m] = "west";
                            westUsed = true;
                        } else if (rooms[j].angle[m] > 135.0 && rooms[j].angle[m] <= 225.0) {
                            rooms[j].directions[m] = "south";
                            southUsed = true;
                        } else if (rooms[j].angle[m] > 225.0 && rooms[j].angle[m] <= 315.0) {
                            rooms[j].directions[m] = "east";
                            eastUsed = true;
                        } else if ((rooms[j].angle[m] > 315.0 || rooms[j].angle[m] <= 45.0) && rooms[j].angle[m] != -1) {
                            rooms[j].directions[m] = "north";
                            northUsed = true;
                        } else if (rooms[j].angle[m] == 45.0) {
                            rooms[j].directions[m] = "north";
                            northUsed = true;
                        } else if (rooms[j].angle[m] == 135.0) {
                            rooms[j].directions[m] = "west";
                            westUsed = true;
                        } else if (rooms[j].angle[m] == 225.0) {
                            rooms[j].directions[m] = "south";
                            southUsed = true;
                        } else if (rooms[j].angle[m] == 315.0) {
                            rooms[j].directions[m] = "east";
                            eastUsed = true;
                        } else {
                            rooms[j].directions[m] = "null";
                        }
                    }

                    for (int m = 0; m < 4; m++) {
                        for (int n = 0; n < 4; n++) {
                            if (m != n && rooms[j].directions[m].compareTo("null") != 0) {
                                if (rooms[j].directions[m].compareTo(rooms[j].directions[n]) == 0) {
                                    collision = true;
                                    prob1 = m;
                                    prob2 = n;
                                    break;
                                }
                            }
                        }
                    }
                    if (collision) {
                        if (rooms[j].directions[prob1].compareTo("north") == 0) {
                            if (rooms[j].angle[prob1] == 360) {
                                if (rooms[j].angle[prob2] >= 315) {
                                    if (!eastUsed) {
                                        rooms[j].directions[prob2] = "east";
                                    }
                                } else if (rooms[j].angle[prob2] <= 45) {
                                    if (!westUsed) {
                                        rooms[j].directions[prob2] = "west";
                                    }
                                }
                            } else if (rooms[j].angle[prob2] == 360) {
                                if (rooms[j].angle[prob1] >= 315) {
                                    if (!eastUsed) {
                                        rooms[j].directions[prob1] = "east";
                                    }
                                } else if (rooms[j].angle[prob1] <= 45) {
                                    if (!westUsed) {
                                        rooms[j].directions[prob1] = "west";
                                    }
                                }
                            } else {
                                //neither is directly north
                                if (rooms[j].angle[prob1] >= 315 && rooms[j].angle[prob2] <= 45) {
                                    //one is more east and one is more west
                                    if (!westUsed) {
                                        rooms[j].directions[prob2] = "west";
                                    } else if (!eastUsed) {
                                        rooms[j].directions[prob1] = "east";
                                    }
                                } else if (rooms[j].angle[prob2] >= 315 && rooms[j].angle[prob1] <= 45) {
                                    if (!westUsed) {
                                        rooms[j].directions[prob1] = "west";
                                    } else if (!eastUsed) {
                                        rooms[j].directions[prob2] = "east";
                                    }
                                }
                            }
                        } else if (rooms[j].directions[prob1].compareTo("south") == 0) {
                            if (rooms[j].angle[prob1] == 180) {
                                if (rooms[j].angle[prob2] >= 135 && rooms[j].angle[prob2] <= 180) {
                                    if (!westUsed) {
                                        rooms[j].directions[prob2] = "west";
                                    }
                                } else if (rooms[j].angle[prob2] <= 225 && rooms[j].angle[prob2] >= 180) {
                                    if (!eastUsed) {
                                        rooms[j].directions[prob2] = "east";
                                    }
                                }
                            } else if (rooms[j].angle[prob2] == 180) {
                                if (rooms[j].angle[prob1] >= 135 && rooms[j].angle[prob1] <= 180) {
                                    if (!westUsed) {
                                        rooms[j].directions[prob1] = "west";
                                    }
                                } else if (rooms[j].angle[prob1] <= 225 && rooms[j].angle[prob1] >= 180) {
                                    if (!eastUsed) {
                                        rooms[j].directions[prob1] = "east";
                                    }
                                }
                            } else {
                                //neither is directly south
                                if ((rooms[j].angle[prob2] >= 135 && rooms[j].angle[prob2] <= 180) && (rooms[j].angle[prob1] <= 225 && rooms[j].angle[prob1] >= 180)) {
                                    //one is more east and one is more west
                                    if (!westUsed) {
                                        rooms[j].directions[prob2] = "west";
                                    } else if (!eastUsed) {
                                        rooms[j].directions[prob1] = "east";
                                    }
                                } else if ((rooms[j].angle[prob1] >= 135 && rooms[j].angle[prob1] <= 180) && (rooms[j].angle[prob2] <= 225 && rooms[j].angle[prob2] >= 180)) {
                                    if (!westUsed) {
                                        rooms[j].directions[prob1] = "west";
                                    } else if (!eastUsed) {
                                        rooms[j].directions[prob2] = "east";
                                    }
                                }
                            }
                        } else if (rooms[j].directions[prob1].compareTo("east") == 0) {
                            if (rooms[j].angle[prob1] == 270) {
                                if (rooms[j].angle[prob2] >= 225 && rooms[j].angle[prob2] <= 270) {
                                    if (!southUsed) {
                                        rooms[j].directions[prob2] = "south";
                                    }
                                } else if (rooms[j].angle[prob2] <= 315 && rooms[j].angle[prob2] >= 270) {
                                    if (!northUsed) {
                                        rooms[j].directions[prob2] = "north";
                                    }
                                }
                            } else if (rooms[j].angle[prob2] == 270) {
                                if (rooms[j].angle[prob1] >= 225 && rooms[j].angle[prob1] <= 270) {
                                    if (!southUsed) {
                                        rooms[j].directions[prob1] = "south";
                                    }
                                } else if (rooms[j].angle[prob1] <= 315 && rooms[j].angle[prob1] >= 270) {
                                    if (!northUsed) {
                                        rooms[j].directions[prob1] = "north";
                                    }
                                }
                            } else {
                                //neither is directly south
                                if ((rooms[j].angle[prob2] >= 225 && rooms[j].angle[prob2] <= 270) && (rooms[j].angle[prob1] <= 315 && rooms[j].angle[prob1] >= 270)) {
                                    //one is more east and one is more west
                                    if (!southUsed) {
                                        rooms[j].directions[prob2] = "south";
                                    } else if (!northUsed) {
                                        rooms[j].directions[prob1] = "north";
                                    }
                                } else if ((rooms[j].angle[prob1] >= 225 && rooms[j].angle[prob1] <= 270) && (rooms[j].angle[prob2] <= 315 && rooms[j].angle[prob2] >= 270)) {
                                    if (!southUsed) {
                                        rooms[j].directions[prob1] = "south";
                                    } else if (!northUsed) {
                                        rooms[j].directions[prob2] = "north";
                                    }
                                }
                            }
                        } else if (rooms[j].directions[prob1].compareTo("west") == 0) {
                            if (rooms[j].angle[prob1] == 90) {
                                if (rooms[j].angle[prob2] >= 90 && rooms[j].angle[prob2] <= 135) {
                                    if (!southUsed) {
                                       rooms[j]. directions[prob2] = "south";
                                    }
                                } else if (rooms[j].angle[prob2] <= 90 && rooms[j].angle[prob2] >= 45) {
                                    if (!northUsed) {
                                        rooms[j].directions[prob2] = "north";
                                    }
                                }
                            } else if (rooms[j].angle[prob2] == 90) {
                                if (rooms[j].angle[prob1] >= 90 && rooms[j].angle[prob1] <= 135) {
                                    if (!southUsed) {
                                        rooms[j].directions[prob1] = "south";
                                    }
                                } else if (rooms[j].angle[prob1] <= 90 && rooms[j].angle[prob1] >= 45) {
                                    if (!northUsed) {
                                        rooms[j].directions[prob1] = "north";
                                    }
                                }
                            } else {
                                //neither is directly south
                                if ((rooms[j].angle[prob2] >= 90 && rooms[j].angle[prob2] <= 135) && (rooms[j].angle[prob1] <= 90 && rooms[j].angle[prob1] >= 45)) {
                                    //one is more east and one is more west
                                    if (!southUsed) {
                                        rooms[j].directions[prob2] = "south";
                                    } else if (!northUsed) {
                                        rooms[j].directions[prob1] = "north";
                                    }
                                } else if ((rooms[j].angle[prob1] >= 90 && rooms[j].angle[prob1] <= 135) && (rooms[j].angle[prob2] <= 90 && rooms[j].angle[prob2] >= 45)) {
                                    if (!southUsed) {
                                        rooms[j].directions[prob1] = "south";
                                    } else if (!northUsed) {
                                        rooms[j].directions[prob2] = "north";
                                    }
                                }
                            }
                        }
                    }
                    collision = false;
                    for (int m = 0; m < 4; m++) {
                        for (int n = 0; n < 4; n++) {
                            if (m != n && rooms[j].directions[m].compareTo("null") != 0) {
                                if (rooms[j].directions[m].compareTo(rooms[j].directions[n]) == 0) {
                                    collision = true;
                                    break;
                                }
                            }
                        }
                    }
                    if(collision){
                        if(rooms[j].directions[prob1].compareTo("north") == 0){
                            if(eastUsed && westUsed){
                                if(rooms[j].angle[prob1] > 0 && rooms[j].angle[prob1] < 90){
                                    for(int m = 0; m < 4; m++){
                                        if (rooms[j].directions[m].compareTo("west") == 0) {
                                            rooms[j].directions[m] = "south";
                                            break;
                                        }
                                    }
                                    rooms[j].directions[prob1] = "west";
                                } else {
                                    for(int m = 0; m < 4; m++){
                                        if(rooms[j].directions[m].compareTo("east") == 0){
                                            rooms[j].directions[m] = "south";
                                            break;
                                        }
                                    }
                                    rooms[j].directions[prob1] = "east";
                                }
                            }
                        } else if (rooms[j].directions[prob1].compareTo("east") == 0) {
                            if (northUsed && southUsed) {
                                if(rooms[j].angle[prob1] > 270 && rooms[j].angle[prob1] < 360){ 
                                    for(int m = 0; m < 4; m++){
                                        if(rooms[j].directions[m].compareTo("north") == 0){
                                            rooms[j].directions[m] = "west";
                                            break;
                                        }
                                    }
                                    rooms[j].directions[prob1] = "north";
                                } else {
                                    for(int m = 0; m < 4; m++){
                                        if(rooms[j].directions[m].compareTo("south") == 0){
                                            rooms[j].directions[m] = "west";
                                            break;
                                        }
                                    }
                                    rooms[j].directions[prob1] = "south";
                                }
                            }
                        } else if (rooms[j].directions[prob1].compareTo("south") == 0){
                            if(eastUsed && westUsed){
                                if(rooms[j].angle[prob1] > 180 && rooms[j].angle[prob1] < 270){
                                    for(int m = 0; m < 4; m++){
                                        if(rooms[j].directions[m].compareTo("east") == 0){
                                            rooms[j].directions[m] = "north";
                                            break;
                                        }
                                    }
                                    rooms[j].directions[prob1] = "east";
                                } else {
                                    for(int m = 0; m < 4; m++){
                                        if(rooms[j].directions[m].compareTo("west") == 0){
                                            rooms[j].directions[m] = "north";
                                            break;
                                        }
                                    }
                                    rooms[j].directions[prob2] = "west";
                                }
                            }
                        } else if (rooms[j].directions[prob1].compareTo("west") == 0){
                            if (northUsed && southUsed) {
                                if(rooms[j].angle[prob1] < 90 && rooms[j].angle[prob1] > 0){ 
                                    for(int m = 0; m < 4; m++){
                                        if(rooms[j].directions[m].compareTo("north") == 0){
                                            rooms[j].directions[m] = "east";
                                            break;
                                        }
                                    }
                                    rooms[j].directions[prob1] = "north";
                                } else {
                                    for(int m = 0; m < 4; m++){
                                        if(rooms[j].directions[m].compareTo("south") == 0){
                                            rooms[j].directions[m] = "east";
                                            break;
                                        }
                                    }
                                    rooms[j].directions[prob1] = "south";
                                }
                            }                        
                        }
                    }
                    for (int m = 0; m < 4; m++) {
                        for (int n = 0; n < 4; n++) {
                            if (m != n && rooms[j].directions[m].compareTo("null") != 0) {
                                if (rooms[j].directions[m].compareTo(rooms[j].directions[n]) == 0) {
                                    return false;
                                }
                            }
                        }
                    }
                    
                    
                    // </editor-fold>     
                    //verify the room to be built has not been built already
                    for (int m = 0; m < 4; m++) {
                        for (int n = 0; n < toBuild.length; n++) {
                            if (toBuild[n][0] == rooms[j].roomNumber && toBuild[n][1] == rooms[j].adjRooms[m]) {
                                built = true;
                                break;
                            } else if (toBuild[n][1] == rooms[j].roomNumber && toBuild[n][0] == rooms[j].adjRooms[m]) {
                                built = true;
                                break;
                            } else {
                                built = false;
                            }
                        }
                        if (!built && rooms[j].adjRooms[m] != -1) {
                            toBuild[k][0] = rooms[j].roomNumber;
                            toBuild[k][1] = rooms[j].adjRooms[m];
                            k++;
                            built = false;
                        }
                    }
                }
                //reset values
                collision = false;
                adjC = 0;
            }
        }
        // </editor-fold>
        
        int aIndex = 0;
        Queue<Integer> q = new ArrayDeque<>();
        
        //building a queue with cardinal angles first then everything else
        for(int i = 0; i < k; i++){
            for(int j = 0; j < 4; j++){
                if(rooms[toBuild[i][0]].adjRooms[j] == toBuild[i][1]){
                    aIndex = j;
                }
            }
            if(rooms[toBuild[i][0]].angle[aIndex] == 0 || rooms[toBuild[i][0]].angle[aIndex] == 90
                    || rooms[toBuild[i][0]].angle[aIndex] == 180 || rooms[toBuild[i][0]].angle[aIndex] == 270
                    || rooms[toBuild[i][0]].angle[aIndex] == 360){
                q.add(i);
            }
        }
        for(int i = 0; i < k; i++){
            for(int j = 0; j < 4; j++){
                if(rooms[toBuild[i][0]].adjRooms[j] == toBuild[i][1]){
                    aIndex = j;
                }
            }
            if(rooms[toBuild[i][0]].angle[aIndex] != 0 && rooms[toBuild[i][0]].angle[aIndex] != 90
                    && rooms[toBuild[i][0]].angle[aIndex] != 180 && rooms[toBuild[i][0]].angle[aIndex] != 270
                    && rooms[toBuild[i][0]].angle[aIndex] != 360){
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            k = q.poll();
            String targetDir = "null";
            x1 = rooms[toBuild[k][0]].x;
            y1 = rooms[toBuild[k][0]].y;
            x2 = rooms[toBuild[k][1]].x;
            y2 = rooms[toBuild[k][1]].y;
            
            
            //finding the matching indices for internal data
            for(int i = 0; i < 4; i++){
                if(rooms[toBuild[k][0]].adjRooms[i] == toBuild[k][1]){
                    if(rooms[toBuild[k][0]].angle[i] != 0 && rooms[toBuild[k][0]].angle[i] != 90
                            && rooms[toBuild[k][0]].angle[i] != 180 && rooms[toBuild[k][0]].angle[i] != 270
                            && rooms[toBuild[k][0]].angle[i] != 360){
                        for(int j = 0; j < 4; j++){
                            if(rooms[toBuild[k][1]].adjRooms[j] == toBuild[k][0]){
                                if(rooms[toBuild[k][1]].directions[j].compareTo("north") == 0){
                                    targetDir = "north";
                                } else if (rooms[toBuild[k][1]].directions[j].compareTo("east") == 0){
                                    targetDir = "east";
                                } else if (rooms[toBuild[k][1]].directions[j].compareTo("south") == 0){
                                    targetDir = "south";
                                } else if (rooms[toBuild[k][1]].directions[j].compareTo("west") == 0){
                                    targetDir = "west";
                                }
                                break;
                            }
                        }
                    }
                    if(buildPath(x1, x2, y1, y2, rooms[toBuild[k][0]].directions[i], targetDir)){
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }
    private boolean buildPath(int i1, int i2, int j1, int j2, String direction, String targetDir){
        int iTemp = i1, 
            jTemp = j1;
        int pick;
        char lastPick = ' ';
        int north = 0,
            south = 0,
            east = 0,
            west = 0;
        int adjust = 0;                     //for making the pathing finding dynamic to the directionality logic
        boolean connected = false;
        int iterationCount = 0;
        
        // <editor-fold defaultstate="collapsed" desc="Initialize Pathing">
        if(targetDir.compareTo("null") != 0){
            if(targetDir.compareTo("north") == 0){
                adjust = 1;
            } else if (targetDir.compareTo("east") == 0){
                adjust = 1;
            } else if (targetDir.compareTo("south") == 0){
                adjust = 1;
            } else if (targetDir.compareTo("west") == 0){
                adjust = 1;
            }
        }
        
        if(direction.compareTo("north") == 0){
            if (mapGUI[iTemp - 1][jTemp].tile[1][1] == '\u2588') {
                iTemp--;
                mapGUI[iTemp][jTemp].tile[0][1] = '\u2503';
                mapGUI[iTemp][jTemp].tile[1][1] = '\u2503';
                mapGUI[iTemp][jTemp].tile[2][1] = '\u2503';
                lastPick = 'N';
            }
        } else if (direction.compareTo("south") == 0) {
            if (mapGUI[iTemp + 1][jTemp].tile[1][1] == '\u2588') {
                iTemp++;
                mapGUI[iTemp][jTemp].tile[0][1] = '\u2503';
                mapGUI[iTemp][jTemp].tile[1][1] = '\u2503';
                mapGUI[iTemp][jTemp].tile[2][1] = '\u2503';
                lastPick = 'S';
            }
        } else if (direction.compareTo("west") == 0) {
            if (mapGUI[iTemp][jTemp - 1].tile[1][1] == '\u2588') {
                jTemp--;
                mapGUI[iTemp][jTemp].tile[1][0] = '\u2501';
                mapGUI[iTemp][jTemp].tile[1][1] = '\u2501';
                mapGUI[iTemp][jTemp].tile[1][2] = '\u2501';
                lastPick = 'W';
            }
        } else if (direction.compareTo("east") == 0) {
            if (mapGUI[iTemp][jTemp + 1].tile[1][1] == '\u2588') {
                jTemp++;
                mapGUI[iTemp][jTemp].tile[1][0] = '\u2501';
                mapGUI[iTemp][jTemp].tile[1][1] = '\u2501';
                mapGUI[iTemp][jTemp].tile[1][2] = '\u2501';
                lastPick = 'E';
            }
        }

        if (i2 >= i1 && j2 > j1) {
            south = 475;
            east = 475;
        }
        else if (i2 <= i1 && j2 < j1) {
            north = 475;
            west = 475;
        }
        else if(i2 > i1 && j2 <= j1){
            south = 475;
            west = 475;
        }
        else if(i2 < i1 && j2 >= j1){
            north = 475;
            east = 475;
        }
        // </editor-fold>
        
        while (!connected) {
            iterationCount++;
            if(iterationCount > 100){
                return true;
            }
            if(iTemp == i2 && jTemp == j2){
                connected = true;
            }
            if(iTemp == i2){
                if(jTemp == j2 - adjust || jTemp == j2 + adjust){
                    if(jTemp == j2 - adjust){
                        if (lastPick == 'S') {
                            mapGUI[iTemp][jTemp].tile[1][1] = '\u2517';
                            mapGUI[iTemp][jTemp].tile[2][1] = '\u2588';
                            mapGUI[iTemp][jTemp].tile[1][2] = '\u2501';
                        } else if (lastPick == 'N') {
                            mapGUI[iTemp][jTemp].tile[1][1] = '\u250f';
                            mapGUI[iTemp][jTemp].tile[1][2] = '\u2501';
                            mapGUI[iTemp][jTemp].tile[0][1] = '\u2588';
                        }
                    } else if (jTemp == j2 + adjust) {
                        if (lastPick == 'S') {
                            mapGUI[iTemp][jTemp].tile[1][1] = '\u251b';
                            mapGUI[iTemp][jTemp].tile[1][0] = '\u2501';
                            mapGUI[iTemp][jTemp].tile[2][1] = '\u2588';
                        } else if (lastPick == 'N') {
                            mapGUI[iTemp][jTemp].tile[1][1] = '\u2513';
                            mapGUI[iTemp][jTemp].tile[1][0] = '\u2501';
                            mapGUI[iTemp][jTemp].tile[0][1] = '\u2588';
                        }
                    }
                    connected = true;
                } else if (jTemp < j2){
                    if (lastPick == 'S') {
                        mapGUI[iTemp][jTemp].tile[1][1] = '\u2517';
                        mapGUI[iTemp][jTemp].tile[2][1] = '\u2588';
                        mapGUI[iTemp][jTemp].tile[1][2] = '\u2501';
                    } else if (lastPick == 'N') {
                        mapGUI[iTemp][jTemp].tile[1][1] = '\u250f';
                        mapGUI[iTemp][jTemp].tile[1][2] = '\u2501';
                        mapGUI[iTemp][jTemp].tile[0][1] = '\u2588';
                    }
                    while (jTemp < j2 - adjust) {
                        jTemp++;
                        mapGUI[iTemp][jTemp].tile[1][0] = '\u2501';
                        mapGUI[iTemp][jTemp].tile[1][1] = '\u2501';
                        mapGUI[iTemp][jTemp].tile[1][2] = '\u2501';
                    }
                    connected = true;
                } else if (jTemp > j2){
                    if (lastPick == 'S') {
                        mapGUI[iTemp][jTemp].tile[1][1] = '\u251b';
                        mapGUI[iTemp][jTemp].tile[1][0] = '\u2501';
                        mapGUI[iTemp][jTemp].tile[2][1] = '\u2588';
                    } else if (lastPick == 'N') {
                        mapGUI[iTemp][jTemp].tile[1][1] = '\u2513';
                        mapGUI[iTemp][jTemp].tile[1][0] = '\u2501';
                        mapGUI[iTemp][jTemp].tile[0][1] = '\u2588';
                    }
                    while (jTemp > j2 + adjust) {
                        jTemp--;
                        mapGUI[iTemp][jTemp].tile[1][0] = '\u2501';
                        mapGUI[iTemp][jTemp].tile[1][1] = '\u2501';
                        mapGUI[iTemp][jTemp].tile[1][2] = '\u2501';
                    }
                    connected = true;
                }
            } else if (jTemp == j2){
                if (iTemp == i2 - adjust || iTemp == i2 + adjust){
                    if (iTemp == i2 - adjust) {
                        if (lastPick == 'E') {
                            mapGUI[iTemp][jTemp].tile[1][1] = '\u2513';
                            mapGUI[iTemp][jTemp].tile[2][1] = '\u2503';
                            mapGUI[iTemp][jTemp].tile[1][2] = '\u2588';
                        } else if (lastPick == 'W') {
                            mapGUI[iTemp][jTemp].tile[1][1] = '\u250f';
                            mapGUI[iTemp][jTemp].tile[2][1] = '\u2503';
                            mapGUI[iTemp][jTemp].tile[1][0] = '\u2588';
                        }
                    } else if (iTemp == i2 + adjust) {
                        if (lastPick == 'E') {
                            mapGUI[iTemp][jTemp].tile[1][1] = '\u251b';
                            mapGUI[iTemp][jTemp].tile[0][1] = '\u2503';
                            mapGUI[iTemp][jTemp].tile[1][2] = '\u2588';
                        } else if (lastPick == 'W') {
                            mapGUI[iTemp][jTemp].tile[1][1] = '\u2517';
                            mapGUI[iTemp][jTemp].tile[0][1] = '\u2503';
                            mapGUI[iTemp][jTemp].tile[1][0] = '\u2588';
                        }
                    }
                    connected = true;
                } else if (iTemp < i2){
                    if (lastPick == 'E') {
                        mapGUI[iTemp][jTemp].tile[1][1] = '\u2513';
                        mapGUI[iTemp][jTemp].tile[2][1] = '\u2503';
                        mapGUI[iTemp][jTemp].tile[1][2] = '\u2588';
                    } else if (lastPick == 'W') {
                        mapGUI[iTemp][jTemp].tile[1][1] = '\u250f';
                        mapGUI[iTemp][jTemp].tile[2][1] = '\u2503';
                        mapGUI[iTemp][jTemp].tile[1][0] = '\u2588';
                    }
                    while (iTemp < i2 - adjust) {
                        iTemp++;
                        mapGUI[iTemp][jTemp].tile[0][1] = '\u2503';
                        mapGUI[iTemp][jTemp].tile[1][1] = '\u2503';
                        mapGUI[iTemp][jTemp].tile[2][1] = '\u2503';                        
                    }
                    connected = true;
                } else if (iTemp > i2){
                    if (lastPick == 'E') {
                        mapGUI[iTemp][jTemp].tile[1][1] = '\u251b';
                        mapGUI[iTemp][jTemp].tile[0][1] = '\u2503';
                        mapGUI[iTemp][jTemp].tile[1][2] = '\u2588';
                    } else if (lastPick == 'W') {
                        mapGUI[iTemp][jTemp].tile[1][1] = '\u2517';
                        mapGUI[iTemp][jTemp].tile[0][1] = '\u2503';
                        mapGUI[iTemp][jTemp].tile[1][0] = '\u2588';
                    }
                    while (iTemp > i2 + adjust) {
                        iTemp--;
                        mapGUI[iTemp][jTemp].tile[0][1] = '\u2503';
                        mapGUI[iTemp][jTemp].tile[1][1] = '\u2503';
                        mapGUI[iTemp][jTemp].tile[2][1] = '\u2503';
                    }
                    connected = true;
                }
            } else {
                pick = 1 + rng.nextInt(1000);
                if (pick > 0 && pick <= south) {
                    if (iTemp + adjust < sizeX && mapGUI[iTemp + 1][jTemp].tile[1][1] == '\u2588' && lastPick != 'N') {
                        iTemp++;
                        if (lastPick == 'E') {
                            mapGUI[iTemp - 1][jTemp].tile[1][1] = '\u2513';
                            mapGUI[iTemp - 1][jTemp].tile[2][1] = '\u2503';
                            mapGUI[iTemp - 1][jTemp].tile[1][2] = '\u2588';
                        } else if (lastPick == 'W') {
                            mapGUI[iTemp - 1][jTemp].tile[1][1] = '\u250f';
                            mapGUI[iTemp - 1][jTemp].tile[2][1] = '\u2503';
                            mapGUI[iTemp - 1][jTemp].tile[1][0] = '\u2588';
                        }
                        mapGUI[iTemp][jTemp].tile[0][1] = '\u2503';
                        mapGUI[iTemp][jTemp].tile[1][1] = '\u2503';
                        mapGUI[iTemp][jTemp].tile[2][1] = '\u2503';
                        if (iTemp == i2 - adjust && jTemp == j2) {
                            connected = true;
                        }
                        lastPick = 'S';
                    }
                } else if (pick > south && pick <= south + north) {
                    if (iTemp - adjust >= 0 && mapGUI[iTemp - 1][jTemp].tile[1][1] == '\u2588' && lastPick != 'S') {
                        iTemp--;
                        if (lastPick == 'E') {
                            mapGUI[iTemp + 1][jTemp].tile[1][1] = '\u251b';
                            mapGUI[iTemp + 1][jTemp].tile[0][1] = '\u2503';
                            mapGUI[iTemp + 1][jTemp].tile[1][2] = '\u2588';
                        } else if (lastPick == 'W') {
                            mapGUI[iTemp + 1][jTemp].tile[1][1] = '\u2517';
                            mapGUI[iTemp + 1][jTemp].tile[0][1] = '\u2503';
                            mapGUI[iTemp + 1][jTemp].tile[1][0] = '\u2588';
                        }
                        mapGUI[iTemp][jTemp].tile[0][1] = '\u2503';
                        mapGUI[iTemp][jTemp].tile[1][1] = '\u2503';
                        mapGUI[iTemp][jTemp].tile[2][1] = '\u2503';
                        if (iTemp == i2 + adjust && jTemp == j2) {
                            connected = true;
                        }
                        lastPick = 'N';
                    }
                } else if (pick > south + north && pick <= south + north + east){
                    if (jTemp + adjust < sizeY && mapGUI[iTemp][jTemp + 1].tile[1][1] == '\u2588' && lastPick != 'W') {
                        jTemp++;
                        if (lastPick == 'S') {
                            mapGUI[iTemp][jTemp - 1].tile[1][1] = '\u2517';
                            mapGUI[iTemp][jTemp - 1].tile[2][1] = '\u2588';
                            mapGUI[iTemp][jTemp - 1].tile[1][2] = '\u2501';
                        } else if (lastPick == 'N') {
                            mapGUI[iTemp][jTemp - 1].tile[1][1] = '\u250f';
                            mapGUI[iTemp][jTemp - 1].tile[1][2] = '\u2501';
                            mapGUI[iTemp][jTemp - 1].tile[0][1] = '\u2588';
                        }
                        mapGUI[iTemp][jTemp].tile[1][0] = '\u2501';
                        mapGUI[iTemp][jTemp].tile[1][1] = '\u2501';
                        mapGUI[iTemp][jTemp].tile[1][2] = '\u2501';
                        if (iTemp == i2 && jTemp == j2 - adjust) {
                            connected = true;
                        } else if (iTemp == i2 + adjust && jTemp == j2) {
                            mapGUI[iTemp][jTemp].tile[1][1] = '\u251b';
                            mapGUI[iTemp][jTemp].tile[1][2] = '\u2588';
                            mapGUI[iTemp][jTemp].tile[0][1] = '\u2503';
                            connected = true;
                        } else if (iTemp == i2 - adjust && jTemp == j2){
                            mapGUI[iTemp][jTemp].tile[1][1] = '\u2513';
                            mapGUI[iTemp][jTemp].tile[2][1] = '\u2503';
                            mapGUI[iTemp][jTemp].tile[1][2] = '\u2588';
                            connected = true;
                        }
                        lastPick = 'E';
                    }
                } else if (pick > south + north + east && pick <= south + north + east + west){
                    if (jTemp - adjust >= 0 && mapGUI[iTemp][jTemp - 1].tile[1][1] == '\u2588' && lastPick != 'E') {
                        jTemp--;
                        if (lastPick == 'S') {
                            mapGUI[iTemp][jTemp + 1].tile[1][1] = '\u251b';
                            mapGUI[iTemp][jTemp + 1].tile[1][0] = '\u2501';
                            mapGUI[iTemp][jTemp + 1].tile[2][1] = '\u2588';
                        } else if (lastPick == 'N') {
                            mapGUI[iTemp][jTemp + 1].tile[1][1] = '\u2513';
                            mapGUI[iTemp][jTemp + 1].tile[1][0] = '\u2501';
                            mapGUI[iTemp][jTemp + 1].tile[0][1] = '\u2588';
                        }
                        mapGUI[iTemp][jTemp].tile[1][0] = '\u2501';
                        mapGUI[iTemp][jTemp].tile[1][1] = '\u2501';
                        mapGUI[iTemp][jTemp].tile[1][2] = '\u2501';
                        if (iTemp == i2 && jTemp == j2 + adjust) {
                            connected = true;
                        } else if(iTemp == i2 + adjust && jTemp == j2){
                            mapGUI[iTemp][jTemp].tile[1][1] = '\u2517';
                            mapGUI[iTemp][jTemp].tile[1][0] = '\u2588';
                            mapGUI[iTemp][jTemp].tile[0][1] = '\u2503';
                            connected = true;
                        } else if(iTemp == i2 - adjust && jTemp == j2){
                            mapGUI[iTemp][jTemp].tile[1][1] = '\u250f';
                            mapGUI[iTemp][jTemp].tile[1][0] = '\u2588';
                            mapGUI[iTemp][jTemp].tile[2][1] = '\u2503';
                            connected = true;
                        }
                        lastPick = 'W';
                    }
                }  
            }
            
        }
        return false;
    }
    private class Room {
        protected int roomNumber;
        protected int size;
        protected String type;
        protected int x,
                      y;
        protected int[] adjRooms = new int[4];
        protected double[] angle = new double[4];
        protected String[] directions = new String[4];
        
        public Room(){
            adjRooms[0] = -1;
            adjRooms[1] = -1;
            adjRooms[2] = -1;
            adjRooms[3] = -1;
            for (int i = 0; i < 4; i++) {
                angle[i] = -1;
                directions[i] = "null";
            }
        }
        
    }
    private class MapTile {
        protected char[][] tile = new char[3][3];
        protected boolean isRoom,
                          hasPlayer;
        
        public MapTile(){
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    tile[i][j] = '\u2588';
                }
            }
        }
    }
}