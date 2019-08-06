package Marcel.entities;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LocalProjectLocation {

    private Path localProjectLocation;

    public LocalProjectLocation(String localProjectLocation){
        this.localProjectLocation = Paths.get(localProjectLocation);
    }

    public Path getLocalProjectLocation() {
        return localProjectLocation;
    }

    public void setLocalProjectLocation(Path localProjectLocation) {
        this.localProjectLocation = localProjectLocation;
    }

    public String getToString(){
        return localProjectLocation.toString();
    }
}
