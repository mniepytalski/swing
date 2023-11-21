package pl.cbr.maze;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.cbr.maze.game.config.ApplicationConfig;
import pl.cbr.maze.menu.MenuMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
@Component
public class ApplicationContainer extends JPanel implements ActionListener, ApplicationListener<MenuMessage> {

    private final SystemTimer systemTimer;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final SystemManager systemManager;
    private final ApplicationConfig config;
    private MenuMessage event;

    public ApplicationContainer(SystemTimer systemTimer, ApplicationEventPublisher applicationEventPublisher,
                                SystemManager systemManager, ApplicationConfig config) {
        this.systemTimer = systemTimer;
        this.applicationEventPublisher = applicationEventPublisher;
        this.systemManager = systemManager;
        this.config = config;
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

    public void add(ApplicationStage stage) {
        getSystemManager().addToStage(stage);
    }

    @Bean
    public JFrame frame() {
        JFrame frame = new JFrame(config.getTitle());
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(config.getSize().getX(), config.getSize().getY());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        return frame;
    }
}
