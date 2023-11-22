package pl.cbr.retro;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationListener;
import pl.cbr.retro.scene.Constants;
import pl.cbr.retro.scene.MainStage;
import pl.cbr.retro.scene.menu.MenuMessage;
import pl.cbr.retro.scene.cross.BackgroundCross;
import pl.cbr.retro.scene.maze.MazeStage;
import pl.cbr.retro.scene.menu.MenuStage;

@SpringBootApplication
public class ApplicationExample implements ApplicationListener<MenuMessage> {

    private final MainStage stage;

    public ApplicationExample(MainStage stage, MenuStage menu, MazeStage maze, BackgroundCross cross) {
        this.stage = stage;
        stage.add(menu);
        stage.add(maze);
        stage.add(cross);
        stage.setScenery("MENU", "CROSS");
    }

    @Override
    public void onApplicationEvent(MenuMessage event) {
        if (Constants.NEW_GAME.equals(event.getMessage())) {
            stage.setScenery("MAZE");
        }
    }

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(ApplicationExample.class);
        builder.profiles("example");
        builder.headless(false);
        builder.run(args);
    }
}