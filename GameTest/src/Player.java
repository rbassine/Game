import java.util.Random;

/**
 * Created by rb129 on 4/10/2016.
 */
public class Player {

    private String name;
    private String type;
    private int level;
    private int stats[];
    private Equipment equipment[];

    public Player(String name, int[] statRanges){
        this.name = name;

    }

    public Player(String name, int level){
        // Auto generates stats based off given level
        Random rand = new Random();

        this.name = name;
        stats = new int[9];
        this.level = level;

        int variation = 0;

        for(int i = 0; i < stats.length; i++){
            if(i == 0 || i == 2){
                continue;
            }
            else if(i == 1){
                variation = (rand.nextInt(60) - 10) * level;
                stats[i] = variation + 50;
                stats[i-1] = stats[i];
            }
            else if(i == 3){
                variation = (rand.nextInt(30) - 10) * level;
                stats[i] = variation + 25;
                stats[i-1] = stats[i];
            }
            else{
                variation = (rand.nextInt(10) - 5) * level;
                stats[i] = variation + 10;
            }
        }
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public int getLevel(){
        return this.level;
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

    public String toString(){
        String output = "\n";

        output += name + "\n" + "Level: " + level + "\n";
        output += "HP: " + stats[0] + "/" + stats[1] + "\t\t";
        output += "MP: " + stats[2] + "/" + stats[3] + "\n";
        output += "Str: " + stats[4] + "\t\t" + "Def: " + stats[5] + "\n";
        output += "Spr: " + stats[6] + "\t\t" + "MDef: " + stats[7] + "\n";
        output += "Speed: " + stats[8] + "\n\n";

        return output;
    }
}
