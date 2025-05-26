package core.controllersMain;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.*;
import core.models.storage.FlightStorage;
import core.models.storage.LocationStorage;
import core.models.storage.PlaneStorage;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class FlightController {

    public static Response registerFlight(
            String id,
            String planeId,
            String departureId,
            String arrivalId,
            String scaleId,
            String yearStr,
            String monthStr,
            String dayStr,
            String hourStr,
            String minuteStr,
            String hoursDurationsArrivalStr,
            String minutesDurationsArrivalStr,
            String hoursDurationsScaleStr,
            String minutesDurationsScaleStr
    ) {
        try {
            if (id == null || id.isEmpty() || !id.matches("[A-Z]{3}\\d{3}")) {
                return new Response("Invalid ID format. It must be like ABC123.", Status.BAD_REQUEST);
            }

            if (departureId.equals(arrivalId)) {
                return new Response("Departure and arrival locations must be different.", Status.BAD_REQUEST);
            }

            FlightStorage storage = FlightStorage.getInstance();
            if (storage.findById(id) != null) {
                return new Response("Flight with this ID already exists.", Status.BAD_REQUEST);
            }

            Plane plane = PlaneStorage.getInstance().findById(planeId);
            if (plane == null) return new Response("Plane not found.", Status.BAD_REQUEST);

            Location departure = LocationStorage.getInstance().findById(departureId);
            if (departure == null) return new Response("Departure location not found.", Status.BAD_REQUEST);

            Location arrival = LocationStorage.getInstance().findById(arrivalId);
            if (arrival == null) return new Response("Arrival location not found.", Status.BAD_REQUEST);

            Location scale = null;
            if (scaleId != null && !scaleId.equals("Location")) {
                scale = LocationStorage.getInstance().findById(scaleId);
                if (scale == null) return new Response("Scale location not found.", Status.BAD_REQUEST);
            }

            // === Parsear fecha ===
            int year = Integer.parseInt(yearStr);
            int month = Integer.parseInt(monthStr);
            int day = Integer.parseInt(dayStr);
            int hour = Integer.parseInt(hourStr);
            int minute = Integer.parseInt(minuteStr);

            LocalDateTime departureDate;
            try {
                departureDate = LocalDateTime.of(year, month, day, hour, minute);
            } catch (DateTimeParseException e) {
                return new Response("Invalid departure date or time.", Status.BAD_REQUEST);
            }

            //TIME LLEGADA
            int hoursArrival = Integer.parseInt(hoursDurationsArrivalStr);
            int minutesArrival = Integer.parseInt(minutesDurationsArrivalStr);
            if (hoursArrival < 0 || minutesArrival < 0 || (hoursArrival == 0 && minutesArrival == 0)) {
                return new Response("Arrival duration must be greater than 00:00.", Status.BAD_REQUEST);
            }

            //TIME ESCALA
            int hoursScale = Integer.parseInt(hoursDurationsScaleStr);
            int minutesScale = Integer.parseInt(minutesDurationsScaleStr);
            if (scale == null && (hoursScale > 0 || minutesScale > 0)) {
                return new Response("If scale is null, duration must be 00:00.", Status.BAD_REQUEST);
            }

           
            Flight flight;
            if (scale == null) {
                flight = new Flight(id, plane, departure, arrival, departureDate, hoursArrival, minutesArrival);
            } else {
                flight = new Flight(id, plane, departure, arrival, scale, departureDate, hoursArrival, minutesArrival, hoursScale, minutesScale);
            }

            storage.add(flight);
            plane.addFlight(flight);

            return new Response("Flight registered successfully", Status.OK, flight.copy());

        } catch (NumberFormatException e) {
            return new Response("All time and duration fields must be numeric.", Status.BAD_REQUEST);
        } catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
