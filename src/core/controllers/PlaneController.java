package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Plane;
import core.models.storage.PlaneStorage;

public class PlaneController {

    public static Response registerPlane(String id, String brand, String model, String capacityStr, String airline) {
        try {
            if (id.isEmpty() || brand.isEmpty() || model.isEmpty() || capacityStr.isEmpty() || airline.isEmpty()) {
                return new Response("All fields must be filled.", Status.BAD_REQUEST);
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
            storage.add(plane);

            return new Response("Plane registered successfully.", Status.OK);

        } catch (NumberFormatException e) {
            return new Response("Capacity must be a valid number.", Status.BAD_REQUEST);
        } catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}

