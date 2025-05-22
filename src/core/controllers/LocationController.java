package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Location;
import core.models.storage.LocationStorage;

public class LocationController {

    public static Response registerLocation(String id, String name, String city, String country, String latitudeStr, String longitudeStr) {
        try {
            // Validar campos vacíos
            if (id.isEmpty() || name.isEmpty() || city.isEmpty() || country.isEmpty() || latitudeStr.isEmpty() || longitudeStr.isEmpty()) {
                return new Response("All fields must be filled.", Status.BAD_REQUEST);
            }

            // Validar formato del ID (3 letras mayúsculas)
            if (!id.matches("^[A-Z]{3}$")) {
                return new Response("Location ID must have exactly 3 uppercase letters.", Status.BAD_REQUEST);
            }

            // Parsear latitud y longitud
            double latitude = Double.parseDouble(latitudeStr);
            double longitude = Double.parseDouble(longitudeStr);

            // Validar rangos y decimales
            if (latitude < -90 || latitude > 90 || !hasAtMostFourDecimals(latitude)) {
                return new Response("Latitude must be in range [-90, 90] with at most 4 decimal places.", Status.BAD_REQUEST);
            }
            if (longitude < -180 || longitude > 180 || !hasAtMostFourDecimals(longitude)) {
                return new Response("Longitude must be in range [-180, 180] with at most 4 decimal places.", Status.BAD_REQUEST);
            }

            // Verificar unicidad del ID
            LocationStorage storage = LocationStorage.getInstance();
            for (Location loc : storage.getAll()) {
                if (loc.getAirportId().equals(id)) {
                    return new Response("A location with this ID already exists.", Status.BAD_REQUEST);
                }
            }

            // Crear y guardar ubicación
            Location location = new Location(id, name, city, country, latitude, longitude);
            storage.add(location);

            return new Response("Location registered successfully.", Status.OK);

        } catch (NumberFormatException e) {
            return new Response("Latitude and longitude must be valid numbers.", Status.BAD_REQUEST);
        } catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    private static boolean hasAtMostFourDecimals(double value) {
        String[] parts = String.valueOf(value).split("\\.");
        return parts.length == 1 || parts[1].length() <= 4;
    }
}

