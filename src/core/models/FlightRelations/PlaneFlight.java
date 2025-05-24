
package core.models.FlightRelations;

import core.models.Flight;
import core.models.Plane;

public class PlaneFlight {
    public static void addFlight(Plane plane, Flight flight) {
        plane.getFlights().add(flight);
    }
}