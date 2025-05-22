package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Location;
import core.models.storage.LocationStorage;

public class LocationController {

    public static Response registerLocation(String id, String name, String city, String country, String latitudeStr, String longitudeStr) {
        try {
            if (id.isEmpty() || name.isEmpty() || city.isEmpty() || country.isEmpty() ||
                latitudeStr.isEmpty() || longitudeStr.isEmpty()) {
                return new Response("All fields must be filled.", Status.BAD_REQUEST);
            }

            double latitude = Double.parseDouble(latitudeStr);
            double longitude = Double.parseDouble(longitudeStr);

            LocationStorage storage = LocationStorage.getInstance();
            for (Location loc : storage.getAll()) {
                if (loc.getAirportId().equals(id)) {
                    return new Response("A location with this ID already exists.", Status.BAD_REQUEST);
                }
            }

            Location location = new Location(id, name, city, country, latitude, longitude);
            storage.add(location);

            return new Response("Location registered successfully.", Status.OK);

        } catch (NumberFormatException e) {
            return new Response("Latitude and longitude must be valid numbers.", Status.BAD_REQUEST);
        } catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
