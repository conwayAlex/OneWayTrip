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
public class Action {
    private String name,
                   desc;
    
    public void setName(String n){
        name = n;
    }
    public void setDesc(String d){
        desc = d;
    }
    public String getName(){
        return name;
    }
    public String getDesc(){
        return desc;
    }
}
