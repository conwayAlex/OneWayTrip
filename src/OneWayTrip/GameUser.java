

package OneWayTrip;
import java.io.*;

/**
 *
 * @author Alex Conway
 * @version 1.0 
 */
public class GameUser extends Player {
    
    public GameUser(){
    }
    public void initNewUser(){
        this.setLevel(1);
        this.setHP(100);
        this.setMaxHP(100);
        this.setMP(100);
        this.setMaxMP(100);
        this.setSP(100);
        this.setMaxSP(100);
        this.setBackpack(10);
        this.setMaxAbilities(3);
        this.setMaxSpells(3);
        this.inventory = new Item[this.getBackpack()];
        this.knownAbilities = new Ability[this.getMaxAbilities()];
        this.knownSpells = new Spell[this.getMaxSpells()];
    }
    public void saveChar(){
        try(FileWriter writer = new FileWriter("saves\\" + this.getName() + ".txt");) {
            writer.write(this.getLevel()+"\r\n");
            writer.write(this.getTag()+"\r\n");
            writer.write(this.getHP()+"\r\n");
            writer.write(this.getMP()+"\r\n");
            writer.write(this.getSP()+"\r\n");
            writer.write(this.getExp()+"\r\n");
            writer.write(this.getMaxHP()+"\r\n");
            writer.write(this.getMaxMP()+"\r\n");
            writer.write(this.getMaxSP()+"\r\n");
            writer.write(this.getTen()+"\r\n");
            writer.write(this.getDex()+"\r\n");
            writer.write(this.getArc()+"\r\n");
            writer.write(this.getBra()+"\r\n");
            writer.write(this.getFin()+"\r\n");
            writer.write(this.getSor()+"\r\n");
            writer.write(this.getBackpack()+"\r\n");
            writer.write(this.getMaxAbilities()+"\r\n");
            writer.write(this.getMaxSpells()+"\r\n");
            
            //write gold
            //save inventory
            /*for(int i = 0; i < inventory.length; i++){
            }*/
            //save equipment
            /*for(int i = 0; i < 8; i++){
                writer.write(gear[i].getName()+"\r\n");
                writer.write(gear[i].getDesc()+"\r\n");
            }*/
            //save weapons
            writer.write(gear[8].getName()+"\r\n");
            writer.write(gear[8].getDesc()+"\r\n");
            writer.write(gear[8].getMinDam()+"\r\n");
            writer.write(gear[8].getMaxDam()+"\r\n");
            writer.write(gear[8].get2H()+"\r\n");
            writer.write(gear[8].getEff1()+"\r\n");
            writer.write(gear[8].getEff2()+"\r\n");
            if(gear[9] != null){
                writer.write(gear[9].getName()+"\r\n");
                writer.write(gear[9].getDesc()+"\r\n");
                writer.write(gear[9].getBL()+"\r\n");
                writer.write(gear[9].getPA()+"\r\n");
                writer.write(gear[9].getSC()+"\r\n");
                writer.write(gear[9].getEff1()+"\r\n");
                writer.write(gear[9].getEff2()+"\r\n");
            }
            else {
                writer.write("\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n");
            }
            
            //save other shit
            //save ab
            for(int i = 0; i < knownAbilities.length; i++){
                if(knownAbilities[i] != null){
                    writer.write(knownAbilities[i].getName()+"\r\n");
                    writer.write(knownAbilities[i].getDesc()+"\r\n");
                }
            }
            for(int i = 0; i < knownSpells.length; i++){
                if(knownSpells[i] != null){
                    writer.write(knownSpells[i].getName()+"\r\n");
                    writer.write(knownSpells[i].getDesc()+"\r\n");
                } 
            }
            //save equipment
            
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadChar(){
        ItemGenerator g = new ItemGenerator();
        try(FileReader reader = new FileReader("saves\\" + this.getName() + ".txt")){
            BufferedReader bReader = new BufferedReader(reader);
            this.setLevel(Integer.parseInt(bReader.readLine()));
            this.setTag(bReader.readLine());
            this.setHP(Integer.parseInt(bReader.readLine()));
            this.setMP(Integer.parseInt(bReader.readLine()));
            this.setSP(Integer.parseInt(bReader.readLine()));
            this.setExp(Integer.parseInt(bReader.readLine()));
            this.setMaxHP(Integer.parseInt(bReader.readLine()));
            this.setMaxMP(Integer.parseInt(bReader.readLine()));
            this.setMaxSP(Integer.parseInt(bReader.readLine()));
            this.setAllAS(Integer.parseInt(bReader.readLine()), Integer.parseInt(bReader.readLine()), Integer.parseInt(bReader.readLine()));
            this.setAllArch(Double.parseDouble(bReader.readLine()), Double.parseDouble(bReader.readLine()), Double.parseDouble(bReader.readLine()));
            this.setBackpack(Integer.parseInt(bReader.readLine()));
            this.setMaxAbilities(Integer.parseInt(bReader.readLine()));
            this.setMaxSpells(Integer.parseInt(bReader.readLine()));
            this.inventory = new Item[this.getBackpack()];
            this.knownAbilities = new Ability[this.getMaxAbilities()];
            this.knownSpells = new Spell[this.getMaxSpells()];
            gear[8] = g.generateWeaponFromSave(bReader);
            gear[9] = g.generateOffHandFromSave(bReader);
            for(int i = 0; i < knownAbilities.length; i++){
                knownAbilities[i] = g.generateAbilityFromSave(bReader);
            }
            for(int i = 0; i < knownSpells.length; i++){
                knownSpells[i] = g.generateSpellFromSave(bReader);
            }
            reader.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
