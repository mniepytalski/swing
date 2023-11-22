package pl.cbr.retro.scene.cross.object3d;

import lombok.*;

@Value
@RequiredArgsConstructor(staticName = "of")
@Data
public class Point2D implements Transform2D<Point2D> {
    double x, y;

    public Point2D rotate(double angle) {
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        return new Point2D(
                x * cos - y * sin,
                x * sin + y * cos
        );
    }

    public Point2D move(Point2D offset) {
        return new Point2D(getX()+offset.getX(), getY()+offset.getY());
    }

    public Point2D scale(int scale) {
        return new Point2D(getX()*scale, getY()*scale);
    }
}
