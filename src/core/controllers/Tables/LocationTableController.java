package core.controllers.Tables;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Location;
import core.models.storage.LocationStorage;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.table.DefaultTableModel;

public class LocationTableController {
    public static Response updateTable(DefaultTableModel locationTable) {
        try {
            locationTable.setRowCount(0);

            LocationStorage storage = LocationStorage.getInstance();
            ArrayList<Location> locations = storage.getAll();

            if (locations == null || locations.isEmpty()) {
                return new Response("Error: The list is empty", Status.NO_CONTENT);
            }

            ArrayList<Location> clones = new ArrayList<>();
            for (Location loc : locations) {
                clones.add(loc.copy());
            }

            clones.sort(Comparator.comparing(Location::getAirportId));

            for (Location loc : clones) {
                locationTable.addRow(new Object[]{
                    loc.getAirportId(),
                    loc.getAirportName(),
                    loc.getAirportCity(),
                    loc.getAirportCountry()
                });
            }

            return new Response("Location list loaded successfully", Status.OK);
        } catch (Exception e) {
            return new Response("Internal error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}

