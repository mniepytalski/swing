package pl.cbr.retro.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Properties;

@Slf4j
@Service
public class PropertiesUtil {

    public Properties load(String fileName) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(fileName)) {
            properties.load(input);
        } catch (IOException io) {
            log.warn("Problem with save file: {}", fileName,io);
        }
        return properties;
    }

    public void save(String fileName, Properties properties) {
        try (OutputStream output = new FileOutputStream(fileName)) {
            properties.store(output, null);
        } catch (Exception io) {
            log.error("Problem with save file: {}", fileName, io);
        }
    }
}
