package core.models.storage;

import core.models.Location;
import java.util.ArrayList;

public class LocationStorage implements StorageInterface<Location> {

    private ArrayList<Location> locations = new ArrayList();
    private static LocationStorage instance;

    public static LocationStorage getInstance() {
        if (instance == null) {
            instance = new LocationStorage();
        }
        return instance;
    }

    @Override
    public void add(Location location) {
        this.locations.add(location);
    }

    @Override
    public ArrayList<Location> getAll() {
        return locations;
    }

    public Location findById(String id) {
        for (Location location : this.locations) {
            if (location.getAirportId().equals(id)) {
                return location;
            }
        }
        return null;
    }

}
