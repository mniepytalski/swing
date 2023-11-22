package pl.cbr.retro.scene;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

@Service
public class SceneryDirector extends KeyAdapter implements Drawing {

    private final List<FileRepository> components = new ArrayList<>();
    private final List<Scenery> stages = new ArrayList<>();

    @Getter
    private final ActualScenery actualScenery;

    public SceneryDirector(ActualScenery actualScenery) {
        this.actualScenery = actualScenery;
    }

    public void addToStage(Scenery stage) {
        addStage(stage);
        if (stage instanceof FileRepository) {
            registerComponent((FileRepository)stage);
        }
    }

    public void addStage(Scenery stage) {
        stages.add(stage);
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
        stages.stream().filter(actualScenery::contains)
                .toList().forEach(s -> s.doDrawing(g));
    }

    public void keyPressed(KeyEvent e) {
        stages.stream().filter(actualScenery::contains)
                .toList().forEach(s -> s.keyPressed(e));
    }
}
