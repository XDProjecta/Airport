package core.controllers.Tables;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.controllers.utils.sorters.PassengerSorter;
import core.models.Passenger;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class PassengerTableController {

    public static Response updateTable(DefaultTableModel passengerTable) {
        try {
            passengerTable.setRowCount(0);
            ArrayList<Passenger> sorted = PassengerSorter.getSortedPassengers();

            if (sorted.isEmpty()) {
                return new Response("No passengers to display", Status.NO_CONTENT);
            }

            for (Passenger p : sorted) {
                passengerTable.addRow(new Object[]{
                    p.getId(),
                    p.getFirstname()+" "+ p.getLastname(),
                    p.getBirthDate(),
                    p.calculateAge(),
                    p.generateFullPhone(),
                    p.getCountry(),
                    p.getFlights().size()
                });
            }

            return new Response("Passenger list loaded successfully", Status.OK);
        } catch (Exception e) {
            return new Response("Error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
