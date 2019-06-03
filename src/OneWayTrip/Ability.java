/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OneWayTrip;

/**
 *
 * @author King of Ohio
 */
public class Ability extends Action {
    private int spCost;
    
    public Ability(String name, String desc){
        this.setName(name);
        this.setDesc(desc);
    }
    
}
