/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OneWayTrip;

import java.util.Random;

/**
 *
 * @author King of Ohio
 */
public class EventGenerator {
    
    private int outcome;
    private int memory;
    private final int ambush = 0,           //a fight 101-200
                      instance = 1,         //a dungeon 301-400
                      location = 2,         //a village 501-600
                      something = 3;        //free item, weird guy, rocks fall, etc 701-800
    private int ambushMin,
                ambushMax,
                instanceMin,
                instanceMax,
                locationMin,
                locationMax,
                somethingMin,
                somethingMax;
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
    
    public Event generateEvent(){
        spinWheel();
        //generate object based on outcome and return
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
}
