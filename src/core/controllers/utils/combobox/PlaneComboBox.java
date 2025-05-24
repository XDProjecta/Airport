
package core.controllers.utils.combobox;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.JsonReaders.ReadJsonPlane;
import core.models.Plane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JComboBox;

public class PlaneComboBox {
       public static Response addItems(JComboBox<String> comboBox) {
        try {
            ReadJsonPlane jsonPlanes = new ReadJsonPlane();
            ArrayList<Plane> planes = jsonPlanes.read("json/planes.json");
            comboBox.removeAllItems();
            Collections.sort(planes, Comparator.comparing(Plane::getId));
            comboBox.addItem("Plane");
            for (Plane plane : planes) {
                comboBox.addItem("" + plane.getId());
            }
            return new Response("File uploaded successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected", Status.INTERNAL_SERVER_ERROR);
        }
    } 
}
