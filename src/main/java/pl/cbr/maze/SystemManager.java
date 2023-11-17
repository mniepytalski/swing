package pl.cbr.maze;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import pl.cbr.maze.game.MazeStage;
import pl.cbr.maze.menu.MenuMessage;
import pl.cbr.maze.menu.MenuStage;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

@Service
public class SystemManager extends KeyAdapter implements ApplicationListener<MenuMessage>, Drawing {

    private final List<FileRepository> components = new ArrayList<>();
    private final Map<SystemState, GameStage> stages = new HashMap<>();

    private final GameState gameState;

    public SystemManager(GameState gameState, MenuStage menuStage, MazeStage mazeStage) {
        this.gameState = gameState;
        addStage(SystemState.MENU, menuStage);
        registerComponent(menuStage);
        addStage(SystemState.GAME, mazeStage);
    }

    public void addStage(SystemState state, GameStage stage) {
        stages.put(state, stage);
    }

    public void registerComponent(FileRepository resource) {
        components.add(resource);
    }

    public void startSystem() {
        components.forEach(FileRepository::load);
    }

    public void stopSystem() {
        components.forEach(FileRepository::save);
    }

    @Override
    public void doDrawing(Graphics g) {
        if ( stages.containsKey(gameState.getSystemState()) ) {
            stages.get(gameState.getSystemState()).doDrawing(g);
        }
    }

    public void keyPressed(KeyEvent e) {
        if ( stages.containsKey(gameState.getSystemState()) ) {
            stages.get(gameState.getSystemState()).keyPressed(e);
        }
    }

    @Override
    public void onApplicationEvent(MenuMessage event) {
        if (Constants.NEW_GAME.equals(event.getMessage())) {
            gameState.setSystemState(SystemState.GAME);
        }
    }
}
