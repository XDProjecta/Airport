
package core.controllers.utils.sorters;

import core.models.Plane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PlaneSorter {
    private ArrayList<Plane> planes;
    
    public static ArrayList<Plane> planes(ArrayList<Plane> planes) {
        Collections.sort(planes, Comparator.comparing(Plane::getId));
       return planes;
    }
}
