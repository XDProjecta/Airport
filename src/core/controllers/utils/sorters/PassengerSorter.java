
package core.controllers.utils.sorters;

import core.models.Passenger;
import core.models.storage.PassengerStorage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class PassengerSorter {

    public static ArrayList<Passenger> getSortedPassengers() {
        ArrayList<Passenger> passengers = PassengerStorage.getInstance().getAll();
        ArrayList<Passenger> clones = new ArrayList<>();
        for (Passenger p : passengers) {
            clones.add(p.copy()); // Patrón Prototype
        }
        clones.sort(Comparator.comparingLong(Passenger::getId));
        return clones;
    }
}
