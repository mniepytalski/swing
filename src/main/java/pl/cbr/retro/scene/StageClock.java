package pl.cbr.retro.scene;

import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.event.ActionListener;

@Service
public class StageClock {
    private static final int DELAY = 50;

    private Timer timer;

    public void init(ActionListener listener) {
        timer = new Timer(DELAY, listener);
    }

    public void start() {
        if ( !timer.isRunning() ) {
            timer.start();
        }
    }

    public void stop() {
        timer.stop();
    }

    public boolean isRunning() {
        return timer.isRunning();
    }
}
