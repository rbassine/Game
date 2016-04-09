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
    public Creature(String name, String type, boolean isNPC){
        stats = new int[7];
        this.name = name;
        if(isNPC == true){
            this.isNPC = true;
            this.type = "NPC";
        }
        else{
            this.isNPC = false;
            this.type = "Object?";
        }
        // No stats needed
    }

    public Creature(String name, String type, boolean isNPC, int hpMpRange, int otherRange){
        this.name = name;
        this.type = type;
        setStats(hpMpRange, otherRange);
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

    public void setStats(int hpMpRange, int otherRange){

    }
}
