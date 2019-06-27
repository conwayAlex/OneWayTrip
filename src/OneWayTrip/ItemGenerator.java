/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OneWayTrip;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

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
    public void generateArmor(int b, int f, int s, int playerLevel) throws IOException{
        int pick,
            slot,
            rarity;
        String defense,
               type = "";
        String itemName,
               desc;
        Random rng = new Random();
        
        pick = 1 + rng.nextInt(b+f+s);
        slot = rng.nextInt(3);
        rarity = rng.nextInt(100);
        
        if(pick <= b){
            pick = 1 + rng.nextInt(b+f+s);
            if(pick <= b){
                defense = "AR";
            }
            else if(pick > b && pick <= b+f){
                defense = "AE";
            }
            else{
                defense = "AS";
            }
        }
        else if(pick > b && pick <= b+f){
            pick = 1 + rng.nextInt(b+f+s);
            if(pick <= b){
                defense = "AE";            
            }
            else if(pick > b && pick <= b+f){
                defense = "EV";
            }
            else{
                defense = "ES";
            }
        }
        else{
            pick = 1 + rng.nextInt(b+f+s);
            if(pick <= b){
                defense = "AS";
            }
            else if(pick > b && pick <= b+f){
                defense = "ES";
            }
            else{
                defense = "SR";
            }
        }
        switch(slot){
            case 0: {
                type = "head";
                break;
            }
            case 1: {
                type = "hands";
                break;
            }
            case 2: {
                type = "body";
                break;
            }
            case 3: {
                type = "feet";
                break;
            }
        }
        
        try (FileReader reader = new FileReader("resources\\armorbases.txt")){
            BufferedReader r = new BufferedReader(reader);
            String line = r.readLine();
            while(line.compareTo(defense) != 0){
                line = r.readLine();
            }
            while(line.compareTo(type) != 0){
                line = r.readLine();
            }
            itemName = r.readLine();
            desc = r.readLine();
            reader.close();
        }
        try(FileReader reader = new FileReader("resources\\armorstats.txt")){
            BufferedReader r = new BufferedReader(reader);
            String line = r.readLine();
            while(line.compareTo(defense) != 0){
                line = r.readLine();
            }
            while(line.compareTo(type) != 0){
                line = r.readLine();
            }
            //find relevent level
            //read in adjective
            //read in stats
            reader.close();
        }
        if(rarity > 95){
            //double enchant
            //reroll for chance at legendary 15%
        }
        else if (rarity > 75){ 
            //single enchant, prefix or suffix 50/50
        }
        
    }
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
    
    public Item[] generateStoreItems(String storeType, int itemAmount) {
        // TODO: Switch to return item array;
        
        
        
        return null;
    }
    
}
