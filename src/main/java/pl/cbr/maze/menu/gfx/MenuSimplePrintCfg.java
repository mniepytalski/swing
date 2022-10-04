package pl.cbr.maze.menu.gfx;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class MenuSimplePrintCfg {

    private int startX = 50;
    private int startY = 50;
    private int offsetX = 300;
    private String fontName = "Courier";
    private int fontSize = 24;
}
