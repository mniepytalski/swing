package pl.cbr.maze;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SystemManager {

    private List<FileRepository> components = new ArrayList<>();

    public void registerComponent(FileRepository resource) {
        components.add(resource);
    }

    public void startSystem() {
        components.forEach(c -> c.load());
    }

    public void stopSystem() {
        components.forEach(c -> c.save());
    }
}
