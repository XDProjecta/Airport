
package core.controllers.utils.combobox;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Location;
import core.models.storage.LocationStorage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JComboBox;

public class LocationComboBox {
       public static Response addItems(JComboBox<String> comboBox) {
        try {
            LocationStorage locationRegister = LocationStorage.getInstance();
            ArrayList<Location> locations = locationRegister.getAll();
            comboBox.removeAllItems();
            Collections.sort(locations, Comparator.comparing(Location::getAirportId));
            comboBox.addItem("Location");
            for (Location location : locations) {
                comboBox.addItem("" + location.getAirportId());
            }
            return new Response("File upload successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
