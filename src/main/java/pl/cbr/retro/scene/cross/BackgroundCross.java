package pl.cbr.retro.scene.cross;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cbr.retro.scene.Scenery;
import pl.cbr.retro.scene.cross.object3d.OurObject;
import pl.cbr.retro.scene.cross.object3d.Point2D;
import pl.cbr.retro.scene.cross.object3d.SystemCounter;

import java.awt.*;
import java.awt.event.KeyEvent;

@AllArgsConstructor
@Service
public class BackgroundCross extends Scenery {

    private final SystemCounter systemCounter;

    @Override
    public String getName() {
        return "CROSS";
    }

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

    @Override
    public void keyPressed(KeyEvent e) {

    }

    private double getAngle(int i, SystemCounter systemCounter) {
        return (double) (systemCounter.getStep() + i) /10.0;
    }
}
