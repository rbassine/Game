import java.util.Random;

/**
 * Created by rb129 on 4/10/2016.
 */
public class Player {

    private String name;
    private String type;
    private int stats[];
    private Equipment equipment[];

    public Player(String name, int[] statRanges){

    }

    public Player(Creature creature){
        // For if an enemy joins your party
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
}
