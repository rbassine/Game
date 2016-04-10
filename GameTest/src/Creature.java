import com.sun.javafx.sg.prism.NGParallelCamera;

import java.util.Random;

/**
 * Created by rb129 on 4/8/2016.
 */
public class Creature {
    // Instance variables
    private String name;
    private String type;
    private boolean isNPC;
    private int[] stats;
        /*
            [0] = hp    [1] = Max Hp    [2] = mp    [3] = Max Mp    [4] = str   [5] = def   [6] = spr   [7] = mdef  [8] = speed
         */

    // Constructors
    public Creature(String name, String type){
        stats = new int[7];
        this.name = name;
        this.isNPC = true;
        this.type = "NGP";      // Cannot battle with
        // No stats needed
    }

    public Creature(String name, String type, int[] statRanges){
        this.name = name;
        this.type = type;
        setStats(statRanges);
        this.isNPC = false;     // Can battle with
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public void setNPC(boolean isNPC){
        this.isNPC = isNPC;
    }

    public boolean getNPC(boolean isNPC){
        return isNPC;
    }

    public void setStats(int[] statRanges){
        Random rand = new Random();

        /*
            statRanges [0] = hp low end     [1] hp high end     and so on
         */
        int index = 0;  // used to go through the values of the stats while the loop works a little differently
        for(int i = 2; i < statRanges.length; i += 2){
            // Starting at 2 so that only the maxHP gets dealt with

            if(index == 0 || index == 2){
                continue;
                // Skips everything else for this run of the loop or current HP and current MP
            }

            int tempStat = rand.nextInt(statRanges[i+1] - statRanges[i]) + statRanges[i];


            if(index == 1 || index == 3){
                this.stats[index] = tempStat;
                this.stats[index - 1] = this.stats[index];
                // Sets the HP to the maxHP and MP to maxMP
            }
            else{
                // For stats without max values
                this.stats[index] = tempStat;
            }

            index++;
        }
    }

    public int[] getStats(){
        return stats;
    }

    public int getSingleStat(int i){
        return stats[i];
    }

    public int attack(Creature target){
        Random rand = new Random();

        int attackRange = (int)(this.stats[4] * 0.2);
        /*
            The rand will always need to be an integer to avoid decimals for damage so if the attack value
            is 10 the damage rand will be between 8 and 12 and if the attack value is 100 then the damage
            will end up being from 80 to 120.
            TODO: I will change this later to be a better equation involving a stat that will differ sometimes
            TODO: but for now this simple equation will work fine.
         */
        int attackVariation = rand.nextInt(attackRange * 2)  - attackRange;
        int defenseRange = (int)(target.getSingleStat(5) * 0.2);
        int defenseVariation = rand.nextInt(defenseRange * 2) - defenseRange;

        // Using the above variations to get and attack and defense value for calcuating the damage
        int attack = this.stats[4] + attackVariation;
        int defense = target.getSingleStat(5) + defenseVariation;

        /*
            TODO: Make sure the damage is above 0, this might change later so that certain attacks will also
            TODO: heal, such as with some games where fire creatures will be healed by fire attacks.
         */
        int damage = attack - defense;
        if(damage <= 0){
            return rand.nextInt(1);
            //  TODO: if the defense is higher, either 1 or 0 damage is dealt, again will be made better at later time
        }
        else{
            return damage;
            // TODO: this can also be improved
        }
    }
}
