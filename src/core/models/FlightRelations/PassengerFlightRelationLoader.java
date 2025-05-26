package core.models.FlightRelations;

import core.models.Flight;
import core.models.Passenger;
import core.models.storage.FlightStorage;
import core.models.storage.PassengerStorage;

import java.util.ArrayList;

public class PassengerFlightRelationLoader {

    public static void linkRelations() {
        ArrayList<Passenger> passengers = PassengerStorage.getInstance().getAll();
        ArrayList<Flight> flights = FlightStorage.getInstance().getAll();

        for (Passenger passenger : passengers) {
            ArrayList<Flight> flightsOfPassenger = passenger.getFlights(); // Ya cargadas desde JSON

            for (Flight flight : flightsOfPassenger) {
                Flight realFlight = FlightStorage.getInstance().findById(flight.getId());

                if (realFlight != null) {
                    // Reestablecer conexión real entre vuelo y pasajero
                    realFlight.addPassenger(passenger);
                    passenger.addFlight(realFlight); // Esto es seguro, ya lo valida internamente
                }
            }
        }
    }
}

