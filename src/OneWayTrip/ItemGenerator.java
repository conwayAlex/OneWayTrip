/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OneWayTrip;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author King of Ohio
 */
public class ItemGenerator {
    
    /*
    This class is designed to be the main work horse for making various items as
    well as the spells and abilities. There are functions for pulling base items
    from file as well as generating the item objects from a save.(TBD) It will
    also receive context information about items to randomly generate for things
    like loot.
    */
    
    public Weapon generateWeaponPreFab(String itemName){
        try(FileReader reader = new FileReader("resources\\weaponbases.txt")){
            BufferedReader bReader = new BufferedReader(reader);
            String line;
            line = bReader.readLine();
            while(line.compareTo(itemName) != 0){
                line = bReader.readLine();
            }
            
            Weapon w = new Weapon(line, bReader.readLine(), 
                    Integer.parseInt(bReader.readLine()),
                    Integer.parseInt(bReader.readLine()),
                    Boolean.parseBoolean(bReader.readLine()),
                    bReader.readLine(),
                    bReader.readLine());
            reader.close();
            return w;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public Weapon generateWeaponFromSave(BufferedReader r) throws IOException{
        String line = r.readLine();
        Weapon w = new Weapon(line, r.readLine(), 
                    Integer.parseInt(r.readLine()),
                    Integer.parseInt(r.readLine()),
                    Boolean.parseBoolean(r.readLine()),
                    r.readLine(),
                    r.readLine());
        return w;
    }
    public OffHand generateOffHandPreFab(String itemName){
        try(FileReader reader = new FileReader("resources\\offhandbases.txt")){
            BufferedReader bReader = new BufferedReader(reader);
            String line;
            line = bReader.readLine();
            while(line.compareTo(itemName) != 0){
                line = bReader.readLine();
            }
            
            OffHand o = new OffHand(line, bReader.readLine(), 
                    Integer.parseInt(bReader.readLine()),
                    Integer.parseInt(bReader.readLine()),
                    Integer.parseInt(bReader.readLine()),
                    bReader.readLine(),
                    bReader.readLine());
            reader.close();
            return o;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public OffHand generateOffHandFromSave(BufferedReader r) throws IOException{
        String line = r.readLine();
        if(line.isEmpty()){
            return null;
        }
        else {
            OffHand o = new OffHand(line, r.readLine(), 
                    Integer.parseInt(r.readLine()),
                    Integer.parseInt(r.readLine()),
                    Integer.parseInt(r.readLine()),
                    r.readLine(),
                    r.readLine());
            return o;
        }
    }
    public Ability generateAbility(String aName){
        try(FileReader reader = new FileReader("resources\\specialactions.txt")){
            BufferedReader bReader = new BufferedReader(reader);
            String abName;
            abName = bReader.readLine();
            while(abName.compareTo(aName) != 0){
                abName = bReader.readLine();
            }
            bReader.readLine();
            Ability a = new Ability(abName,bReader.readLine());
            return a;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public Ability generateAbilityFromSave(BufferedReader r) throws IOException{
        String line = r.readLine();
        Ability a = new Ability(line, r.readLine());
        return a;
    }
    public Spell generateSpell(String sName){
        try(FileReader reader = new FileReader("resources\\specialactions.txt")){
            BufferedReader bReader = new BufferedReader(reader);
            String spName;
            spName = bReader.readLine();
            while(spName.compareTo(sName) != 0){
                spName = bReader.readLine();
            }
            bReader.readLine();
            Spell s = new Spell(spName, bReader.readLine());
            return s;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public Spell generateSpellFromSave(BufferedReader r) throws IOException{
        String line = r.readLine();
        Spell s = new Spell(line, r.readLine());
        return s;
    }
    public int generateGold(int playerLevel){
        return 0;
    }
    public Item[] generateLoot(int playerLevel){
        return null;
    }
}
