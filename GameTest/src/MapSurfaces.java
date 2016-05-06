import java.awt.*;

/**
 * Creates the surfaces that will make up the map.
 * Created by rb129 on 4/13/2016.
 */
public class MapSurfaces {
    private String surfaceType;
    private boolean isWalkable;

    // Constructor
    public MapSurfaces(String surfaceType, boolean isWalkable){
        this.surfaceType = surfaceType;
        this.isWalkable = isWalkable;
    }

    // Methods
    public String getSurfaceType(){
        return surfaceType;
    }

    public void setSurfaceType(String surfaceType){
        this.surfaceType = surfaceType;
    }

    public boolean getIsWalkable(){
        return isWalkable;
    }

    public void setIsWalkable(boolean isWalkable){
        this.isWalkable = isWalkable;
    }

    public void drawSurface(Graphics g, int size, int x, int y){
        g.setColor(Color.green);
        g.drawRect(x, y, size, size);   // Will draw the current surface onto the map
    }
}
