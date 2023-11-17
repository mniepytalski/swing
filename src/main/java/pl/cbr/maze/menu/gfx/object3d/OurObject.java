package pl.cbr.maze.menu.gfx.object3d;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.cbr.maze.Drawing;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class OurObject implements Transform2D<OurObject>, Drawing {
    private List<Edge> edges;
    public OurObject() {
        edges = new ArrayList<>();
    }

    public static OurObject create() {

        OurObject ourObject = getObject();

        OurObject ball0 = ourObject.scale(100).move(Point2D.of(50,-50));

        List<Edge> edges1 = ball0.rotate(1.5708).getEdges();
        List<Edge> edges2 = edges1.stream().map(e -> e.rotate(1.5708)).toList();
        List<Edge> edges3 = edges2.stream().map(e -> e.rotate(1.5708)).toList();

        ball0.getEdges().addAll(edges1);
        ball0.getEdges().addAll(edges2);
        ball0.getEdges().addAll(edges3);

        return ball0;
    }

    private static OurObject getObject() {

        Point2D a1 = Point2D.of(0,0);
        Point2D a2 = Point2D.of(2,0);
        Point2D a3 = Point2D.of(2,1);
        Point2D a4 = Point2D.of(0,1);

        OurObject ourObject = new OurObject();
        ourObject.getEdges().add(new Edge(a1, a2));
        ourObject.getEdges().add(new Edge(a2, a3));
        ourObject.getEdges().add(new Edge(a3, a4));
        return ourObject;
    }

    public OurObject move(Point2D offset) {
        return new OurObject(edges.stream().map(o -> o.move(offset)).collect(Collectors.toList()));
    }

    public OurObject scale(int scale) {
        return new OurObject(edges.stream().map(o -> o.scale(scale)).collect(Collectors.toList()));
    }

    public OurObject rotate(double angle) {
        return new OurObject(edges.stream().map(o -> o.rotate(angle)).collect(Collectors.toList()));
    }

    @Override
    public void doDrawing(Graphics g) {
        edges.forEach(edge -> edge.doDrawing(g));
    }
}
