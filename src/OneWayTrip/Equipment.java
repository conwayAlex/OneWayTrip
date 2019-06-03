

package OneWayTrip;

/**
 *
 * @author Alex Conway
 * @version 1.0 
 */

public class Equipment extends Item {
    
    private int slot;
    private String effect1,
                   effect2;
    
    public void setSlot(int s){
        slot = s;
    }
    public int getSlot(){
        return slot;
    }
    //Weapon
    public int getMinDam(){
        return 0;
    }
    public int getMaxDam(){
        return 0;
    }
    public boolean get2H(){
        return true;
    }
    //Armor
    public int getAR(){
        return 0;
    }
    public int getEV(){
        return 0;
    }
    public int getSR(){
        return 0;
    }
    //Off Hand
    public int getBL(){
        return 0;
    }
    public int getPA(){
        return 0;
    }
    public int getSC(){
        return 0;
    }
    //Effect
    public void setEff1(String e){
        effect1 = e;
    }
    public void setEff2(String e){
        effect2 = e;
    }
    public String getEff1(){
        return effect1;
    }
    public String getEff2(){
        return effect2;
    }
    
    
    
}
