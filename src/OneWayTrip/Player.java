

package OneWayTrip;

import java.util.Random;

/**
 *
 * @author Alex Conway
 * @version 1.0 
 */
public class Player extends Entity {

    private int experience;
    private String classTag;
    //Archetype weights
    private double brawn,
                   finesse,
                   sorcery;
    //Stat Values
    private int tenacity,
                dexterity,
                arcane;
    //Capacities
    private int backpack,
                maxAbilities,
                maxSpells;
    
    protected Item[] inventory;
    protected Equipment[] gear = new Equipment[10];
    protected Ability[] knownAbilities;
    protected Spell[] knownSpells;
    //protected int gold;
    
    
    //Mutate
    public void setTag(String c){
        classTag = c;
    }
    public void setAllAS(int t, int d, int a){
        tenacity = t;
        dexterity = d;
        arcane  = a;
    }
    public void setAllArch(double b, double f, double s){
        brawn = b;
        finesse = f;
        sorcery = s;
    }
    public void setTen(int t){
        tenacity = t;
    }
    public void setDex(int d){
        dexterity = d;
    }
    public void setArc(int a){
        arcane = a;
    }
    public void setExp(int e){
        experience = e;
    }
    public void setBackpack(int b){
        backpack = b;
    }
    public void setMaxAbilities(int a){
        maxAbilities = a;
    }
    public void setMaxSpells(int s){
        maxSpells = s;
    }
    //Access
    public String getTag(){
        return classTag;
    }
    public double getBra(){
        return brawn;
    }
    public double getFin(){
        return finesse;
    }
    public double getSor(){
        return sorcery;
    }
    public int getTen(){
        return tenacity;
    }
    public int getDex(){
        return dexterity;
    }
    public int getArc(){
        return arcane;
    }
    public int getExp(){
        return experience;
    }
    public int getBackpack(){
        return backpack;
    }
    public int getMaxAbilities(){
        return maxAbilities;
    }
    public int getMaxSpells(){
        return maxSpells;
    }
    //Actions
    public void recieveExp(int amount){
        experience += amount;
    }
    public int attack(){
        Random rng = new Random();
        return gear[8].getMinDam() + rng.nextInt(gear[8].getMaxDam());
    }
    public boolean recieveDamage(int d){
        this.setHP(this.getHP() - d);
        return this.getHP() <= 0;
    }
    //use ability
    //use spell
    //use item
    public int equipGear(Equipment equip){
        if(gear[equip.getSlot()] == null){
            gear[equip.getSlot()] = equip;
            return 0;
        }
        else{
            //equip collision logic
            return -1;
        }
    }
    public int storeItem(Item item){
        for(int i = 0; i < inventory.length; i++){
            if(inventory[i] == null){
                inventory[i] = item;
                return 0;
            }
        }
        return -1;
    }
    public int learnAbility(Ability ability){
        for(int i = 0; i < knownAbilities.length; i++){
            if(knownAbilities[i] == null){
                knownAbilities[i] = ability;
                return 0;
            }
        }
        return -1;
    }
    public int learnSpell(Spell spell){
        for(int i = 0; i < knownSpells.length; i++){
            if(knownSpells[i] == null){
                knownSpells[i] = spell;
                return 0;
            }
        }
        return -1;
    }
    
}
