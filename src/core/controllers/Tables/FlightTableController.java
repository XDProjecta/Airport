package core.controllers.Tables;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.storage.FlightStorage;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.table.DefaultTableModel;

public class FlightTableController {
    public static Response updateTable(DefaultTableModel flightTable) {
        try {
            flightTable.setRowCount(0);

            FlightStorage storage = FlightStorage.getInstance();
            ArrayList<Flight> flights = storage.getAll();

            if (flights == null || flights.isEmpty()) {
                return new Response("Error: The list is empty", Status.NO_CONTENT);
            }

            ArrayList<Flight> clones = new ArrayList<>();
            for (Flight f : flights) {
                clones.add(f.copy());
            }

            clones.sort(Comparator.comparing(Flight::getId));

            for (Flight f : clones) {
                String scaleId;
                if (f.getScaleLocation() == null) {
                    scaleId = "-";
                } else {
                    scaleId = f.getScaleLocation().getAirportId();
                }

                flightTable.addRow(new Object[]{
                    f.getId(),
                    f.getDepartureLocation().getAirportId(),
                    f.getArrivalLocation().getAirportId(),
                    scaleId,
                    f.getDepartureDate(),
                    f.calculateArrivalDate(),
                    f.getPlane().getId(),
                    f.getNumPassengers()
                });
            }

            return new Response("Flight list loaded successfully", Status.OK);
        } catch (Exception e) {
            return new Response("Internal error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
