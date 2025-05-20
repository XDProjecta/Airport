
package core.models.storage;
import core.models.Plane;
import java.util.ArrayList;

public class PlaneStorage implements StorageInterface<Plane> {

    private ArrayList<Plane> planes = new ArrayList();

    @Override
    public void add(Plane plane) {
        this.planes.add(plane);
    }

    @Override
    public ArrayList getAll() {
        return planes;
    }

}
