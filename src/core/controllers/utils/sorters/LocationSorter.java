
package core.controllers.utils.sorters;

import core.models.Location;
import core.models.storage.LocationStorage;
import java.util.ArrayList;
import java.util.Comparator;

public class LocationSorter {
    public static ArrayList<Location> getSortedLocationCopies() {
        ArrayList<Location> locations = LocationStorage.getInstance().getAll();
        ArrayList<Location> sortedCopies = new ArrayList<>();
        for (Location loc : locations) {
            sortedCopies.add(loc.copy());
        }
        sortedCopies.sort(Comparator.comparing(Location::getAirportId));
        return sortedCopies;
    }
}
