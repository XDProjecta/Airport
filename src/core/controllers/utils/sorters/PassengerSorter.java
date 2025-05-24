
package core.controllers.utils.sorters;

import core.models.Passenger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class PassengerSorter {

    private ArrayList<Passenger> passengers;
    
    public static ArrayList<Passenger> passengersSort(ArrayList<Passenger> passengers) {
        Collections.sort(passengers, Comparator.comparingLong(Passenger::getId));
       return passengers;
    }
}
