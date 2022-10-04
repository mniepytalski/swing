package pl.cbr.maze;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class GameState {
    private SystemState systemState = SystemState.MENU;
}
