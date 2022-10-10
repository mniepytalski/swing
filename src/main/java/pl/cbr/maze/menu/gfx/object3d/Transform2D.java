package pl.cbr.maze.menu.gfx.object3d;

public interface Transform2D <T> {

    T rotate(double angle);

    T move(Point2D offset);

    T scale(int scale);
}
