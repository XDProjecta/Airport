package core.controllersMain;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.Passenger;
import core.models.storage.FlightStorage;
import core.models.storage.PassengerStorage;

public class PassengerFlightController {

    public static Response addFlight(String passengerIdStr, String flightId) {
        try {
            long passengerId = Long.parseLong(passengerIdStr);

            PassengerStorage passengerStorage = PassengerStorage.getInstance();
            FlightStorage flightStorage = FlightStorage.getInstance();

            Passenger passenger = passengerStorage.findById(passengerId);
            if (passenger == null) {
                return new Response("Passenger not found", Status.BAD_REQUEST);
            }

            Flight flight = flightStorage.findById(flightId);
            if (flight == null) {
                return new Response("Flight not found", Status.BAD_REQUEST);
            }

            if (passenger.getFlights().contains(flight)) {
                return new Response("Passenger is already registered to this flight", Status.BAD_REQUEST);
            }

        passenger.getFlights().add(flight);
        flight.getPassengers().add(passenger);

            return new Response("Flight added successfully", Status.OK);

        } catch (NumberFormatException e) {
            return new Response("Invalid passenger ID format", Status.BAD_REQUEST);
        } catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

}
