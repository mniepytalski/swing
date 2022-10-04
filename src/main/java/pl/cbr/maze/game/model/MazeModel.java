package pl.cbr.maze.game.model;

public class MazeModel {

    private static MazeGenerator mazeGenerator;

    public static MazeGenerator getMaze() {
        if ( mazeGenerator==null ) {
            mazeGenerator = new MazeGenerator(5,5);
        }
        return mazeGenerator;
    }
}
