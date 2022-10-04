package pl.cbr.maze.object3d;

import org.springframework.stereotype.Service;
import pl.cbr.maze.SystemCounter;

import java.awt.*;

@Service
public class BackgroundCross {

    public void doDrawing(Graphics g, SystemCounter systemCounter) {
        OurObject ourObject = OurObject.create();
        Point2D offset = new Point2D(200, 200);

        for ( int i=0; i<4; i++ ) {
            g.setColor(new Color(i*80, i*80, 255));
            OurObject ball0 = ourObject.rotate(getAngle(-i, systemCounter)).move(offset);
            ball0.print(g);
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
