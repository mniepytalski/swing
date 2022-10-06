package pl.cbr.maze.game.model;

import java.util.HashMap;
import java.util.Map;

public class MazeModel {

    private static Map<String,MazeGenerator> mazes = new HashMap<>();

    public static MazeGenerator getMaze(String name) {
        if ( !mazes.containsKey(name) ) {
            mazes.put(name, new MazeGenerator(260,144));
        }
        return mazes.get(name);
    }
}
