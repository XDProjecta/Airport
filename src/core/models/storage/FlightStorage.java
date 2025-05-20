
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
        
    @Override
    public void add (Flight flight) {
        this.flights.add(flight);
    }

    @Override
    public ArrayList getAll() {
        return flights;
    }

}
