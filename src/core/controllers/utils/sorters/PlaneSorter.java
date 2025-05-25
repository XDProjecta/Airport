package core.controllers.utils.sorters;

import core.models.Plane;
import core.models.storage.PlaneStorage;
import java.util.ArrayList;
import java.util.Comparator;

public class PlaneSorter {

    public static ArrayList<Plane> getSortedPlanes() {
        ArrayList<Plane> planes = PlaneStorage.getInstance().getAll();

        ArrayList<Plane> clones = new ArrayList<>();
        for (Plane p : planes) {
            clones.add(p.copy());
        }
        clones.sort(Comparator.comparing(Plane::getId));
        return clones;
    }
}
