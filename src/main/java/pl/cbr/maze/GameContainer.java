package pl.cbr.maze;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.cbr.maze.game.GameKeyAdapter;
import pl.cbr.maze.game.gfx.MazeGfx;
import pl.cbr.maze.game.model.MazeGenerator;
import pl.cbr.maze.game.model.MazeModel;
import pl.cbr.maze.menu.MenuKeyAdapter;
import pl.cbr.maze.menu.MenuLogic;
import pl.cbr.maze.menu.MenuMessage;
import pl.cbr.maze.object3d.BackgroundCross;

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
    private final SystemCounter systemCounter;
    private final MenuLogic gameMenu;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final SystemManager systemManager;
    private final GameState gameState;
    private final MazeGfx mazeGfx;
    private MenuMessage event;

    public GameContainer(SystemTimer systemTimer, SystemCounter systemCounter, MenuLogic menuLogic,
                         ApplicationEventPublisher applicationEventPublisher, SystemManager systemManager,
                         GameState gameState, MazeGfx mazeGfx) {
        this.systemTimer = systemTimer;
        this.systemCounter = systemCounter;
        this.gameMenu = menuLogic;
        this.applicationEventPublisher = applicationEventPublisher;
        this.systemManager = systemManager;
        this.gameState = gameState;
        this.mazeGfx = mazeGfx;
    }

    @PostConstruct
    private void initBoard() {
        setFocusable(true);
//        addKeyListener(new GameKeyAdapter());
        systemTimer.init(this);
        systemTimer.start();
        addKeyListener(new MenuKeyAdapter(gameMenu.getMenuModel(), gameState));
        addKeyListener(new GameKeyAdapter(gameState));
        systemManager.registerComponent(gameMenu);
        systemManager.startSystem();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void doDrawing(Graphics g) {
        BackgroundCross backgroundCross = new BackgroundCross();
        backgroundCross.doDrawing(g, systemCounter);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch(gameState.getSystemState()) {
            case MENU:
                doDrawing(g);
                gameMenu.doDrawing(g);
                break;
            case GAME:
                mazeGfx.doDrawing(g);
                break;
        }
    }

    @Override
    public void onApplicationEvent(MenuMessage event) {
        this.event = event;
        log.info("message: {}", event);

        if ("exit".equals(event.getMessage())) {
            systemManager.stopSystem();
            setVisible(false);
            System.exit(0);
        }
        if ("new-game".equals(event.getMessage())) {
            gameState.setSystemState(SystemState.GAME);
        }

    }
}
