
package core.models.FlightRelations;

import core.models.Flight;
import core.models.Passenger;

public class PassengerFlight {

    public static void addFlight(Passenger passengerSelected,Flight flightSelected) {
        passengerSelected.getFlights().add(flightSelected);
    }
}

