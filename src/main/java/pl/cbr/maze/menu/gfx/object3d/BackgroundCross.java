package pl.cbr.maze.menu.gfx.object3d;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cbr.maze.Drawing;

import java.awt.*;

@AllArgsConstructor
@Service
public class BackgroundCross implements Drawing {

    private final SystemCounter systemCounter;

    public void doDrawing(Graphics g) {
        OurObject ourObject = OurObject.create();
        Point2D offset = Point2D.of(200, 200);

        for ( int i=0; i<4; i++ ) {
            g.setColor(new Color(i*80, i*80, 255));
            OurObject ball0 = ourObject.rotate(getAngle(-i, systemCounter)).move(offset);
            ball0.doDrawing(g);
        }

        systemCounter.setStep(systemCounter.getStep()+1);
        if ( systemCounter.getStep()>3600 ) {
            systemCounter.setStep(0);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private double getAngle(int i, SystemCounter systemCounter) {
        return (double) (systemCounter.getStep() + i) /10.0;
    }

}
