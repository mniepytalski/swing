package pl.cbr.retro.scene;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.cbr.retro.scene.config.ApplicationConfig;
import pl.cbr.retro.scene.menu.MenuMessage;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
@Component
public class MainStage extends JPanel implements ActionListener, ApplicationListener<MenuMessage> {

    private final StageClock stageClock;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final SceneryDirector sceneryDirector;
    private final ApplicationConfig config;
    private MenuMessage event;

    public MainStage(StageClock stageClock, ApplicationEventPublisher applicationEventPublisher,
                     SceneryDirector sceneryDirector, ApplicationConfig config) {
        this.stageClock = stageClock;
        this.applicationEventPublisher = applicationEventPublisher;
        this.sceneryDirector = sceneryDirector;
        this.config = config;
        initBoard();
    }

    private void initBoard() {
        setFocusable(true);
        stageClock.init(this);
        stageClock.start();
        addKeyListener(sceneryDirector);
        sceneryDirector.startSystem();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        sceneryDirector.doDrawing(g);
    }

    @Override
    public void onApplicationEvent(MenuMessage event) {
        this.event = event;
        log.info("message: {}", event);

        if (Constants.EXIT.equals(event.getMessage())) {
            sceneryDirector.stopSystem();
            setVisible(false);
            System.exit(0);
        }
    }

    public void add(Scenery scenery) {
        getSceneryDirector().addToStage(scenery);
    }


    public void setScenery(String ... names) {
        getSceneryDirector().getActualScenery().setScenery(names);
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
