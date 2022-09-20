package pl.cbr.ucho.menu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import pl.cbr.ucho.menu.config.MenuConfig;

@Service
@Data
@AllArgsConstructor
public class MenuModel {
    private final MenuConfig menuConfig;

}
