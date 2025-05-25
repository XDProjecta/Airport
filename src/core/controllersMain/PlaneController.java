package core.controllersMain;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.JsonReaders.ReadJsonPlane;
import core.models.Plane;
import core.models.storage.PlaneStorage;

import java.time.Year;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PlaneController {

    public static Response registerPlane(String id, String brand, String model, String capacityStr, String airline) {
        try {
            if (id.isEmpty() || brand.isEmpty() || model.isEmpty() || capacityStr.isEmpty() ||
                airline.isEmpty()) {
                return new Response("All fields must be filled.", Status.BAD_REQUEST);
            }

            if (!id.matches("^[A-Z]{2}\\d{5}$")) {
                return new Response("Invalid plane ID format. Must be XXYYYYY (2 uppercase letters followed by 5 digits).", Status.BAD_REQUEST);
            }

            int capacity = Integer.parseInt(capacityStr);
            if (capacity <= 0) {
                return new Response("Capacity must be greater than 0.", Status.BAD_REQUEST);
            }

            PlaneStorage storage = PlaneStorage.getInstance();
            for (Plane p : storage.getAll()) {
                if (p.getId().equals(id)) {
                    return new Response("A plane with this ID already exists.", Status.BAD_REQUEST);
                }
            }

            Plane plane = new Plane(id, brand, model, capacity, airline);
            storage.add(plane); // Agregado aquí

            return new Response("Plane registered", Status.OK, plane.copy());

        } catch (NumberFormatException e) {
            return new Response("Capacity and year must be valid numbers.", Status.BAD_REQUEST);
        } catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
    
}


