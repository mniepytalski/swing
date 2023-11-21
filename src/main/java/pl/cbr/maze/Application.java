package pl.cbr.maze;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import pl.cbr.maze.game.MazeStage;
import pl.cbr.maze.menu.MenuStage;

@SpringBootApplication
public class Application {

    public Application(ApplicationContainer stage, MenuStage menu, MazeStage maze) {
        stage.add(menu);
        stage.add(maze);
    }

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
        builder.headless(false);
        builder.run(args);
    }
}