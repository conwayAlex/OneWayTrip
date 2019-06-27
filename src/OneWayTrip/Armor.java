

package OneWayTrip;

/**
 *
 * @author Alex Conway
 * @version 1.0 
 */
public class Armor extends Equipment {

    private String type;
    
    private final int armor,
                      evasion,
                      spellResist;
    
    public Armor(String name, String desc, String t, int a, int e, int s, String e1, String e2){

        this.setName(name);
        this.setDesc(desc);
        type = t; 
        switch(type){
            case "head":{
                this.setSlot(0);
                break;
            }
            case "hands":{
                this.setSlot(1);
                break;
            }
            case "body":{
                this.setSlot(2);
                break;
            }
            case "feet":{
                this.setSlot(3);
                break;
            }
            default:
        }
        armor = a;
        evasion = e;
        spellResist = s;
        this.setEff1(e1);
        this.setEff2(e2);
    }
    
    @Override
    public int getAR(){
        return armor;
    }
    @Override
    public int getEV(){
        return evasion;
    }
    @Override
    public int getSR(){
        return spellResist;
    }
    
}
