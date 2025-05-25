package core.controllersMain;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.FlightRelations.DelayFlight;
import core.models.storage.FlightStorage;
import java.util.ArrayList;

public class DelayFlightController {

    public static Response delayFlight(String id, String hour, String minute) {
        try {
            FlightStorage storage = FlightStorage.getInstance();
            ArrayList<Flight> flights = storage.getAll();
            Flight delayFlight = null;

            int hourInt = 0, minuteInt = 0;

            //Validaciones
            if (flights == null || flights.isEmpty()) {
                return new Response("There are no flights registered", Status.NO_CONTENT);
            }

            if (id == null || id.trim().isEmpty() || id.equals("Flight")) {
                return new Response("Please choose a valid flight ID", Status.BAD_REQUEST);
            }

            if (hour == null || hour.trim().isEmpty()) {
                return new Response("Choose an hour", Status.BAD_REQUEST);
            }
            try {
                hourInt = Integer.parseInt(hour);
                if (hourInt <= 0) {
                    return new Response("Hour must be greater than 0", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Hour must be numeric", Status.BAD_REQUEST);
            }

            if (minute == null || minute.trim().isEmpty()) {
                return new Response("Choose a minute", Status.BAD_REQUEST);
            }
            try {
                minuteInt = Integer.parseInt(minute);
                if (minuteInt < 0) {
                    return new Response("Minute must be 0 or more", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Minute must be numeric", Status.BAD_REQUEST);
            }

            //Busqueda de vuelo por ID
            for (Flight flight : flights) {
                if (flight.getId().equals(id)) {
                    delayFlight = flight;
                    break;
                }
            }

            if (delayFlight == null) {
                return new Response("Flight ID not found", Status.NOT_FOUND);
            }

            DelayFlight.delay(delayFlight, hourInt, minuteInt);

            return new Response("Flight successfully delayed", Status.OK);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
