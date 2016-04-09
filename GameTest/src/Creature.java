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
            [0] = hp    [1] = mp    [2] = str   [3] = def   [4] = spr   [5] = mdef  [6] = speed
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

        for(int i = 0; i < statRanges.length; i += 2){
            int tempStat = rand.nextInt(statRanges[i+1] - statRanges[i]) + statRanges[i];
            this.stats[i] = tempStat;
        }
    }

    public int[] getStats(){
        return stats;
    }
}
