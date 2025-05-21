package core.models.JsonReaders;

import java.util.ArrayList;

public interface JsonReader<T> {
    ArrayList<T> read(String path);
}

