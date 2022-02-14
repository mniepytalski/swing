package pl.cbr.ucho;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.cbr.ucho.menu.MenuKeyAdapter;
import pl.cbr.ucho.menu.MenuLogic;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
@AllArgsConstructor
@Component
public class Board extends JPanel implements ActionListener, Drawing {

    private final SystemTimer systemTimer;
    private final SystemState systemState;
    private final MenuLogic gameMenu;

    @PostConstruct
    private void initBoard() {
        setFocusable(true);
//        addKeyListener(new GameKeyAdapter());
        systemTimer.init(this);
        systemTimer.start();
        addKeyListener(new MenuKeyAdapter(gameMenu));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void doDrawing(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(0,0,systemState.getStep(),100);
        systemState.setStep(systemState.getStep()+10);
        if ( systemState.getStep()>2000 ) {
            systemState.setStep(0);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        gameMenu.doDrawing(g);
    }
}
