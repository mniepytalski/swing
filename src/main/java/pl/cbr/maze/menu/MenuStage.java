package pl.cbr.maze.menu;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.cbr.maze.FileRepository;
import pl.cbr.maze.ApplicationStage;
import pl.cbr.maze.utils.PropertiesUtil;
import pl.cbr.maze.menu.gfx.MenuSimplePrintEngine;
import pl.cbr.maze.menu.model.MenuModel;
import pl.cbr.maze.menu.gfx.object3d.BackgroundCross;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Properties;

@Slf4j
@Service
@AllArgsConstructor
public class MenuStage extends ApplicationStage implements FileRepository {

    private final MenuModel menuModel;
    private final MenuKeyAdapter keyAdapter;
    private final MenuSimplePrintEngine menuPrintEngine;
    private final BackgroundCross backgroundCross;
    private final PropertiesUtil propertiesUtil;

    private final static String fileName = "menu.properties";

    public String getName() {
        return "MENU";
    }

    @Override
    public void doDrawing(Graphics g) {
        backgroundCross.doDrawing(g);
        menuPrintEngine.doDrawing(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyAdapter.keyPressed(e);
    }

    @Override
    public void load() {
        Properties properties = propertiesUtil.load(fileName);
        menuModel.readParametersToProperties(properties);
    }

    @Override
    public void save() {
        propertiesUtil.save(fileName, menuModel.writeParametersToProperties());
    }
}
