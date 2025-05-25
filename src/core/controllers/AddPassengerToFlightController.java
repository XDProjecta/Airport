/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.FlightRelations.PassengerFlight;
import core.models.Passenger;
import core.models.storage.FlightStorage;
import core.models.storage.PassengerStorage;

/**
 *
 * @author jmanu
 */
public class AddPassengerToFlightController {

    public static Response addPassengerToFlight(String passengerIdStr, String flightIdStr) {
        try {
            // Validar ID del pasajero
            if (passengerIdStr == null || passengerIdStr.trim().isEmpty()) {
                return new Response("Passenger ID must not be empty.", Status.BAD_REQUEST);
            }

            long passengerId;
            try {
                passengerId = Long.parseLong(passengerIdStr);
            } catch (NumberFormatException ex) {
                return new Response("Passenger ID must be numeric.", Status.BAD_REQUEST);
            }

            PassengerStorage passengerStorage = PassengerStorage.getInstance();
            Passenger passenger = passengerStorage.findById(passengerId);
            if (passenger == null) {
                return new Response("Passenger not found.", Status.NOT_FOUND);
            }

            // Validar ID del vuelo
            if (flightIdStr == null || flightIdStr.trim().isEmpty() || flightIdStr.equals("Flight")) {
                return new Response("Please select a valid flight.", Status.BAD_REQUEST);
            }

            FlightStorage flightStorage = FlightStorage.getInstance();
            Flight flight = flightStorage.getById(flightIdStr);
            if (flight == null) {
                return new Response("Flight not found.", Status.NOT_FOUND);
            }

            // Verificar si el pasajero ya está en ese vuelo
            if (flight.getPassengers().contains(passenger)) {
                return new Response("Passenger is already assigned to this flight.", Status.BAD_REQUEST);
            }

            // Añadir vuelo al pasajero
            PassengerFlight.addFlight(passenger, flight);
            return new Response("Flight added successfully.", Status.OK);

        } catch (Exception ex) {
            return new Response("Unexpected error: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}

