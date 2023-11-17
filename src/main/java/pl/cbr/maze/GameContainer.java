package pl.cbr.maze;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.cbr.maze.menu.MenuMessage;

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
    private final ApplicationEventPublisher applicationEventPublisher;
    private final SystemManager systemManager;
    private MenuMessage event;

    public GameContainer(SystemTimer systemTimer, ApplicationEventPublisher applicationEventPublisher,
                         SystemManager systemManager) {
        this.systemTimer = systemTimer;
        this.applicationEventPublisher = applicationEventPublisher;
        this.systemManager = systemManager;
        initBoard();
    }


    private void initBoard() {
        setFocusable(true);
        systemTimer.init(this);
        systemTimer.start();
        addKeyListener(systemManager);
        systemManager.startSystem();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void doDrawing(Graphics g) {
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        systemManager.doDrawing(g);
    }

    @Override
    public void onApplicationEvent(MenuMessage event) {
        this.event = event;
        log.info("message: {}", event);

        if (Constants.EXIT.equals(event.getMessage())) {
            systemManager.stopSystem();
            setVisible(false);
            System.exit(0);
        }
    }
}
