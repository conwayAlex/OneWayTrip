

package OneWayTrip;

/**
 *
 * @author Alex Conway
 * @version 1.0 
 */
public class OffHand extends Equipment {

    private int block,
                parry,
                spellCounter;
    
    private String effect1,
                   effect2;
    
    public OffHand(String name, String desc, int b, int p, int s, String e1, String e2){
        this.setName(name);
        this.setDesc(desc);
        block = b;
        parry = p;
        spellCounter = s;
        this.setEff1(e1);
        this.setEff2(e2);
        this.setSlot(9);
    }
    @Override
    public int getBL(){
        return block;
    }
    @Override
    public int getPA(){
        return parry;
    }
    @Override
    public int getSC(){
        return spellCounter;
    }
    
}
