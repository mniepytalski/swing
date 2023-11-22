package pl.cbr.retro.scene.cross.object3d;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import pl.cbr.retro.scene.Drawing;

import java.awt.*;

@Value
@AllArgsConstructor
@Data
public class Edge implements Transform2D<Edge>, Drawing {
    Point2D x, y;

    @Override
    public Edge rotate(double angle) {
        return new Edge(x.rotate(angle), y.rotate(angle));
    }

    @Override
    public Edge move(Point2D offset) {
        return new Edge(x.move(offset), y.move(offset));
    }

    @Override
    public Edge scale(int scale) {
        return new Edge(x.scale(scale), y.scale(scale));
    }

    @Override
    public void doDrawing(Graphics g) {
        g.drawLine((int)x.getX(), (int)x.getY(), (int)y.getX(), (int)y.getY());
    }
}
