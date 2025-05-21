//POR EVALUAR AQUÍ NO HAY SOLID
package core.models.storage;

/*import core.models.Passenger;
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

    @Override
    public void add(Object genericItem) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}*/
import core.models.Passenger;
import java.util.ArrayList;

public class PassengerStorage implements StorageInterface<Passenger> {

    private ArrayList<Passenger> passengers = new ArrayList();
    private static PassengerStorage instance;

    public static PassengerStorage getInstance() {
        if (instance == null) {
            instance = new PassengerStorage();
        }
        return instance;
    }

    @Override
    public void add(Passenger passenger) {
        this.passengers.add(passenger);
    }

    @Override
    public ArrayList<Passenger> getAll() {
        return passengers;
    }



}
