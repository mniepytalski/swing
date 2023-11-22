package pl.cbr.retro.scene.maze.model;

import pl.cbr.retro.scene.Drawing;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;

public class MazeGenerator implements Drawing {
    private final int sizeX;
    private final int sizeY;
    private final int[][] maze;

    int wall = 5;
    int offset = 10;

    public MazeGenerator(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        maze = new int[this.sizeX][this.sizeY];
        generateMaze(0, 0);
    }

    private void generateMaze(int cx, int cy) {
        Direction[] dirs = Direction.values();
        Collections.shuffle(Arrays.asList(dirs));
        for (Direction dir : dirs) {
            int nx = cx + dir.dx;
            int ny = cy + dir.dy;
            if (between(nx, sizeX) && between(ny, sizeY)
                    && (maze[nx][ny] == 0)) {
                maze[cx][cy] |= dir.bit;
                maze[nx][ny] |= dir.opposite.bit;
                generateMaze(nx, ny);
            }
        }
    }

    private static boolean between(int v, int upper) {
        return (v >= 0) && (v < upper);
    }

    @Override
    public void doDrawing(Graphics g) {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                if ((maze[x][y] & Direction.N.getBit()) == 0 ) {
                    drawLine(g, Point.get(x, y) , Point.get(x+1, y));
                }
                if ((maze[x][y] & Direction.W.getBit()) == 0) {
                    drawLine(g, Point.get(x, y) , Point.get(x, y+1));
                }
            }
        }
        drawLine(g, Point.get(0, sizeY) , Point.get(sizeX, sizeY));
        drawLine(g, Point.get(sizeX, 0) , Point.get(sizeX, sizeY));
    }

    private void drawLine(Graphics g, Point a, Point b) {
        Point a1 = a.multiply(wall).add(offset);
        Point b1 = b.multiply(wall).add(offset);
        g.drawLine(a1.getX(), a1.getY(), b1.getX(), b1.getY());
    }
}
