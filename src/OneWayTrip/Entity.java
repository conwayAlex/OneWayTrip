package OneWayTrip;

/**
 * This entity class is designed with the basics
 * of a living creature in the game world.
 * All enemies, NPCs, and players will be based
 * from this. 
 * @author Alex Conway
 * @version 1.0 
 */
public class Entity {
    private String name;
    //Resource Values
    private int level,
                hitpoints,
                hitpointsMax,
                mana,
                manaMax,
                stamina,
                staminaMax;
    //Mitigation Values
    private int armor,
                block,
                evasion,
                parry,
                spellCounter,
                spellResist;
    
    public Entity(){
    }
    //Mutate
    public void setName(String n){
        name = n;
    }
    public void setLevel(int l){
        level = l;
    }
    public void setHP(int hp){
        hitpoints = hp;
    }
    public void setMaxHP(int hp){
        hitpointsMax = hp;
    }
    public void setMP (int mp){
        mana = mp;
    }
    public void setMaxMP(int mp){
        manaMax = mp;
    }
    public void setSP(int sp){
        stamina = sp;
    }
    public void setMaxSP(int sp){
        staminaMax = sp;
    }
    public void setAllRes(int a, int b, int e, int p, int c, int r){
        armor = a;
        block = b;
        evasion = e;
        parry = p;
        spellCounter = c;
        spellResist = r;
    }
    
    //Access
    public String getName(){
        return name;
    }
    public int getLevel(){
        return level;
    }
    public int getHP(){
        return hitpoints;
    }
    public int getMaxHP(){
        return hitpointsMax;
    }
    public int getMP(){
        return mana;
    }
    public int getMaxMP(){
        return manaMax;
    }
    public int getSP(){
        return stamina;
    }
    public int getMaxSP(){
        return staminaMax;
    }
}
