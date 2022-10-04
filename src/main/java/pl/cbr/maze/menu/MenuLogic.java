package pl.cbr.maze.menu;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.cbr.maze.Drawing;
import pl.cbr.maze.FileRepository;
import pl.cbr.maze.menu.gfx.MenuSimplePrintEngine;
import pl.cbr.maze.menu.model.MenuModel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.io.*;
import java.util.Properties;

@Service
@Slf4j
@Getter
public class MenuLogic extends KeyAdapter implements Drawing, FileRepository {
    private final MenuModel menuModel;
    private final MenuSimplePrintEngine menuPrintEngine;

    private final static String fileName = "menu.properties";

    public MenuLogic(MenuModel menuModel, MenuSimplePrintEngine menuPrintEngine) {
        this.menuModel = menuModel;
        this.menuPrintEngine = menuPrintEngine;
    }

    public void doDrawing(Graphics g) {
        menuPrintEngine.doDrawing(g, menuModel);
    }

    public void load() {
        Properties properties = loadProperties();
        menuModel.readParametersToProperties(properties);
    }

    public void save() {
        saveProperties(menuModel.writeParametersToProperties());
    }

    private Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(fileName)) {
            properties.load(input);
        } catch (IOException io) {
            log.warn("Problem with save file: {}", fileName,io);
        }
        return properties;
    }

    private void saveProperties(Properties properties) {
        try (OutputStream output = new FileOutputStream(fileName)) {
            properties.store(output, null);
        } catch (Exception io) {
            log.error("Problem with save file: {}", fileName, io);
        }
    }
}
