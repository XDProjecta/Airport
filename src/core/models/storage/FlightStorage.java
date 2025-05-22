
package core.models.storage;

import core.models.Flight;
import java.util.ArrayList;

public class FlightStorage implements StorageInterface<Flight> {
private ArrayList<Flight> flights = new ArrayList();
  private static FlightStorage instance;
    
        public static FlightStorage getInstance() {
        if (instance == null) {
            instance = new FlightStorage();
        }
        return instance;
    }
        public Flight getById(String id) {
            for (Flight flight : this.getAll()) {
                if (flight.getId().equals(id)) {
                return flight;
        }
    }
    return null;
}

        
    @Override
    public void add (Flight flight) {
        this.flights.add(flight);
    }

    @Override
    public ArrayList<Flight> getAll() {
        return flights;
    }

}
