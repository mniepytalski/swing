package pl.cbr.retro.scene;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Service
public class ActualScenery {
    private Set<String> stages = new HashSet<>();

    public boolean contains(Scenery stage) {
        return stages.contains(stage.getName());
    }

    public void setScenery(String ... names) {
        stages = Arrays.stream(names).collect(Collectors.toSet());
    }
}
