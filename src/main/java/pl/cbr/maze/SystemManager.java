package pl.cbr.maze;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import pl.cbr.maze.menu.MenuMessage;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SystemManager extends KeyAdapter implements ApplicationListener<MenuMessage>, Drawing {

    private final List<FileRepository> components = new ArrayList<>();
    private final Map<String, ApplicationStage> stages = new HashMap<>();

    private final ApplicationState applicationState;

    public SystemManager(ApplicationState applicationState) {
        this.applicationState = applicationState;
    }

    public void addToStage(ApplicationStage stage) {
        addStage(stage);
        if (stage instanceof FileRepository) {
            registerComponent((FileRepository)stage);
        }
    }

    public void addStage(ApplicationStage stage) {
        stages.put(stage.getName(), stage);
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

        if ( stages.containsKey(applicationState.getSystemState().name()) ) {
            stages.get(applicationState.getSystemState().name()).doDrawing(g);
        }
    }

    public void keyPressed(KeyEvent e) {
        if ( stages.containsKey(applicationState.getSystemState().name()) ) {
            stages.get(applicationState.getSystemState().name()).keyPressed(e);
        }
    }

    @Override
    public void onApplicationEvent(MenuMessage event) {
        if (Constants.NEW_GAME.equals(event.getMessage())) {
            applicationState.setSystemState(SystemState.MAIN);
            applicationState.setStages("MAIN");
        }
    }
}
