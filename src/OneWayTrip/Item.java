

package OneWayTrip;

/**
 *
 * @author Alex Conway
 * @version 1.0 
 */
public class Item {

    private String itemName,
                   description;
    private boolean stackable,
                    throwable,
                    consumable;
    
    public void setName(String name){
        itemName = name;
    }
    public void setDesc(String desc){
        description = desc;
    }
    public String getName(){
        return itemName;
    }
    public String getDesc(){
        return description;
    }
}
