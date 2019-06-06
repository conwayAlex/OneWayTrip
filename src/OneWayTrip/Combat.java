/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OneWayTrip;

import java.util.Random;

/**
 *
 * @author King of Ohio
 */
public class Combat extends Event {
    protected Enemy enemy;
    
    public Combat(Enemy e){
        enemy = e;
    }
    @Override
    public int basicAttack(){
        Random rng = new Random();
        return enemy.getMinDam() + rng.nextInt(enemy.getMaxDam());
    }
    @Override
    public boolean recieveDamage(int d){
        enemy.setHP(enemy.getHP() - d);
        return enemy.getHP() <= 0;
    }
}
