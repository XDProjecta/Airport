
package core.models.FlightRelations;

import core.models.Flight;

public class DelayFlight {
    public static void delay(Flight flight, int hour, int minutes){
        System.out.println(flight.getDepartureDate());
        flight.setDepartureDate(flight.getDepartureDate().plusHours(hour).plusMinutes(minutes));
        System.out.println(flight.getDepartureDate());
    }
}