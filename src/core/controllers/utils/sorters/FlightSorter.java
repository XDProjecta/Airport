
package core.controllers.utils.sorters;

import core.models.Flight;
import java.util.ArrayList;
import java.util.Comparator;

public class FlightSorter {
    public static ArrayList<Flight> getSortedFlights(ArrayList<Flight> flights) {
        ArrayList<Flight> clones = new ArrayList<>();
        for (Flight f : flights) clones.add(f.copy());
        clones.sort(Comparator.comparing(Flight::getId));
        return clones;
    }
}
