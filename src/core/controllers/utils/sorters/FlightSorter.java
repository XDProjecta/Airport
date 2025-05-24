
package core.controllers.utils.sorters;

import core.models.Flight;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FlightSorter {
    private ArrayList<Flight> flights;
    
    public static ArrayList<Flight> flightsSorter(ArrayList<Flight> flights) {
        Collections.sort(flights, Comparator.comparing(Flight::getId));
       return flights;
    }
}
