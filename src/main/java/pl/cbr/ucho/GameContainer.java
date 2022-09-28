package pl.cbr.ucho;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.cbr.ucho.menu.MenuKeyAdapter;
import pl.cbr.ucho.menu.MenuLogic;
import pl.cbr.ucho.menu.MenuMessage;
import pl.cbr.ucho.object3d.BackgroundCross;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
@Component
public class GameContainer extends JPanel implements ActionListener, Drawing, ApplicationListener<MenuMessage> {

    private final SystemTimer systemTimer;
    private final SystemState systemState;
    private final MenuLogic gameMenu;
    private final ApplicationEventPublisher applicationEventPublisher;
    private MenuMessage event;

    public GameContainer(SystemTimer systemTimer, SystemState systemState, MenuLogic menuLogic, ApplicationEventPublisher applicationEventPublisher) {
        this.systemTimer = systemTimer;
        this.systemState = systemState;
        this.gameMenu = menuLogic;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @PostConstruct
    private void initBoard() {
        setFocusable(true);
//        addKeyListener(new GameKeyAdapter());
        systemTimer.init(this);
        systemTimer.start();
        addKeyListener(new MenuKeyAdapter(gameMenu.getMenuModel()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void doDrawing(Graphics g) {
        BackgroundCross backgroundCross = new BackgroundCross();
        backgroundCross.doDrawing(g, systemState);
        Toolkit.getDefaultToolkit().sync();
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
            gameMenu.save();
            setVisible(false);
            System.exit(0);
        }
    }
}
