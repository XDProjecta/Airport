
package core.controllers.utils.combobox;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.storage.FlightStorage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JComboBox;

public class FlightComboBox {

    public static Response addItems(JComboBox<String> comboBox) {
        try {
            FlightStorage storage = FlightStorage.getInstance();
            ArrayList<Flight> flights = storage.getAll();
            comboBox.removeAllItems();
            Collections.sort(flights, Comparator.comparing(Flight::getId));
            comboBox.addItem("Flight");
            for (Flight flight : flights) {
                comboBox.addItem("" + flight.getId());
            }
            return new Response("File uploaded successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
