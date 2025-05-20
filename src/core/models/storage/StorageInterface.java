
package core.models.storage;

import java.util.ArrayList;

public interface StorageInterface<T> {
    public abstract void add(T genericItem);
    ArrayList<T> getAll();
    
}
