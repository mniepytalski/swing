package pl.cbr.ucho.menu;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class MenuCfg {

    private int startX = 50;
    private int startY = 50;
    private String fontName = "Courier";
    private int fontSize = 24;
}
