
package core.controllers.utils.combobox;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.JsonReaders.ReadJsonPassenger;
import core.models.Passenger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JComboBox;

public class PassengerComboBox {
        public static Response addItems(JComboBox<String> comboBox) {
        try {
            ReadJsonPassenger jsonPassenger = new ReadJsonPassenger();
            ArrayList<Passenger> passengers = jsonPassenger.read("json/passengers.json");
            comboBox.removeAllItems();
            System.out.println("Size: " + passengers.size());
            Collections.sort(passengers, Comparator.comparingLong(Passenger::getId));
            comboBox.addItem("Select User");
            for (Passenger passenger : passengers) {
                comboBox.addItem("" + passenger.getId());
            }
            return new Response("File uploaded successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
