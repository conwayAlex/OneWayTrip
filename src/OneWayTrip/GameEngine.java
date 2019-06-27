

package OneWayTrip;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex Conway
 * @version 1.0 
 */
public class GameEngine implements Runnable {
    //State and task status
    protected String gameState = "Start";
    protected String choice;
    protected boolean taskForUI = false;
    protected boolean inEvent = false;
    //Menus and log
    private String[] menuItems,
                       optDesc;
    private String logText;
    //Control values
    private int choicesLeft;
    //Objects
    private GameUser user;
    protected Event event;
    private ItemGenerator iGen = new ItemGenerator();
    private EventGenerator eGen = new EventGenerator();
    
    public GameEngine(GameUser u){
        user = u;
    }
    @Override
    public void run() {
        try {
            evaluateSelf();
        } catch (InterruptedException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Engine process complete.");
    }
    /*
    This function is designed to determine the state of the game and move itself
    based on the users choice in the menu fed to the UI. It will kick back tasks
    to the UI if needed.
    */
    public void evaluateSelf() throws InterruptedException{
        if(taskForUI){
            return;
        }
        if(inEvent){
            return;
        }
        switch(gameState){
            case "Start": {
                switch(choice){
                    case "Continue": {
                        gameState = "Play";
                        contGame();
                        break;
                    }
                    case "New Game": {
                        gameState = "Choosing name";
                        user.initNewUser();
                        taskForUI = true;
                        System.out.println("Kicking back to UI for next state.");
                        setMenu("yesNo");
                        setDesc("yesNo");
                        break;
                    }
                    case "Load Game": {
                        gameState = "Loading character";
                        setLogT(gameState, null);
                        setMenu(gameState);
                        setDesc(gameState);
                        break;
                    }
                    case "Options": {
                        //opts menu
                        break;
                    }
                    case "Exit": {
                        System.exit(0);
                    }
                }
                break;
            }
            case "Loading character": {
                gameState = "Play";
                loadGame();
                break;
            }
            case "Choosing class": {
                if(choice.compareTo("Custom") == 0){
                    gameState = "Choosing stats";
                    taskForUI = true;
                    setLogT(gameState, null);
                    setMenu("yesNo");
                    setDesc("yesNo");
                }
                else {
                    logText = chooseClass();
                    gameState = "Play";
                    menuItems = new String[] {""};
                    optDesc = new String[] {""};
                    user.saveChar();
                }
                break;
            }
            case "Choosing weapon": {
                chooseWeapon();
                break;
            }
            case "Choosing offhand": {
                chooseOffHand();
                break;
            }
            case "Choosing actions": {
                chooseActions();
                if(choicesLeft == 0){
                    gameState = "Play";
                    setMenu(gameState);
                    setDesc(gameState);
                    user.saveChar();
                    break;
                }
                setMenu(gameState);
                setDesc(gameState);
                break;
            }
            case "Play": {
                switch(choice){
                    case "Explore": {
                        gameState = "Combat"; //this will need to be dynamic to the event generated
                        event = eGen.testGen();
                        logText = "An enemy appeared.\n";
                        setMenu(gameState);
                        setDesc(gameState);
                        break;
                    }
                    case "Character": {
                        //gameState = "Character";
                        break;
                    }
                    case "Save": {
                        user.saveChar();
                        logText = "Game saved.";
                        break;
                    }
                    case "Quit": {
                        gameState = "Start";
                        quitGame();
                        break;
                    }
                }
                break;
            }
            case "Character": {
                switch(choice){
                    case "Info": {
                        break;
                    }
                    case "Equipment": {
                        break;
                    }
                    case "Inventory": {
                        break;
                    }
                    case "Abilities": {
                        break;
                    }
                    case "Spells": {
                        break;
                    }
                    case "Back": {
                        gameState = "Play";
                        //set menus
                        break;
                    }
                }
            }
            case "Combat": {
                switch(choice) {
                    case "Attack": {
                        attack();
                        break;
                    }
                    case "Actions": {
                        break;
                    }
                    case "Items": {
                        break;
                    }
                    case "Run": {
                        break;
                    }
                }
                break;
            }
        }
    }
    /*
    The menus in the UI need to be updated for every choice the user makes. These
    are the predefined ones used for the various aspects of gameplay. Further
    menus will need to be generated for things like inventory lists and loot.
    */
    public void setLogT(String neededText, String context){
        switch(neededText){
            case "yesNo1":{
                logText = "Is " + context + " correct?\n";
                break;
            }
            case "invalid": {
                logText = "Entry invalid. Try again.\n";
                break;
            }
            case "Loading character": {
                logText = "Which character to load?\n";
                break;
            }
            case "Choosing class": {
                logText = "Choosing one of the named classes will auto-apply your stats.\n"
                + "Each class embodies a focus and is given starting gear.\n"
                + "Hybrid classes prioritize one stat over another.\n"
                + "For full control of stats and equipment, choose the 'custom' option.\n";
                break;
            }
            case "Choosing stats": {
                logText = "A path laden with choice.\n"
                + "At each level, you will be allowed to allocate your stats.\n"
                + "You start with 3 points.\n"
                + "Please enter in the format of # # # \n"
                + "Tenacity, Dexterity, and Arcane, respectively.\n";
                break;
            }
            case "Choosing weapon": {
                logText = "Now, please choose a weapon. Note that choosing a one handed weapon lets\n"
                + "you pick an offhand item and a ability/spell or three abilities/spells but\n"
                + "choosing a two handed weapon will only let you pick two abilities/spells.\n";
                break;
            }
            case "Choosing offhand": {
                logText = "Having chose a one-handed weapon, you may choose to take an\n"
                + "off-hand item and a spell/ability or three spells/abilities in any combination.\n";
                break;
            }
            case "Choosing actions": {
                logText = "Please choose a spell or ability from the list.\n"
                + "You may choose " + context + " more.\n";
                break;
            }
            case "Play": {
                logText = "What shall you do?\n";
                break;
            }
        }
    }
    public void setMenu(String neededMenu){
        switch(neededMenu){
            case "yesNo":{
                menuItems = new String[] {"Yes", "No"};
                break;
            }
            case "Loading character": {
                File f = new File("saves/");
                menuItems = f.list();
                for(int i = 0; i < menuItems.length; i++){
                    menuItems[i] = menuItems[i].replace(".txt", "");
                }
                break;
            }
            case "Choosing class":{
                menuItems = new String [] {"Warrior", "Ranger", "Magician", "Knight", "Witchblade",
                                           "Duelist", "Rogue", "Battlemage", "Trickster", "Custom"};
                break;
            }
            case "Choosing weapon": {
                menuItems = new String[] {"Iron Sword", "Iron Axe", "Iron Mace", "Iron Dagger", 
                                          "Wood Wand", "Wood Bow", "Wood Crossbow", "Iron Greatsword", 
                                          "Iron Greataxe", "Iron Greathammer", "Wood Staff"};
                break;
            }
            case "Choosing offhand": {
                menuItems = new String[] {"Iron Shield", "Iron Parrying Dagger", "Hemlock Talisman",
                                          "Leather Buckler", "Wood Shield", "Enchanted Iron Dagger", "None"};
                break;
            }
            case "Choosing actions": {
                menuItems = new String[] {"Rigid Stance", "Evasive Stance", "Guard Stance", "Counter Stance", "Resistant Stance",
                                          "Rage", "Precise Strike", "Double Strike",
                                          "Frostbite", "Shock", "Firespout", "Heal", "Dispel", "Alter Status", "Cripple", "Silence"};
                break;
            }
            case "Play": {
                menuItems = new String[] {"Explore", "Character", "Save", "Quit"};
                break;
            }
            case "Combat": {
                menuItems = new String[] {"Attack", "Actions", "Items", "Run"};
                break;
            }
        }
    }
    public void setDesc(String neededDesc){
        switch(neededDesc){
            case "yesNo":{
                optDesc = new String[] {"", ""};
                break;
            }
            case "Loading character": {
                File f = new File("saves/");
                optDesc = new String[f.listFiles().length];
                for(int i = 0; i < optDesc.length; i++){
                    optDesc[i] = "";
                }
                break;
            }
            case "Choosing class": {
                optDesc = new String[] {"+3 Tenacity, starts with a two-handed greataxe and <abilities>.",
                                        "+3 Dexterity, starts with a bow and <abilities>.",
                                        "+3 Arcane, starts with a wooden staff and <spells>.",
                                        "+2 Ten, +1 Dex, starts with a sword, buckler, and <ability>.",
                                        "+2 Ten, +1 Arc, starts with a sword, talisman, and <ability>.",
                                        "+2 Dex, +1 Ten, starts with a sword, buckler, and <ability>.",
                                        "+2 Dex, +1 Arc, starts with a dagger and <ability & spell 3>.",
                                        "+2 Arc, +1 Ten, starts with a mace, shield, and <spell>.",
                                        "+2 Arc, +1 Dex, starts with a dagger and <ability & spell 3>.",
                                        "You will choose your stats and your loadout."};
                break;
            }
            case "Choosing weapon": {
                optDesc = new String[] {"An iron sword. Deals 1-5 damage. One handed.",
                                        "An iron axe. Deals 2-4 damage. One handed.",
                                        "An iron mace. Deals 3 damage. One handed.",
                                        "An iron dagger. Deals 1-5 damage. One handed.",
                                        "A wooden wand. Deals 1-5 damage. One handed.",
                                        "A wooden bow. Deals 3-7 damage. Uses two hands.",
                                        "A wooden crossbow. Deals 1-10 damage. Uses two hands.",
                                        "An iron greatsword. Deals 1- 10 damage. Uses two hands.",
                                        "An iron greataxe. Deals 3-7 damage. Uses two hands.",
                                        "An iron greathammer. Deals 5 damage. Uses two hands.",
                                        "A wooden staff. Deals 1-10 damage. Uses two hands."};
                break;
            }
            case "Choosing offhand": {
                optDesc = new String[] {"An iron shield. Provides a chance to block attacks.",
                                        "An iron parrying dagger. Provides a chance to parry attacks.",
                                        "A hemlock talisman. Provides a chance to counter a spell.",
                                        "A leather buckler. Provides a small chance to block and parry.",
                                        "A wood shield with runes carved in it. Provides a small chance to block and counter spells.",
                                        "An iron dagger with latent magical energy. Provides a small chance to parry and counter spells.",
                                        "An empty off hand. No bonus is given."};
                break;
            }
            case "Choosing actions": {
                optDesc = new String [] {"-Ability-\n This stance provides a bonus to attack based damage reduction.",
                                         "-Ability-\n This stance provides a bonus to evasion rating.",
                                         "-Ability-\n This stance provides a bonus to block chance. (Can be used without a shield)",
                                         "-Ability-\n This stance provides a bonus to parry or spell counter based on your higher stat, dex or arc, respectively. If equal, will give reduced chance to both.",
                                         "-Ability-\n This stance provides a bonus to spell resistance.",
                                         "-Ability-\n Adrenaline fills you with fury. You do more damage but the recklessness causes you to recieve more attack and spell damage.",
                                         "-Ability-\n A calculated attack with your weapon. Can set a bleed or stun depending on your weapon.",
                                         "-Ability-\n Two attacks in the span of the time of one.",
                                         "-Spell-\n A damage over time cold spell.",
                                         "-Spell-\n A jolt of pure lightning energy.",
                                         "-Spell-\n An unavoidable spray of fire.",
                                         "-Spell-\n A healing spell of holy origin. Can choose target.",
                                         "-Spell-\n This spell will remove a buff on a target or a debuff on yourself.",
                                         "-Spell-\n Removes an ailment from yourself or makes a target susceptible.",
                                         "-Spell-\n Blocks the enemy from using abilities.",
                                         "-Spell-\n Prevents spell casting by an enemy."};
                break;
            }
            case "Play": {
                optDesc = new String[] {"Explore onward into the unknown.",
                                        "Gives access to deeper stats, your inventory, equipment, abilities and spells.",
                                        "Manually save the game. The game automatically saves every ten minutes played (tbd).",
                                        "Returns you to the main menu and saves the game."};
                break;
            }
            case "Combat": {
                optDesc = new String[] {"Attack with your weapon.",
                                        "Use an ability or spell.",
                                        "Use an item.",
                                        "Attempt to escape."};
                break;
            }
        }
    }
    public String getLogText(){
        return logText;
    }
    public String[] getMenuText(){
        return menuItems;
    }
    public String[] getDescText(){
        return optDesc;
    }
    //Generate list of players abilities and spells
    public void generateASMenu(){
        String[] menu = new String[user.knownAbilities.length + user.knownSpells.length];
        int i;
        int j = 0;
        for(i = 0; i < user.knownAbilities.length; i++){
            if(user.knownAbilities[i].getName() != null){
                menu[j] = user.knownAbilities[i].getName();
                j++;
            }  
        }
        for(i = 0; i < user.knownSpells.length; i++){
            if(user.knownSpells[i].getName() != null){
                menu[j] = user.knownSpells[i].getName();
                j++;
            }
        }
        menuItems = menu;
    }
    //Generate players useable items list
    public void generateUseMenu(){
        
    }
    //Simply finds the last modified game file and opens it
    public void contGame(){
        long time = 0;
        int lastPlayed = -1;
        File f = new File("saves/");
        File[] fa;
        fa = f.listFiles();
        for(int i = 0; i < fa.length; i++){
            if(fa[i].lastModified() > time){
                time = fa[i].lastModified();
                lastPlayed = i;
            }
        }
        user.setName(fa[lastPlayed].getName().replace(".txt", ""));
        user.loadChar();
        setLogT(gameState, null);
        setMenu(gameState);
        setDesc(gameState);
    }
    //Generates a list of character names to choose which to load
    public void loadGame(){
        user.setName(choice);
        user.loadChar();
        setLogT(gameState, null);
        setMenu(gameState);
        setDesc(gameState);
    }
    public void quitGame(){
        user.saveChar();
        setLogT(gameState, null);
        setMenu(gameState);
        setDesc(gameState);
    }
    /*
    These functions handle the character creation process. Class choice will
    auto-fill the needed character starting stuff while the custom option uses
    the subsequent three functions to pick their load out.
    */
    public String chooseClass() {
        switch(choice){
            case "Warrior": {
                user.setAllAS(3, 0, 0);
                user.setTag(choice);
                Weapon w = iGen.generateWeaponPreFab("Iron Greataxe");
                user.equipGear(w);
                Ability a;
                a = iGen.generateAbility("Rage");
                user.learnAbility(a);
                a = iGen.generateAbility("Double Strike");
                user.learnAbility(a);
                return "The path of brawn!\n";
            }
            case "Ranger": {
                user.setAllAS(0, 3, 0);
                user.setTag(choice);
                Weapon w = iGen.generateWeaponPreFab("Wood Bow");
                user.equipGear(w);
                Ability a;
                a = iGen.generateAbility("Evasive Stance");
                user.learnAbility(a);
                a = iGen.generateAbility("Precise Strike");
                user.learnAbility(a);
                return "The path of finesse!\n";
            }
            case "Magician": {
                user.setAllAS(0, 0, 3);
                user.setTag(choice);
                Weapon w = iGen.generateWeaponPreFab("Wood Wand");
                user.equipGear(w);
                Spell s;
                s = iGen.generateSpell("Shock");
                user.learnSpell(s);
                s = iGen.generateSpell("Firespout");
                user.learnSpell(s);
                s = iGen.generateSpell("Silence");
                user.learnSpell(s);
                return "The path of sorcery!\n";
            }
            case "Knight": {
                user.setAllAS(2, 1, 0);
                user.setTag(choice);
                Weapon w = iGen.generateWeaponPreFab("Iron Sword");
                user.equipGear(w);
                OffHand o;
                o = iGen.generateOffHandPreFab("Leather Buckler");
                user.equipGear(o);
                Ability a;
                a = iGen.generateAbility("Guard Stance");
                user.learnAbility(a);
                return "A path mixing brawn and finesse!\n";
            }
            case "Witchblade": {
                user.setAllAS(2, 0, 1);
                user.setTag(choice);
                Weapon w;
                w = iGen.generateWeaponPreFab("Iron Sword");
                user.equipGear(w);
                OffHand o;
                o = iGen.generateOffHandPreFab("Hemlock Talisman");
                user.equipGear(o);
                Ability a;
                a = iGen.generateAbility("Counter Stance");
                user.learnAbility(a);
                return "A path mixing brawn and sorcery!\n";
            }
            case "Duelist": {
                user.setAllAS(1, 2, 0);
                user.setTag(choice);
                Weapon w;
                w = iGen.generateWeaponPreFab("Iron Sword");
                user.equipGear(w);
                OffHand o;
                o = iGen.generateOffHandPreFab("Leather Buckler");                
                user.equipGear(o);
                Ability a;
                a = iGen.generateAbility("Counter Stance");
                user.learnAbility(a);
                return "A path mixing finesse and brawn!\n";
            }
            case "Rogue": {
                user.setAllAS(0, 2, 1);
                user.setTag(choice);
                Weapon w;
                w = iGen.generateWeaponPreFab("Iron Dagger");
                user.equipGear(w);
                Ability a;
                a = iGen.generateAbility("Precise Strike");
                user.learnAbility(a);
                a = iGen.generateAbility("Double Strike");
                user.learnAbility(a);
                Spell s;
                s = iGen.generateSpell("Cripple");
                user.learnSpell(s);
                return "A path mixing finesse and sorcery!\n";
            }
            case "Battlemage": {
                user.setAllAS(1, 0, 2);
                user.setTag(choice);
                Weapon w;
                w = iGen.generateWeaponPreFab("Iron Mace");
                user.equipGear(w);
                OffHand o;
                o = iGen.generateOffHandPreFab("Wood Shield");
                user.equipGear(o);
                Spell s;
                s = iGen.generateSpell("Resistant Stance");
                user.learnSpell(s);
                return "A path mixing sorcery and brawn!\n";
            }
            case "Trickster": {
                user.setAllAS(0, 1, 2);
                user.setTag(choice);
                Weapon w;
                w = iGen.generateWeaponPreFab("Iron Dagger");
                user.equipGear(w);
                Spell s;
                s = iGen.generateSpell("Frostbite");
                user.learnSpell(s);
                s = iGen.generateSpell("Dispel");
                user.learnSpell(s);
                Ability a;
                a = iGen.generateAbility("Precise Strike");
                user.learnAbility(a);
                return "A path mixing sorcery and finesse!\n";
            } 
        }
        return null;
    }
    public void chooseWeapon() {
        Weapon w;
        w = iGen.generateWeaponPreFab(choice);
        user.equipGear(w);
        if(user.gear[8].get2H()){
            gameState = "Choosing actions";
            choicesLeft = 2;
            setLogT(gameState, null);
            setMenu(gameState);
            setDesc(gameState);
        }
        else{
            gameState = "Choosing offhand";
            setLogT(gameState, null);
            setMenu(gameState);
            setDesc(gameState);
        }
    }
    public void chooseOffHand() {
        if(choice.compareTo("None") != 0){
            OffHand o;
            o = iGen.generateOffHandPreFab(choice);
            user.equipGear(o);
            choicesLeft = 1;
        }
        else {
            choicesLeft = 3;
        }
        gameState = "Choosing actions";
        setLogT(gameState, Integer.toString(choicesLeft));
        setMenu(gameState);
        setDesc(gameState);
    }
    public void chooseActions() {
        int index = 0;
        
        for(int i = 0; i < user.knownAbilities.length; i++){
            if(user.knownAbilities[i] != null){
                if(user.knownAbilities[i].getName().compareTo(choice) == 0){
                    logText = "You've chosen this already.\n";
                    return;
                }
            }
        }
        for(int i = 0; i < user.knownSpells.length; i++){
            if(user.knownAbilities[i] != null){
                if(user.knownSpells[i].getName().compareTo(choice) == 0){
                    logText = "You've chosen this already.\n";
                    return;
                }
            }
        }
        for(int i = 0; i < menuItems.length; i ++){
            if(menuItems[i].compareTo(choice) == 0){
                index = i;
            }
        }
        if(index > 7){
            Spell s;
            s = iGen.generateSpell(choice);
            user.learnSpell(s);
            choicesLeft--;
            logText = "You have " + choicesLeft + " choices left.\n";
        }
        else {
            Ability a;
            a = iGen.generateAbility(choice);
            user.learnAbility(a);
            choicesLeft--;
            logText = "You have " + choicesLeft + " choices left.\n";
        }
    }
    //Functions for the combat commands
    public void attack(){
        int playerDam = user.attack(),
            enemyDam = event.basicAttack();
        boolean playerDead,
                enemyDead;
                        
        enemyDead = event.recieveDamage(playerDam);
        playerDead = user.recieveDamage(enemyDam);
                        
        if(enemyDead){
            gameState = "Play";
            logText = "Enemy defeated!\n";
            inEvent = false;
        }
        else if(playerDead){
            gameState = "Play";
            logText = "You have fallen in battle.\n";
            inEvent = false;
        }
        else {
            logText = "Enemy took " + playerDam + " damage.\n"
                + "You took " + enemyDam + " damage.\n";
        }
    }
    //Event handling
    public void handleEvent(){
        
    }
}