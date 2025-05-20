
package core.models.storage;

import core.models.Passenger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PassengerStorage implements StorageInterface {
    private List<Passenger> passengers;
    
    public PassengerStorage() {
        passengers = new ArrayList<>();
    }
    
    public boolean addPassenger(Passenger passenger) {
        if (passenger == null || passengerExists(passenger.getId())) {
            return false;
        }
        passengers.add(passenger);
        Collections.sort(passengers, Comparator.comparingLong(Passenger::getId));
        return true;
    }
    
    public Passenger getPassenger(long id) {
        return passengers.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    public List<Passenger> getAllPassengers() {
        return new ArrayList<>(passengers);
    }
    
    public boolean updatePassenger(Passenger passenger) { // Implementación de actualización
        return true;
    }
    
    private boolean passengerExists(long id) {
        return passengers.stream().anyMatch(p -> p.getId() == id);
    }
}