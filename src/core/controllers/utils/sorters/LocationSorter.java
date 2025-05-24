
package core.controllers.utils.sorters;

import core.models.Location;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LocationSorter {
    private ArrayList<Location> locations;
    
    public static ArrayList<Location> locations(ArrayList<Location> locations) {
        Collections.sort(locations, Comparator.comparing(Location::getAirportId));
       return locations;
    }
}
