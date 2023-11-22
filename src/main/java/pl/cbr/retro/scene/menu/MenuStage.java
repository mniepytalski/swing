package pl.cbr.retro.scene.menu;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.cbr.retro.scene.FileRepository;
import pl.cbr.retro.scene.Scenery;
import pl.cbr.retro.utils.PropertiesUtil;
import pl.cbr.retro.scene.menu.gfx.MenuSimplePrintEngine;
import pl.cbr.retro.scene.menu.model.MenuModel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Properties;

@Slf4j
@Service
@AllArgsConstructor
public class MenuStage extends Scenery implements FileRepository {

    private final MenuModel menuModel;
    private final MenuKeyAdapter keyAdapter;
    private final MenuSimplePrintEngine menuPrintEngine;
    private final PropertiesUtil propertiesUtil;

    private final static String fileName = "menu.properties";

    public String getName() {
        return "MENU";
    }

    @Override
    public void doDrawing(Graphics g) {
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
