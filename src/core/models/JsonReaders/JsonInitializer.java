package core.models.JsonReaders;
import core.models.*;
import core.models.storage.*;

import java.util.ArrayList;

public class JsonInitializer {
    public static void loadAll() {
        loadPassengers();
        loadPlanes();
        loadLocations();
        loadFlights();
    }

    public static void loadPassengers() {
        ReadJsonPassenger reader = new ReadJsonPassenger();
        ArrayList<Passenger> passengers = reader.read("json/passengers.json");
        PassengerStorage storage = PassengerStorage.getInstance();
        for (Passenger p : passengers) storage.add(p);
    }

    public static void loadPlanes() {
        ReadJsonPlane reader = new ReadJsonPlane();
        ArrayList<Plane> planes = reader.read("json/planes.json");
        PlaneStorage storage = PlaneStorage.getInstance();
        for (Plane p : planes) storage.add(p);
    }

    public static void loadLocations() {
        ReadJsonLocation reader = new ReadJsonLocation();
        ArrayList<Location> locations = reader.read("json/locations.json");
        LocationStorage storage = LocationStorage.getInstance();
        for (Location l : locations) storage.add(l);
    }

    public static void loadFlights() {
        ReadJsonFlight reader = new ReadJsonFlight();
        ArrayList<Flight> flights = reader.read("json/flights.json");
        FlightStorage storage = FlightStorage.getInstance();
        for (Flight f : flights) storage.add(f);
    }
}

