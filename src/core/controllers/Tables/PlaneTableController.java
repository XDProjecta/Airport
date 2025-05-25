package core.controllers.Tables;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Plane;
import core.models.storage.PlaneStorage;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.table.DefaultTableModel;

public class PlaneTableController {
    public static Response refreshTable(DefaultTableModel planeTable) {
        try {
            planeTable.setRowCount(0);

            PlaneStorage storage = PlaneStorage.getInstance();
            ArrayList<Plane> planes = storage.getAll();

            if (planes == null || planes.isEmpty()) {
                return new Response("Error, The list is empty", Status.NO_CONTENT);
            }

            ArrayList<Plane> clones = new ArrayList<>();
            for (Plane p : planes) {
                clones.add(p.copy());
            }

            clones.sort(Comparator.comparing(Plane::getId));

            for (Plane p : clones) {
                planeTable.addRow(new Object[]{
                    p.getId(),
                    p.getBrand(),
                    p.getModel(),
                    p.getMaxCapacity(),
                    p.getAirline(),
                    p.getNumFlights()
                });
            }

            return new Response("Plane list loaded successfully", Status.OK);
        } catch (Exception e) {
            return new Response("Internal error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
