package pl.cbr.ucho.object3d;

import org.springframework.stereotype.Service;
import pl.cbr.ucho.SystemState;

import java.awt.*;

@Service
public class BackgroundCross {

    public void doDrawing(Graphics g, SystemState systemState) {
        OurObject ourObject = OurObject.create();
        Point2D offset = new Point2D(200, 200);

        for ( int i=0; i<4; i++ ) {
            g.setColor(new Color(i*80, i*80, 255));
            OurObject ball0 = ourObject.rotate(getAngle(-i, systemState)).move(offset);
            ball0.print(g);
        }

        systemState.setStep(systemState.getStep()+1);
        if ( systemState.getStep()>3600 ) {
            systemState.setStep(0);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private double getAngle(int i, SystemState systemState) {
        return (double) (systemState.getStep() + i) /10.0;
    }

}
