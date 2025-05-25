package core.controllers.Tables;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.controllers.utils.sorters.PlaneSorter;
import core.models.Plane;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class PlaneTableController {

    public static Response refreshTable(DefaultTableModel planeTable) {
        try {
            planeTable.setRowCount(0);

            ArrayList<Plane> planes = PlaneSorter.getSortedPlanes();

            if (planes == null || planes.isEmpty()) {
                return new Response("Error, The list is empty", Status.NO_CONTENT);
            }

            for (Plane p : planes) {
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
