package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.Location;
import core.models.Plane;
import core.models.storage.FlightStorage;

import java.time.LocalDateTime;
import java.util.List;

public class FlightController {

    public static Response registerFlight(String id, Plane plane, Location departure,
                                          Location arrival, Location scale,
                                          LocalDateTime departureDate,
                                          int hoursDurationsArrival, int minutesDurationsArrival,
                                          int hoursDurationsScale, int minutesDurationsScale) {
        try {
            if (id.isEmpty() || plane == null || departure == null || arrival == null || departureDate == null) {
                return new Response("All fields must be filled", Status.BAD_REQUEST);
            }

            FlightStorage storage = FlightStorage.getInstance();
            List<Flight> flights = storage.getAll();

            for (Flight f : flights) {
                if (f.getId().equals(id)) {
                    return new Response("A flight with this ID already exists", Status.BAD_REQUEST);
                }
            }

            Flight flight;
            if (scale == null) {
                flight = new Flight(id, plane, departure, arrival, departureDate,
                        hoursDurationsArrival, minutesDurationsArrival);
            } else {
                flight = new Flight(id, plane, departure, scale, arrival, departureDate,
                        hoursDurationsArrival, minutesDurationsArrival,
                        hoursDurationsScale, minutesDurationsScale);
            }

            storage.add(flight);
            return new Response("Flight registered successfully", Status.OK);

        } catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}


