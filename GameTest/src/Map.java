import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by rb129 on 4/13/2016.
 */
public class Map {
    private ArrayList<MapSurfaces> surfaces;    // The different types of surfaces that are in the map
    private MapSurfaces[][] mapTiles;    // Each tile on a map
    private int width;
    private int height;
    private int surfaceSize;

    // Constructor
    public Map(int width, int height, int surfaceSize){
        // This will only work with one type of surface, or it will end up being kind of strange.
        this.width = width;
        this.height = height;
        this.surfaceSize = surfaceSize;
        mapTiles = new MapSurfaces[height][width];

        int numXTiles = width / surfaceSize;
        int numYTiles = height / surfaceSize;

        for(int i = 0; i < numYTiles; i++){
            for(int j = 0; j < numYTiles; j++){
                mapTiles[i][j] = new MapSurfaces("Grass", true);    // TODO: make based off of the ArrayList
            }
        }

    }

    public Map(File file){
        // TODO: make a file to work with do design a map with an array of surfaces
    }

    // Methods
    public void drawMap(Graphics g){
        int numXTiles = width / surfaceSize;
        int numYTiles = height / surfaceSize;

        for(int i = 0; i < mapTiles.length; i++){
            for(int j = 0; i < mapTiles[i].length; j++){
                int xStart = j * surfaceSize;
                int yStart = i * surfaceSize;

                mapTiles[i][j].drawSurface(g, surfaceSize, xStart, yStart);
            }
        }
    }
}
