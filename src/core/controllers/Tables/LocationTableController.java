package core.controllers.Tables;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.controllers.utils.sorters.LocationSorter;
import core.models.Location;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class LocationTableController {
    public static Response refreshTable(DefaultTableModel locationTable) {
        try {
            locationTable.setRowCount(0);

            ArrayList<Location> locations = LocationSorter.getSortedLocations();

            if (locations == null || locations.isEmpty()) {
                return new Response("Error: The list is empty", Status.NO_CONTENT);
            }

            for (Location loc : locations) {
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

