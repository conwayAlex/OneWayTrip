/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OneWayTrip;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author King of Ohio
 */
public class EventGenerator {
    
    private int playerLevel;
    private int[] eCountPerLvl = new int[] {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private int outcome;
    private int memory;
    private final int ambush = 0,           //a fight
                      instance = 1,         //a dungeon
                      location = 2,         //a village
                      something = 3;        //free item, weird guy, rocks fall, etc
    private int ambushMin,
                ambushMax,
                instanceMin,
                instanceMax,
                locationMin,
                locationMax,
                somethingMin,
                somethingMax;
    private ItemGenerator iGen = new ItemGenerator();
    private Random rng = new Random();
    private boolean valid = false;
    
    public EventGenerator(){
        ambushMin = 400;
        ambushMax = 600;
        instanceMin = 1400;
        instanceMax = 1600;
        locationMin = 2400;
        locationMax = 2600;
        somethingMin = 3400;
        somethingMax = 3600;
    }
    public void setLevel(int l){
        playerLevel = l;
    }
    public Event generateEvent(){
        spinWheel();
        
        return null;
    }
    public void spinWheel(){
        valid = false;
        while(valid == false){
            outcome = ambushMin + rng.nextInt(somethingMax);
            if(outcome >= ambushMin && outcome <= ambushMax){
                //gen ambush
                System.out.println("...");
                memory = ambush;
                valid = true;
                ambushMin += 20;
                ambushMax -= 20;
                instanceMin -= 20;
                instanceMax += 20;
                locationMin -= 10;
                locationMax += 10;
                somethingMin -= 20;
                somethingMax += 20;
            }
            else if(outcome >= instanceMin && outcome <= instanceMax){
                //gen instance
                System.out.println("...");
                memory = instance;
                valid = true;
                ambushMin -= 20;
                ambushMax += 20;
                instanceMin += 20;
                instanceMax -= 20;
                locationMin -= 10;
                locationMax += 10;
                somethingMin -= 20;
                somethingMax += 20;
            }   
            else if (outcome >= locationMin && outcome <= locationMax){
                if(memory != location){
                    //get location
                    System.out.println("location");
                    memory = location;
                    valid = true;
                    ambushMin -= 30;
                    ambushMax += 30;
                    instanceMin -= 30;
                    instanceMax += 30;
                    locationMin += 30;
                    locationMax -= 30;
                    somethingMin -= 30;
                    somethingMax += 30;
                }
            }
            else if (outcome >= somethingMin && outcome <= somethingMax) {
                //gen something
                System.out.println("...");
                memory = something;
                valid = true;
                ambushMin -= 20;
                ambushMax += 20;
                instanceMin -= 20;
                instanceMax += 20;
                locationMin -= 10;
                locationMax += 10;
                somethingMin += 20;
                somethingMax -= 20;
            }
        }
    }
    public Enemy makeAmbush(){
        int count = eCountPerLvl[playerLevel-1];
        int pick = 0 + rng.nextInt(count-1);
        int offset = 0;
        for(int i = 0; i < playerLevel-1; i++){
            offset += eCountPerLvl[i];
        }
        offset = offset *12;
        pick = pick*12;
        
        try(FileReader reader = new FileReader("resources\\enemies.txt")){
            BufferedReader bR = new BufferedReader(reader);
            for(int i = 0; i <offset+pick;i++){
                bR.readLine();
            }
            bR.readLine();
            Enemy e = new Enemy(bR.readLine(),
                    bR.readLine(),
                    iGen.generateLoot(playerLevel),
                    iGen.generateGold(playerLevel));
            e.setStats(Integer.parseInt(bR.readLine()),
                    Integer.parseInt(bR.readLine()),
                    Integer.parseInt(bR.readLine()));
            e.setAllRes(Integer.parseInt(bR.readLine()),
                    Integer.parseInt(bR.readLine()),
                    Integer.parseInt(bR.readLine()),
                    Integer.parseInt(bR.readLine()),
                    Integer.parseInt(bR.readLine()),
                    Integer.parseInt(bR.readLine()));
            return e;
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public Event testGen(){
        Enemy e = new Enemy("Lame Jobber", "human", null, 0);
        e.setStats(20, 0, 0);
        e.setAllRes(0, 0, 0, 0, 0, 0);
        e.setDamage(1, 5);
        Combat c = new Combat(e);
        return c;
    }
}
