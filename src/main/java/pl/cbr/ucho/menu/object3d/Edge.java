package pl.cbr.ucho.menu.object3d;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.awt.*;

@Value
@AllArgsConstructor
@Data
public class Edge implements Transform2D<Edge>, PrintGraphics {
    Point2D x, y;

    public void print(Graphics g) {
        g.drawLine((int)x.getX(), (int)x.getY(), (int)y.getX(), (int)y.getY());
    }

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
}
