package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.JsonReaders.ReadJsonPlane;
import core.models.Plane;
import core.models.storage.PlaneStorage;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlaneController {
    
    public static void loadPlanesFromJson() {
        ReadJsonPlane reader = new ReadJsonPlane();
        ArrayList<Plane> planes = reader.read("json/planes.json");

        PlaneStorage storage = PlaneStorage.getInstance();
        for (Plane plane : planes) {
            storage.add(plane);
        }
    }

    public static Response registerPlane(String id, String brand, String model, String capacityStr, String airline,
                                         String yearStr, String type) {
        try {
            if (id.isEmpty() || brand.isEmpty() || model.isEmpty() || capacityStr.isEmpty() ||
                airline.isEmpty() || yearStr.isEmpty() || type.isEmpty()) {
                return new Response("All fields must be filled.", Status.BAD_REQUEST);
            }

            if (!id.matches("^[A-Z]{2}\\d{5}$")) {
                return new Response("Invalid plane ID format. Must be XXYYYYY (2 uppercase letters followed by 5 digits).", Status.BAD_REQUEST);
            }

            int capacity = Integer.parseInt(capacityStr);
            if (capacity <= 0) {
                return new Response("Capacity must be greater than 0.", Status.BAD_REQUEST);
            }

            int year = Integer.parseInt(yearStr);
            int currentYear = Year.now().getValue();
            if (year < 1900 || year > currentYear) {
                return new Response("Year must be between 1900 and " + currentYear + ".", Status.BAD_REQUEST);
            }

            List<String> validTypes = Arrays.asList("comercial", "carga");
            if (!validTypes.contains(type.toLowerCase())) {
                return new Response("Invalid plane type. Must be: comercial, carga.", Status.BAD_REQUEST);
            }

            PlaneStorage storage = PlaneStorage.getInstance();
            for (Plane p : storage.getAll()) {
                if (p.getId().equals(id)) {
                    return new Response("A plane with this ID already exists.", Status.BAD_REQUEST);
                }
            }

            Plane plane = new Plane(id, brand, model, capacity, airline);
            storage.add(plane); // Agregado aquí

            return new Response("Plane registered successfully.", Status.OK);

        } catch (NumberFormatException e) {
            return new Response("Capacity and year must be valid numbers.", Status.BAD_REQUEST);
        } catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}


