
package core.controllers.utils.sorters;

import core.models.Plane;
import java.util.ArrayList;
import java.util.Comparator;

public class PlaneSorter {
    public static ArrayList<Plane> getSortedPlanes(ArrayList<Plane> planes) {
        ArrayList<Plane> clones = new ArrayList<>();
        for (Plane p : planes) clones.add(p.copy());
        clones.sort(Comparator.comparing(Plane::getId));
        return clones;
    }
}
