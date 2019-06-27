package OneWayTrip;

import java.util.Random;
import java.lang.Math;

/**
 * This will be used to generate instances for the player to
 * explore. They will be procedural and can be varying lengths 
 * and difficulties.
 * @author Alex Conway
 * @version 1.0 
 */
public class Instance extends Event {
    private int roomCount = 0;
    private int size = 5;
    private int sizeX = 10;
    private int sizeY = 10;
    private int[] s;
    protected int[][] map = new int[sizeX][sizeY];
    protected MapTile[][] mapGUI;
    private Random rng = new Random();
    protected Room[] rooms;

    public Instance(){
        //provide a size value, which will set the variables to the size values needed
    }
    public void generateRoomLayout(){
        int pick;
        boolean diag;
        while (roomCount < size) {
            for (int i = 1; i < sizeX-1; i++) {
                for (int j = 1; j < sizeY-1; j++) {
                    if (roomCount < 15) {
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
                                    //System.out.println("made room");
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
    public void combineAdjacentRooms(int maxI, int maxJ){
        int rC = 0;
        for(int i = 0; i < maxI; i++){
            for(int j = 0; j < maxJ; j++){
                if(map[i][j] == 1){
                    //check all four wall sharing cells, make sure no out of bounds access
                    if(i-1 >= 0 && map[i-1][j] == 1){
                        map[i][j] += 1;
                        map[i-1][j] = 0;
                    }
                    if(i+1 <= maxI-1 && map[i+1][j] == 1){
                        map[i][j] += 1;
                        map[i+1][j] = 0;
                    }
                    if(j-1 >= 0 && map[i][j-1] == 1){
                        map[i][j] += 1;
                        map[i][j-1] = 0;
                    }
                    if(j+1 <= maxJ-1 && map[i][j+1] == 1){
                        map[i][j] += 1;
                        map[i][j+1] = 0;
                    }
                    rC++;
                }
            }
        }
        rooms = new Room[rC];
    } 
    public void generateRoomStruct(int maxI, int maxJ){
        int rCount = 0;
        for(int i = 0; i < maxI; i++){
            for(int j = 0; j < maxJ; j++){
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
    public void generateConnections(){
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
        boolean collision = false;
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
        /*
        for(int i = 0; i < rooms.length; i++){
            System.out.print("Room num: " + rooms[i].roomNumber + " Adj Rms: "
                    + rooms[i].adjRooms[0] + " " + rooms[i].adjRooms[1] + " "
                    + rooms[i].adjRooms[2] + " " + rooms[i].adjRooms[3] + "\n");
        }*/
        
        
        //arbitrarily pick a room to test what half of map not connected to other half
        pick = rng.nextInt(rooms.length);
        s[pick] = explore(pick);
        connected = true;
        for(int i = 0; i < s.length; i++){
            if(s[i] == 0){
                connected = false;
            }
        }
        //display array after first run through
        /*
        for(int i = 0; i < s.length; i++){
            System.out.print(s[i] + " ");
        }*/
        //System.out.println();
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
        //display effects
        /*
        for(int i = 0; i < s.length; i++){
            s[i] = 0;
        }*/
        pick = rng.nextInt(rooms.length);
        s[pick] = explore(pick);
        /*
        for(int i = 0; i < s.length; i++){
            System.out.print(s[i] + " ");
        }*/
        //System.out.println();
        
        
        //decide whether rooms should have additional connections and to what other rooms
        //arbitraryConnections();
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
    public void generateMap(){
        int k = 0;
        mapGUI = new MapTile[sizeX][sizeY];
        //create room tiles
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
                else {
                    mapGUI[i][j] = new MapTile();
                }
            }
        }
        connectRooms();
    }
    private int pickRoom(int k){
        //set s[i] = 1, ie set visited
        //k(x1,y1), i(x2,y2)
        int closestRoom = k;
        double distPrev = 10000;
        double distCurr;
        s[k] = 1;
        //scan unvisited rooms and determine distance from current
        for(int i = 0; i < s.length; i++){
            if(s[i] == 0){
                distCurr = Math.sqrt(Math.pow(rooms[i].x - rooms[k].x, 2) + Math.pow(rooms[i].y - rooms[k].y, 2));
                if(distCurr < distPrev){
                    closestRoom = i;
                    distPrev = distCurr;
                }
            }
        }
        //with no unvisited rooms, closestRoom should remain unchanged, this will break the recursion
        if(closestRoom == k){
            return 0;
        }
        //otherwise, find the room closest to this rooms closest
        else{
            rooms[closestRoom].adjRooms[0] = pickRoom(closestRoom);
            return closestRoom;
        }        
    }
    private void collisionClean(){
        boolean collision;
        for(int i = 0; i < rooms.length; i++){
            for(int j = 0; j < 4; j++){
                if(rooms[i].adjRooms[j] != -1){
                    for(int m = 0; m < rooms.length; m++){
                        for(int n = 0; n < 4; n++){
                            if(i != m && rooms[i].adjRooms[j] != rooms[m].adjRooms[n] && rooms[m].adjRooms[n] != -1){
                                collision = checkIntersection(i, rooms[i].adjRooms[j], m, rooms[m].adjRooms[n]);
                                if(collision){
                                    for(int k = 0; k < 4; k++){
                                        if(rooms[rooms[m].adjRooms[n]].adjRooms[k] == rooms[m].adjRooms[n]){
                                            rooms[rooms[m].adjRooms[n]].adjRooms[k] = -1;
                                            break;
                                        }
                                    }
                                    rooms[m].adjRooms[n] = -1;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private void arbitraryConnections(){
        int pick,
            con1 = 0,
            con2 = 0;
        boolean intersect,
                collision = false;
        for(int i = 0; i < rooms.length; i++){
            pick = 1 + rng.nextInt(10);
            //room will have additional path
            if(pick >= 6){
                //insert choose 1 or 2 more paths
                pick = 0 + rng.nextInt(rooms.length);
                while(pick == i || pick == rooms[i].adjRooms[0] || pick == rooms[i].adjRooms[1]){
                    pick = 0 + rng.nextInt(rooms.length);
                }
                //scan other room connection combos for a collision
                for(int j = 0; j < rooms.length; j++){
                    for (int k = 0; k < rooms.length; k++){
                        if((j != i && k != pick) || (k != i && j != pick)){
                            //determine if an intersection occurs then see if connection exists to collide with
                            intersect = checkIntersection(i, pick, j, k);
                            if(intersect){
                                collision = rooms[j].adjRooms[0] == k || rooms[k].adjRooms[0] == j;
                            }
                        }
                    }
                }
            }
            if(!collision){
                for(int j = 2; j < 4; j++){
                    if(rooms[i].adjRooms[j] == -1){
                        con1 = j;
                    }
                }
                for(int j = 2; j < 4; j++){
                    if(rooms[pick].adjRooms[j] == -1){
                        con2 = j;
                    }
                }
                if(rooms[i].adjRooms[con1] == -1 && rooms[pick].adjRooms[con2] == -1){
                    rooms[i].adjRooms[con1] = pick;
                    rooms[pick].adjRooms[con2] = i;
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
    private void connectRooms(){
        int[][] builtPaths = new int[rooms.length*2][2];
        int[] toBuild = new int[4];
        int adjC = 0;
        int x1, x2, y1, y2;
        double[] angle = new double[4];
        String[] directions = new String[4];
        int k = 0;
        boolean built = false;
        boolean collision = false;
        //initialize for control
        for(int i = 0; i < 4; i++){
            angle[i] = -1;
        }
        for(int i = 0; i < rooms.length*2; i++){
            for(int j = 0; j < 2; j++){
                builtPaths[i][j] = -1;
            }
        }
        for (int m = 0; m < 4; m++) {
            directions[m] = "null";
        }
        
        //for each possible amount of rooms
        for(int i = 4; i > 0; i--){
            //for each room
            for(int j = 0; j < rooms.length; j++){
                
                //for each adj room
                for(int m = 0; m < 4; m++){
                    if(rooms[j].adjRooms[m] != -1){
                        double ax = rooms[j].x,
                               ay = rooms[j].y,
                               bx = rooms[rooms[j].adjRooms[m]].x,
                               by = rooms[rooms[j].adjRooms[m]].y;
                        angle[m] = Math.toDegrees(Math.atan2(by - ay, bx - ax) + Math.PI);
                        adjC++;
                        if (angle[m] < 0) {
                            angle[m] += 360;
                        }
                    }
                    else {
                        angle[m] = -1;
                    }
                }
                if (adjC == i) {
                    //System.out.println(j);
                    //build connections logic
                    for(int m = 0; m < 4; m++){
                        if(angle[m] > 45 && angle[m] < 135){
                            directions[m] = "west";
                        }
                        else if (angle[m] > 135 && angle[m] < 225){
                            directions[m] = "south";
                        }
                        else if (angle[m] > 225 && angle[m] < 315){
                            directions[m] = "east";
                        }
                        else if ((angle[m] > 315 || angle[m] < 45) && angle[m] != -1){
                            directions[m] = "north";
                        }
                        else if (angle[m] == 45){
                            directions[m] = "north west";
                        } 
                        else if (angle[m] == 135){
                            directions[m] = "south west";
                        }
                        else if (angle[m] == 225){
                            directions[m] = "south east";
                        }
                        else if (angle[m] == 315){
                            directions[m] = "north east";
                        }
                        else {
                            directions[m] = "null";
                        }
                    }
                    
                    for(int m = 0; m < 4; m++){
                        //System.out.println(directions[m]);
                        for(int n = 0; n < 0; n++){
                            if(directions[m].compareTo(directions[n]) == 0){
                                System.out.println("BIG FUCKUS");
                            }
                        }
                    }
                    //System.out.println("end room " + i);
                    //for each adj room
                    for (int m = 0; m < 4; m++) {
                        for (int n = 0; n < builtPaths.length; n++) {
                            if (builtPaths[n][0] == rooms[j].roomNumber && builtPaths[n][1] == rooms[j].adjRooms[m]) {
                                built = true;
                                break;
                            } else if (builtPaths[n][1] == rooms[j].roomNumber && builtPaths[n][0] == rooms[j].adjRooms[m]) {
                                built = true;
                                break;
                            } else {
                                built = false;
                            }
                        }
                        if (!built && rooms[j].adjRooms[m] != -1) {
                            x1 = rooms[j].x;
                            y1 = rooms[j].y;
                            x2 = rooms[rooms[j].adjRooms[m]].x;
                            y2 = rooms[rooms[j].adjRooms[m]].y;
                            //System.out.println("building path " + j + " " + rooms[j].adjRooms[m]);
                            //buildPath(x1, x2, y1, y2);
                            builtPaths[k][0] = rooms[j].roomNumber;
                            builtPaths[k][1] = rooms[j].adjRooms[m];
                            k++;
                            built = false; //reset
                        }
                    }
                }
                adjC = 0;
                for (int m = 0; m < 4; m++) {
                    angle[m] = -1;
                }
                for (int m = 0; m < 4; m++) {
                    directions[m] = "null";
                }
            }
        }
    }
    private void buildPath(int i1, int i2, int j1, int j2){
        int iTemp = i1, 
            jTemp = j1;
        int flip;
        char lastFlip = ' ';
        int north = 0,
            south = 0,
            east = 0,
            west = 0;
        boolean connected = false;
        
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
 
        while (!connected) {
            if(lastFlip == ' '){
                if(iTemp == i2 && jTemp < j2){
                    while(jTemp < j2 - 1){
                        if (mapGUI[iTemp][jTemp + 1].tile[1][1] == '\u2588') {
                            jTemp++;
                            mapGUI[iTemp][jTemp].tile[1][0] = '\u2501';
                            mapGUI[iTemp][jTemp].tile[1][1] = '\u2501';
                            mapGUI[iTemp][jTemp].tile[1][2] = '\u2501';
                            if (jTemp == j2 - 1) {
                                break;
                            }
                        }
                        else {
                            lastFlip = 'E';
                            break;
                        }
                    }
                    connected = true;
                }
                else if (iTemp == i2 && jTemp > j2){
                    while (jTemp > j2 + 1) {
                        if (mapGUI[iTemp][jTemp - 1].tile[1][1] == '\u2588') {
                            jTemp--;
                            mapGUI[iTemp][jTemp].tile[1][0] = '\u2501';
                            mapGUI[iTemp][jTemp].tile[1][1] = '\u2501';
                            mapGUI[iTemp][jTemp].tile[1][2] = '\u2501';
                            if (jTemp == j2 + 1) {
                                break;
                            }
                        }
                        else {
                            lastFlip = 'W';
                            break;
                        }
                    }
                    connected = true;
                }
                else if (iTemp > i2 && jTemp == j2){
                    while (iTemp > i2 + 1) {
                        if (mapGUI[iTemp - 1][jTemp].tile[1][1] == '\u2588') {
                            iTemp--;
                            mapGUI[iTemp][jTemp].tile[0][1] = '\u2503';
                            mapGUI[iTemp][jTemp].tile[1][1] = '\u2503';
                            mapGUI[iTemp][jTemp].tile[2][1] = '\u2503';
                            if (iTemp == i2 + 1) {
                                break;
                            }
                        }
                        else{
                            lastFlip = 'S';
                        }
                    }
                    connected = true;
                }
                else if (iTemp < i2 && jTemp == j2){
                    while (iTemp < i2 - 1) {
                        if (mapGUI[iTemp + 1][jTemp].tile[1][1] == '\u2588') {
                            iTemp++;
                            mapGUI[iTemp][jTemp].tile[0][1] = '\u2503';
                            mapGUI[iTemp][jTemp].tile[1][1] = '\u2503';
                            mapGUI[iTemp][jTemp].tile[2][1] = '\u2503';
                            if (iTemp == i2 - 1) {
                                break;
                            }
                        }
                        else {
                            lastFlip = 'N';
                            break;
                        }
                    }
                    connected = true;
                }
                else {
                    lastFlip = 'x';
                }
            }
            else if (lastFlip == 'S' && iTemp == i2) {
                System.out.println("Immediately next to each other from S");
                //if the last choice was south, and we only want to go east or west, adjust path
                if (iTemp == i2 && jTemp < j2 && mapGUI[iTemp][jTemp + 1].tile[1][1] == '\u2588') {
                    mapGUI[iTemp][jTemp].tile[0][1] = '\u2503';
                    mapGUI[iTemp][jTemp].tile[1][1] = '\u2517';
                    mapGUI[iTemp][jTemp].tile[2][1] = '\u2588';
                    mapGUI[iTemp][jTemp].tile[1][2] = '\u2501';
                    while (jTemp < j2 - 1) {
                        if (mapGUI[iTemp][jTemp + 1].tile[1][1] == '\u2588') {
                            jTemp++;
                            mapGUI[iTemp][jTemp].tile[1][0] = '\u2501';
                            mapGUI[iTemp][jTemp].tile[1][1] = '\u2501';
                            mapGUI[iTemp][jTemp].tile[1][2] = '\u2501';
                            if (jTemp == j2 - 1) {
                                connected = true;
                                break;
                            }
                        } else {
                            lastFlip = 'E';
                            break;
                        }
                    }
                    if (jTemp == j2 - 1) {
                        connected = true;
                    }
                } else if (iTemp == i2 && jTemp > j2 && mapGUI[iTemp][jTemp - 1].tile[1][1] == '\u2588') {
                    mapGUI[iTemp][jTemp].tile[0][1] = '\u2503';
                    mapGUI[iTemp][jTemp].tile[1][1] = '\u251b';
                    mapGUI[iTemp][jTemp].tile[1][0] = '\u2501';
                    mapGUI[iTemp][jTemp].tile[2][1] = '\u2588';
                    while (jTemp > j2 + 1) {
                        if (mapGUI[iTemp][jTemp - 1].tile[1][1] == '\u2588') {
                            jTemp--;
                            mapGUI[iTemp][jTemp].tile[1][0] = '\u2501';
                            mapGUI[iTemp][jTemp].tile[1][1] = '\u2501';
                            mapGUI[iTemp][jTemp].tile[1][2] = '\u2501';
                            if (jTemp == j2 + 1) {
                                connected = true;
                                break;
                            }
                        } else {
                            lastFlip = 'W';
                            break;
                        }
                    }
                    if (jTemp == j2 + 1) {
                        connected = true;
                    }
                } else {
                    connected = true;
                }
            } else if (lastFlip == 'N' && iTemp == i2) {
                System.out.println("Immediately next to each other N");
                if (iTemp == i2 && jTemp < j2 && mapGUI[iTemp][jTemp + 1].tile[1][1] == '\u2588') {
                    mapGUI[iTemp][jTemp].tile[1][1] = '\u250f';
                    mapGUI[iTemp][jTemp].tile[1][2] = '\u2501';
                    mapGUI[iTemp][jTemp].tile[0][1] = '\u2588';
                    while (jTemp < j2 - 1) {
                        if (mapGUI[iTemp][jTemp + 1].tile[1][1] == '\u2588') {
                            jTemp++;
                            mapGUI[iTemp][jTemp].tile[1][0] = '\u2501';
                            mapGUI[iTemp][jTemp].tile[1][1] = '\u2501';
                            mapGUI[iTemp][jTemp].tile[1][2] = '\u2501';
                            if (jTemp == j2 - 1) {
                                connected = true;
                                break;
                            }
                        } else {
                            lastFlip = 'E';
                            break;
                        }
                    }
                    if (jTemp == j2 - 1) {
                        connected = true;
                    }
                } else if (iTemp == i2 && jTemp > j2 && mapGUI[iTemp][jTemp - 1].tile[1][1] == '\u2588') {
                    mapGUI[iTemp][jTemp].tile[1][1] = '\u2513';
                    mapGUI[iTemp][jTemp].tile[1][0] = '\u2501';
                    mapGUI[iTemp][jTemp].tile[0][1] = '\u2588';
                    while (jTemp > j2 + 1) {
                        if (mapGUI[iTemp][jTemp - 1].tile[1][1] == '\u2588') {
                            jTemp--;
                            mapGUI[iTemp][jTemp].tile[1][0] = '\u2501';
                            mapGUI[iTemp][jTemp].tile[1][1] = '\u2501';
                            mapGUI[iTemp][jTemp].tile[1][2] = '\u2501';
                            if (jTemp == j2 + 1) {
                                connected = true;
                                break;
                            }
                        } else {
                            lastFlip = 'W';
                            break;
                        }
                    }
                    if (jTemp == j2 + 1) {
                        connected = true;
                    }
                } else {
                    connected = true;
                }
            } else if (lastFlip == 'E' && jTemp == j2 && mapGUI[iTemp + 1][jTemp].tile[1][1] == '\u2588') {
                System.out.println("Immediately next to each other E");
                if (jTemp == j2 && iTemp > i2) {
                    mapGUI[iTemp][jTemp].tile[1][1] = '\u251b';
                    mapGUI[iTemp][jTemp].tile[0][1] = '\u2503';
                    mapGUI[iTemp][jTemp].tile[1][2] = '\u2588';
                    while (iTemp > i2 + 1) {
                        if (mapGUI[iTemp - 1][jTemp].tile[1][1] == '\u2588') {
                            iTemp--;
                            mapGUI[iTemp][jTemp].tile[0][1] = '\u2503';
                            mapGUI[iTemp][jTemp].tile[1][1] = '\u2503';
                            mapGUI[iTemp][jTemp].tile[2][1] = '\u2503';
                            if (iTemp == i2 + 1) {
                                connected = true;
                                break;
                            }
                        } else {
                            lastFlip = 'S';
                            break;
                        }
                    }
                    if (iTemp == i2 + 1) {
                        connected = true;
                    }
                } else if (jTemp == j2 && iTemp < i2 && mapGUI[iTemp + 1][jTemp].tile[1][1] == '\u2588') {
                    mapGUI[iTemp][jTemp].tile[1][1] = '\u2513';
                    mapGUI[iTemp][jTemp].tile[2][1] = '\u2503';
                    mapGUI[iTemp][jTemp].tile[1][2] = '\u2588';
                    while (iTemp < i2 - 1) {
                        if (mapGUI[iTemp + 1][jTemp].tile[1][1] == '\u2588') {
                            iTemp++;
                            mapGUI[iTemp][jTemp].tile[0][1] = '\u2503';
                            mapGUI[iTemp][jTemp].tile[1][1] = '\u2503';
                            mapGUI[iTemp][jTemp].tile[2][1] = '\u2503';
                            if (iTemp == i2 - 1) {
                                connected = true;
                                break;
                            }
                        } else {
                            lastFlip = 'N';
                            break;
                        }
                    }
                    if (iTemp == i2 - 1) {
                        connected = true;
                    }
                } else {
                    connected = true;
                }
            } else if (lastFlip == 'W' && jTemp == j2) {
                System.out.println("Immediately next to each other W");
                if (jTemp == j2 && iTemp > i2 && mapGUI[iTemp - 1][jTemp].tile[1][1] == '\u2588') {
                    mapGUI[iTemp][jTemp].tile[1][1] = '\u2517';
                    mapGUI[iTemp][jTemp].tile[0][1] = '\u2503';
                    mapGUI[iTemp][jTemp].tile[1][0] = '\u2588';
                    while (iTemp > i2 + 1) {
                        if (mapGUI[iTemp - 1][jTemp].tile[1][1] == '\u2588') {
                            iTemp--;
                            mapGUI[iTemp][jTemp].tile[0][1] = '\u2503';
                            mapGUI[iTemp][jTemp].tile[1][1] = '\u2503';
                            mapGUI[iTemp][jTemp].tile[2][1] = '\u2503';
                        } else {
                            lastFlip = 'S';
                            break;
                        }
                    }
                    if (iTemp == i2 + 1) {
                        connected = true;
                    }
                } else if (jTemp == j2 && iTemp < i2 && mapGUI[iTemp + 1][jTemp].tile[1][1] == '\u2588') {
                    mapGUI[iTemp][jTemp].tile[1][1] = '\u250f';
                    mapGUI[iTemp][jTemp].tile[2][1] = '\u2503';
                    mapGUI[iTemp][jTemp].tile[1][0] = '\u2588';
                    while (iTemp < i2 - 1) {
                        if (mapGUI[iTemp + 1][jTemp].tile[1][1] == '\u2588') {
                            iTemp++;
                            mapGUI[iTemp][jTemp].tile[0][1] = '\u2503';
                            mapGUI[iTemp][jTemp].tile[1][1] = '\u2503';
                            mapGUI[iTemp][jTemp].tile[2][1] = '\u2503';
                        } else {
                            lastFlip = 'N';
                            break;
                        }
                    }
                    if (iTemp == i2 - 1) {
                        connected = true;
                    }
                } else {
                    if (iTemp == i2 + 1) {
                        mapGUI[iTemp][jTemp].tile[1][1] = '\u2517';
                        mapGUI[iTemp][jTemp].tile[0][1] = '\u2503';
                        mapGUI[iTemp][jTemp].tile[1][0] = '\u2588';
                    } else if (iTemp == i2 - 1) {
                        mapGUI[iTemp][jTemp].tile[1][1] = '\u250f';
                        mapGUI[iTemp][jTemp].tile[2][1] = '\u2503';
                        mapGUI[iTemp][jTemp].tile[1][0] = '\u2588';
                    }
                    connected = true;
                }
            } else {
                System.out.println("Must choose direction.");
                flip = 1 + rng.nextInt(1000);
                if (flip > 0 && flip <= south) {
                    //move south
                    System.out.println("chose S");
                    if (iTemp + 1 < sizeX && mapGUI[iTemp + 1][jTemp].tile[1][1] == '\u2588' && lastFlip != 'N') {
                        iTemp++;
                        if (lastFlip == 'E') {
                            mapGUI[iTemp - 1][jTemp].tile[1][1] = '\u2513';
                            mapGUI[iTemp - 1][jTemp].tile[2][1] = '\u2503';
                            mapGUI[iTemp - 1][jTemp].tile[1][2] = '\u2588';
                        } else if (lastFlip == 'W') {
                            mapGUI[iTemp - 1][jTemp].tile[1][1] = '\u250f';
                            mapGUI[iTemp - 1][jTemp].tile[2][1] = '\u2503';
                            mapGUI[iTemp - 1][jTemp].tile[1][0] = '\u2588';
                        }
                        mapGUI[iTemp][jTemp].tile[0][1] = '\u2503';
                        mapGUI[iTemp][jTemp].tile[1][1] = '\u2503';
                        mapGUI[iTemp][jTemp].tile[2][1] = '\u2503';
                        if (iTemp == i2 - 1 && jTemp == j2) {
                            connected = true;
                            System.out.println("should escape south");
                        }
                        lastFlip = 'S';
                    }
                } else if (flip > south && flip <= south + north) {
                    //move north
                    System.out.println("chose N");
                    if (iTemp - 1 >= 0 && mapGUI[iTemp - 1][jTemp].tile[1][1] == '\u2588' && lastFlip != 'S') {
                        iTemp--;
                        if (lastFlip == 'E') {
                            mapGUI[iTemp + 1][jTemp].tile[1][1] = '\u251b';
                            mapGUI[iTemp + 1][jTemp].tile[0][1] = '\u2503';
                            mapGUI[iTemp + 1][jTemp].tile[1][2] = '\u2588';
                        } else if (lastFlip == 'W') {
                            mapGUI[iTemp + 1][jTemp].tile[1][1] = '\u2517';
                            mapGUI[iTemp + 1][jTemp].tile[0][1] = '\u2503';
                            mapGUI[iTemp + 1][jTemp].tile[1][0] = '\u2588';
                        }
                        mapGUI[iTemp][jTemp].tile[0][1] = '\u2503';
                        mapGUI[iTemp][jTemp].tile[1][1] = '\u2503';
                        mapGUI[iTemp][jTemp].tile[2][1] = '\u2503';
                        if (iTemp == i2 + 1 && jTemp == j2) {
                            connected = true;
                            System.out.println("should escape north");
                        }
                        lastFlip = 'N';
                    }
                } else if (flip > south + north && flip <= south + north + east) {
                    //move east
                    System.out.println("chose E");
                    if (jTemp + 1 < sizeY && mapGUI[iTemp][jTemp + 1].tile[1][1] == '\u2588' && lastFlip != 'W') {
                        jTemp++;
                        if (lastFlip == 'S') {
                            mapGUI[iTemp][jTemp - 1].tile[1][1] = '\u2517';
                            mapGUI[iTemp][jTemp - 1].tile[2][1] = '\u2588';
                            mapGUI[iTemp][jTemp - 1].tile[1][2] = '\u2501';
                        } else if (lastFlip == 'N') {
                            mapGUI[iTemp][jTemp - 1].tile[1][1] = '\u250f';
                            mapGUI[iTemp][jTemp - 1].tile[1][2] = '\u2501';
                            mapGUI[iTemp][jTemp - 1].tile[0][1] = '\u2588';
                        }
                        mapGUI[iTemp][jTemp].tile[1][0] = '\u2501';
                        mapGUI[iTemp][jTemp].tile[1][1] = '\u2501';
                        mapGUI[iTemp][jTemp].tile[1][2] = '\u2501';
                        if (iTemp == i2 && jTemp == j2 - 1) {
                            connected = true;
                            System.out.println("should escape east");
                        } else if (iTemp == i2 - 1 && jTemp == j2) {
                            connected = true;
                        }
                        lastFlip = 'E';
                    }
                } else if (flip > south + north + east && flip <= south + north + east + west) {
                    //move west
                    System.out.println("chose W");
                    if (jTemp - 1 >= 0 && mapGUI[iTemp][jTemp - 1].tile[1][1] == '\u2588' && lastFlip != 'E') {
                        jTemp--;
                        if (lastFlip == 'S') {
                            mapGUI[iTemp][jTemp + 1].tile[1][1] = '\u251b';
                            mapGUI[iTemp][jTemp + 1].tile[1][0] = '\u2501';
                            mapGUI[iTemp][jTemp + 1].tile[2][1] = '\u2588';
                        } else if (lastFlip == 'N') {
                            mapGUI[iTemp][jTemp + 1].tile[1][1] = '\u2513';
                            mapGUI[iTemp][jTemp + 1].tile[1][0] = '\u2501';
                            mapGUI[iTemp][jTemp + 1].tile[0][1] = '\u2588';
                        }
                        mapGUI[iTemp][jTemp].tile[1][0] = '\u2501';
                        mapGUI[iTemp][jTemp].tile[1][1] = '\u2501';
                        mapGUI[iTemp][jTemp].tile[1][2] = '\u2501';
                        if (iTemp == i2 && jTemp == j2 + 1) {
                            connected = true;
                            System.out.println("should escape west");
                        }
                        lastFlip = 'W';
                    }
                }
            }

        }
    }
    protected class Room {
        protected int roomNumber;
        protected int size;
        protected String type;
        protected int x,
                      y;
        protected int[] adjRooms = new int[4];
        protected int[][] reserves = new int[4][3]; //room #, x, y for each possible adj room
        
        public Room(){
            adjRooms[0] = -1;
            adjRooms[1] = -1;
            adjRooms[2] = -1;
            adjRooms[3] = -1;
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 3; j++){
                    reserves[i][j] = -1;
                }
            }
        }
        
    }
    protected class MapTile {
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