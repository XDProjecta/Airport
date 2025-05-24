package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.FlightRelations.PassengerFlight;
import core.models.Passenger;
import core.models.storage.FlightStorage;
import core.models.storage.PassengerStorage;
import java.util.ArrayList;

public class PassengerFlightController {

    public static Response addFlight(String id, String idFlight) {
        PassengerStorage passengerRegister = PassengerStorage.getInstance();
        ArrayList<Passenger> passengers = passengerRegister.getAll();
        FlightStorage flightRegister = FlightStorage.getInstance();
        ArrayList<Flight> flights = flightRegister.getAll();
        Passenger passengerSelected = null;
        Flight flightSelected = null;
        try {
            long idPassenger;
            try {
                idPassenger = Long.parseLong(id);
            } catch (NumberFormatException ex) {
                return new Response("Select a passenger on administration", Status.BAD_REQUEST);
            }
            if (passengers.isEmpty()) {
                return new Response("No passengers available", Status.BAD_REQUEST);
            }

            for (Passenger passenger : passengers) {
                passengerSelected = passenger;
            }

            if (idFlight.equals("Flight")) {
                return new Response("Select a flight", Status.BAD_REQUEST);
            }

            if (flights.isEmpty()) {
                return new Response("No flights available", Status.BAD_REQUEST);
            }
            for (Passenger passenger : passengers) {
                passengerSelected = passenger;
            }
            
            for(Flight flight: flights){
                flightSelected = flight;
            }

            PassengerFlight.addFlight(passengerSelected, flightSelected);
            return new Response("Flight added successfully", Status.OK);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
