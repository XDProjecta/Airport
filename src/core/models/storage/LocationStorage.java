package core.models.storage;

import core.models.Location;
import java.util.ArrayList;

public class LocationStorage implements StorageInterface<Location> {
private ArrayList<Location> locations = new ArrayList();
    @Override
    public void add(Location location) {
        this.locations.add(location);
    }

    @Override
    public ArrayList getAll() {
        return locations;
    }

}
