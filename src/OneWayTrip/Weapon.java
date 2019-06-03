

package OneWayTrip;


/**
 *
 * @author Alex Conway
 * @version 1.0 
 */
public class Weapon extends Equipment {
   
    private int minDam,
                maxDam;
    
    //private String effect1,
    //               effect2;
    
    private boolean twoHanded;
    
    public Weapon(String name, String desc, int i, int a, boolean t, String e1, String e2){
        this.setName(name);
        this.setDesc(desc);
        minDam = i;
        maxDam = a;
        twoHanded = t;
        this.setEff1(e1);
        this.setEff2(e2);
        this.setSlot(8);
    }
    
    @Override
    public int getMinDam(){
        return minDam;
    }
    @Override
    public int getMaxDam(){
        return maxDam;
    }
    @Override
    public boolean get2H(){
        return twoHanded;
    }
    
}
