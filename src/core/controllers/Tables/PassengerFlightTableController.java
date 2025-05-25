package core.controllers.Tables;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.Passenger;
import core.models.storage.PassengerStorage;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class PassengerFlightTableController {

    public static Response refreshTable(DefaultTableModel flightTable, String passengerId) {
        try {
            flightTable.setRowCount(0); 

            long id = Long.parseLong(passengerId); 
            Passenger passenger = PassengerStorage.getInstance().findById(id);

            if (passenger == null) {
                return new Response("Passenger not found", Status.BAD_REQUEST);
            }

            ArrayList<Flight> flights = passenger.getFlights();

            for (Flight flight : flights) {
                flightTable.addRow(new Object[]{
                        flight.getId(),
                        flight.getDepartureDate(),
                        flight.calculateArrivalDate()
                });
            }

            return new Response("Flights retrieved successfully", Status.OK);
        } catch (NumberFormatException e) {
            return new Response("Invalid passenger ID format", Status.BAD_REQUEST);
        } catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
