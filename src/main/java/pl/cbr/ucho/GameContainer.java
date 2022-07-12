package pl.cbr.ucho;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.cbr.ucho.menu.MenuKeyAdapter;
import pl.cbr.ucho.menu.MenuLogic;
import pl.cbr.ucho.menu.MenuMessage;
import pl.cbr.ucho.menu.object3d.OurObject;
import pl.cbr.ucho.menu.object3d.Point2D;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
@AllArgsConstructor
@Component
public class GameContainer extends JPanel implements ActionListener, Drawing, ApplicationListener<MenuMessage> {

    private final SystemTimer systemTimer;
    private final SystemState systemState;
    private final MenuLogic gameMenu;
    private final ApplicationEventPublisher applicationEventPublisher;
    private MenuMessage event;

    @PostConstruct
    private void initBoard() {
        setFocusable(true);
//        addKeyListener(new GameKeyAdapter());
        systemTimer.init(this);
        systemTimer.start();
        addKeyListener(new MenuKeyAdapter(gameMenu, applicationEventPublisher));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void doDrawing(Graphics g) {
        OurObject ourObject = OurObject.create();
        Point2D offset = new Point2D(200, 200);

        for ( int i=0; i<4; i++ ) {
            g.setColor(new Color(i*80, i*80, 255));
            OurObject ball0 = ourObject.rotate(getAngle(-i)).move(offset);
            ball0.print(g);
        }

        systemState.setStep(systemState.getStep()+1);
        if ( systemState.getStep()>3600 ) {
            systemState.setStep(0);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private double getAngle(int i) {
        return (double) (systemState.getStep() + i) /10.0;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        gameMenu.doDrawing(g);
    }

    @Override
    public void onApplicationEvent(MenuMessage event) {
        this.event = event;
        log.info("message: {}", event);
        if ("exit".equals(event.getMessage())) {
            setVisible(false);
            System.exit(0);
        }
    }
}
