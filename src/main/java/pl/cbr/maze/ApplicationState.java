package pl.cbr.maze;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Service
public class ApplicationState {
    private SystemState systemState = SystemState.MENU;
    private Set<String> stages = new HashSet<>();

    public ApplicationState() {
        setStages("MENU");
    }

    public void setStages(String ... names) {
        stages = Arrays.stream(names).collect(Collectors.toSet());
    }
}
