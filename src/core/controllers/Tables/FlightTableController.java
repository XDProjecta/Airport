package core.controllers.Tables;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.controllers.utils.sorters.FlightSorter;
import core.models.Flight;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class FlightTableController {

    public static Response refreshTable(DefaultTableModel flightTable) {
        try {
            flightTable.setRowCount(0);
            ArrayList<Flight> flights = FlightSorter.getSortedFlights();

            for (Flight f : flights) {
                String departure = "-";
                String arrival = "-";
                String scale = "-";
                String departureDate = "-";
                String arrivalDate = "-";
                String planeId = "-";
                int numPassengers = 0;

                if (f.getDepartureLocation() != null) {
                    departure = f.getDepartureLocation().getAirportId();
                }

                if (f.getArrivalLocation() != null) {
                    arrival = f.getArrivalLocation().getAirportId();
                }

                if (f.getScaleLocation() != null) {
                    scale = f.getScaleLocation().getAirportId();
                }

                if (f.getDepartureDate() != null) {
                    departureDate = f.getDepartureDate().toString();
                }

                if (f.calculateArrivalDate() != null) {
                    arrivalDate = f.calculateArrivalDate().toString();
                }

                if (f.getPlane() != null) {
                    planeId = f.getPlane().getId();
                }

                if (f.getPassengers() != null) {
                    numPassengers = f.getPassengers().size();
                }

                flightTable.addRow(new Object[]{
                    f.getId(),
                    departure,
                    scale,
                    arrival,
                    departureDate,
                    arrivalDate,
                    planeId,
                    f.getPassengers().size()
                });
            }

            return new Response("Flight list loaded successfully", Status.OK);
        } catch (Exception e) {
            return new Response("Internal error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
