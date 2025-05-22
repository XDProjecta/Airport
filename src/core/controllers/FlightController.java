package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.*;
import core.models.storage.FlightStorage;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class FlightController {

    public static Response registerFlight(String id, Plane plane, Location departure,
                                          Location arrival, Location scale,
                                          LocalDateTime departureDate,
                                          int hoursDurationsArrival, int minutesDurationsArrival,
                                          int hoursDurationsScale, int minutesDurationsScale) {
        try {
            // Validaciones básicas
            if (id == null || id.isEmpty() || !id.matches("[A-Z]{3}\\d{3}")) {
                return new Response("Invalid ID format. It must be in format XXXYYY.", Status.BAD_REQUEST);
            }
            if (plane == null || departure == null || arrival == null || departureDate == null) {
                return new Response("Required fields must not be null.", Status.BAD_REQUEST);
            }
            if (departure.equals(arrival)) {
                return new Response("Departure and arrival locations must be different.", Status.BAD_REQUEST);
            }

            if (hoursDurationsArrival < 0 || minutesDurationsArrival < 0 || (hoursDurationsArrival == 0 && minutesDurationsArrival == 0)) {
                return new Response("Flight duration must be greater than 00:00.", Status.BAD_REQUEST);
            }

            if (scale == null && (hoursDurationsScale != 0 || minutesDurationsScale != 0)) {
                return new Response("If scale is not present, scale duration must be 00:00.", Status.BAD_REQUEST);
            }

            // Validar ID único
            FlightStorage storage = FlightStorage.getInstance();
            for (Flight f : storage.getAll()) {
                if (f.getId().equals(id)) {
                    return new Response("A flight with this ID already exists.", Status.BAD_REQUEST);
                }
            }

            // Crear y guardar vuelo
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
            return new Response("Flight registered successfully.", Status.OK);

        } catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response delayFlight(String flightId, int delayHours, int delayMinutes) {
        try {
            if (delayHours < 0 || delayMinutes < 0 || (delayHours == 0 && delayMinutes == 0)) {
                return new Response("Delay time must be greater than 00:00.", Status.BAD_REQUEST);
            }

            FlightStorage storage = FlightStorage.getInstance();
            Flight flight = storage.getById(flightId);

            if (flight == null) {
                return new Response("Flight not found.", Status.NOT_FOUND);
            }

            flight.delay(delayHours, delayMinutes);
            return new Response("Flight delayed successfully.", Status.OK);
        } catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response addPassengerToFlight(String flightId, Passenger passenger) {
        try {
            if (passenger == null) {
                return new Response("Passenger must not be null.", Status.BAD_REQUEST);
            }

            FlightStorage storage = FlightStorage.getInstance();
            Flight flight = storage.getById(flightId);

            if (flight == null) {
                return new Response("Flight not found.", Status.NOT_FOUND);
            }

            boolean added = flight.addPassenger(passenger);
            if (!added) {
                return new Response("Passenger could not be added (possibly already on the flight).", Status.BAD_REQUEST);
            }

            return new Response("Passenger added successfully.", Status.OK);

        } catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getAllFlightsSortedByDate() {
        try {
            List<Flight> sortedFlights = FlightStorage.getInstance().getAll();
            sortedFlights.sort(Comparator.comparing(Flight::getDepartureDate));

            // FALTA Patrón Prototype (copias) gabriel cachón

            
            return new Response("Flights retrieved successfully.", Status.OK);
        } catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}


