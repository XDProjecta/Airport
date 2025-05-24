package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.FlightRelations.DelayFlight;
import core.models.storage.FlightStorage;
import java.util.ArrayList;

/**
 *
 * @author Laura
 */
public class DelayFlightController {

    public static Response delayFlight(String id, String hour, String minute) {
        try {
            FlightStorage storage = FlightStorage.getInstance();
            ArrayList<Flight> flights = storage.getAll();
            Flight delayFlight = null;

            System.out.println("Size: " + flights.size());
            int hourInt = 0, minuteInt = 0;

            if(flights==null){
                return new Response ("There are not flights", Status.NO_CONTENT);
            }
            
            if (id.equals("Flight")) {
                return new Response("Choose an ID", Status.OK);
            }

            try {
                hourInt = Integer.parseInt(hour);
                if (hour.equals("")) {
                    return new Response("Choose an hour", Status.OK);
                }
            } catch (NumberFormatException ex) {
                return new Response("Hour must be numeric", Status.BAD_REQUEST);
            }

            try {
                minuteInt = Integer.parseInt(minute);
                if (hour.equals("")) {
                    return new Response("Choose a minute", Status.OK);
                }
            } catch (NumberFormatException ex) {
                return new Response("Minute must be numeric", Status.BAD_REQUEST);
            }
            
                for (Flight flight : flights) {
                    if (flight.getId().equals(id)) {
                        delayFlight = flight;
                    }
                }

            DelayFlight.delay(delayFlight, hourInt, minuteInt);

            return new Response("Flight successfully delayed", Status.OK);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
