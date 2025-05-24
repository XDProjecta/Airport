
package core.models.storage;

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
