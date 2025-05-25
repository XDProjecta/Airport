package core.controllers.Tables;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Passenger;
import core.models.storage.PassengerStorage;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.table.DefaultTableModel;

public class PassengerTableController {
    public static Response updateTable(DefaultTableModel passengerTable) {
        try {
            passengerTable.setRowCount(0);

            PassengerStorage storage = PassengerStorage.getInstance();
            ArrayList<Passenger> passengers = storage.getAll();

            if (passengers == null || passengers.isEmpty()) {
                return new Response("Error: The list is empty", Status.NO_CONTENT);
            }

            // usamos PROTOTIPE para clonar un pasajero con el método .copy()
            ArrayList<Passenger> clones = new ArrayList<>();
            for (Passenger p : passengers) {
                clones.add(p.copy());
            }

            // Ordenamos los pasajeros clonados
            clones.sort(Comparator.comparingLong(Passenger::getId));

            for (Passenger p : clones) {
                passengerTable.addRow(new Object[]{
                    p.getId(),
                    p.getFirstname(),
                    p.getLastname(),
                    p.getBirthDate(),
                    p.calculateAge(),
                    p.generateFullPhone(),
                    p.getCountry(),
                    p.getNumFlights()
                });
            }

            return new Response("Passenger list loaded successfully", Status.OK);
        } catch (Exception e) {
            return new Response("Internal error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
