package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.*;
import core.models.JsonReaders.ReadJsonFlight;
import core.models.storage.FlightStorage;
import core.models.storage.LocationStorage;
import core.models.storage.PlaneStorage;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class FlightController {

    public static void loadFlightsFromJson() {
        ReadJsonFlight reader = new ReadJsonFlight();
        ArrayList<Flight> flights = reader.read("json/flights.json");

        FlightStorage storage = FlightStorage.getInstance();
        for (Flight fli : flights) {
            storage.add(fli);
        }
    }

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
            // Validaciones básicas, si está vacío en este caso y si no es el formato adecuado.
            if (id == null || id.isEmpty() || !id.matches("[A-Z]{3}\\d{3}")) {
                return new Response("Invalid ID format. It must be in format XXXYYY.", Status.BAD_REQUEST);
            }

            if (planeId == null || departureId == null || arrivalId == null ||
                yearStr == null || monthStr == null || dayStr == null ||
                hourStr == null || minuteStr == null || hoursDurationsArrivalStr == null ||
                minutesDurationsArrivalStr == null || hoursDurationsScaleStr == null || minutesDurationsScaleStr == null) {
                return new Response("All fields must be filled.", Status.BAD_REQUEST);
            }

            if (departureId.equals(arrivalId)) {
                return new Response("Departure and arrival locations must be different.", Status.BAD_REQUEST);
            }

            // Validar si el ID ya existe
            FlightStorage storage = FlightStorage.getInstance();
            if (storage.findById(id) != null) {
                return new Response("A flight with this ID already exists.", Status.BAD_REQUEST);
            }

            // Obtener objetos reales
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

            // Parsear fecha y duración
            int year = Integer.parseInt(yearStr);
            int month = Integer.parseInt(monthStr);
            int day = Integer.parseInt(dayStr);
            int hour = Integer.parseInt(hourStr);
            int minute = Integer.parseInt(minuteStr);

            int hoursDurationsArrival = Integer.parseInt(hoursDurationsArrivalStr);
            int minutesDurationsArrival = Integer.parseInt(minutesDurationsArrivalStr);
            int hoursDurationsScale = Integer.parseInt(hoursDurationsScaleStr);
            int minutesDurationsScale = Integer.parseInt(minutesDurationsScaleStr);

            if (hoursDurationsArrival < 0 || minutesDurationsArrival < 0 ||
                (hoursDurationsArrival == 0 && minutesDurationsArrival == 0)) {
                return new Response("Flight duration must be greater than 00:00.", Status.BAD_REQUEST);
            }

            if (scale == null && (hoursDurationsScale != 0 || minutesDurationsScale != 0)) {
                return new Response("If scale is null, scale duration must be 00:00.", Status.BAD_REQUEST);
            }

            LocalDateTime departureDate;
            try {
                departureDate = LocalDateTime.of(year, month, day, hour, minute);
            } catch (DateTimeParseException e) {
                return new Response("Invalid departure date or time.", Status.BAD_REQUEST);
            }

            // Crear vuelo
            Flight flight;
            if (scale == null) {
                flight = new Flight(id, plane, departure, arrival, departureDate, hoursDurationsArrival, minutesDurationsArrival);
            } else {
                flight = new Flight(id, plane, departure, arrival, scale, departureDate,
                        hoursDurationsArrival, minutesDurationsArrival,
                        hoursDurationsScale, minutesDurationsScale);
            }

            // Guardar vuelo
            storage.add(flight);
            return new Response("Flight registered successfully.", Status.OK);

        } catch (NumberFormatException e) {
            return new Response("Numeric values are required in date and duration fields.", Status.BAD_REQUEST);
        } catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
