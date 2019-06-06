

package OneWayTrip;

/**
 *
 * @author Alex Conway
 * @version 1.0 
 */
public class Enemy extends Entity{

    private String type;
    private Item[] loot;
    private int gold;
    private int minDam,
                maxDam;
    protected Action[] actions;
    
    public Enemy( String n, String t, Item[] items, int money){
        this.setName(n);
        type = t;
        loot = items;
        gold = money;
    }
    public void setStats(int hp, int mp, int sp){
        this.setHP(hp);
        this.setMP(mp);
        this.setSP(sp);
    }
    public void setActions(Action[] a){
        actions = a;
    }
    public void setDamage(int min, int max){
        minDam = min;
        maxDam = max;
    }
    public int getMinDam(){
        return minDam;
    }
    public int getMaxDam(){
        return maxDam;
    }
    
}
