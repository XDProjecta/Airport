package core.controllers.Tables;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.controllers.utils.sorters.FlightSorter;
import core.models.Flight;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class FlightTableController {
    public static Response refreshTable(DefaultTableModel flightTable) {
        try {
            flightTable.setRowCount(0);

            ArrayList<Flight> flights = FlightSorter.getSortedFlights();

            if (flights == null || flights.isEmpty()) {
                return new Response("Error: The list is empty", Status.NO_CONTENT);
            }

            for (Flight f : flights) {
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
