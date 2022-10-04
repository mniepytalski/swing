package pl.cbr.maze.game.model;


import pl.cbr.maze.Drawing;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;

public class MazeGenerator implements Drawing {
    private final int x;
    private final int y;
    private final int[][] maze;

    public MazeGenerator(int x, int y) {
        this.x = x;
        this.y = y;
        maze = new int[this.x][this.y];
        generateMaze(0, 0);
    }

    public void display() {
        for (int i = 0; i < y; i++) {
            // draw the north edge
            for (int j = 0; j < x; j++) {
                System.out.print((maze[j][i] & 1) == 0 ? "+---" : "+   ");
            }
            System.out.println("+");
            // draw the west edge
            for (int j = 0; j < x; j++) {
                System.out.print((maze[j][i] & 8) == 0 ? "|   " : "    ");
            }
            System.out.println("|");
        }
        // draw the bottom line
        for (int j = 0; j < x; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }

    private void generateMaze(int cx, int cy) {
        Direction[] dirs = Direction.values();
        Collections.shuffle(Arrays.asList(dirs));
        for (Direction dir : dirs) {
            int nx = cx + dir.dx;
            int ny = cy + dir.dy;
            if (between(nx, x) && between(ny, y)
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
        int wall = 20;

        for (int i = 0; i < y; i++) {
            // draw the north edge
            for (int j = 0; j < x; j++) {
                if ((maze[j][i] & 1) == 0 ) {
                    g.drawLine(i*wall, j*wall, i*wall+wall, j*wall);

                }
               // System.out.print((maze[j][i] & 1) == 0 ? "+---" : "+   ");
            }
            //System.out.println("+");
            // draw the west edge
            for (int j = 0; j < x; j++) {
                if ((maze[j][i] & 8) == 0) {
             //       g.drawLine(i * wall, j * wall, i * wall , j * wall+wall);
                }
                // System.out.print((maze[j][i] & 8) == 0 ? "|   " : "    ");
            }
            //System.out.println("|");
        }
        // draw the bottom line
        for (int j = 0; j < x; j++) {
           // System.out.print("+---");
        }
       // System.out.println("+");
    }
}
