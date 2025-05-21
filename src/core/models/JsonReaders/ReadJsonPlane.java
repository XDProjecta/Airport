package core.models.JsonReaders;

import core.models.Plane;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class ReadJsonPlane implements JsonReader<Plane> {

    @Override
    public ArrayList<Plane> read(String path) {
        ArrayList<Plane> planes = new ArrayList<>();

        try (InputStream is = new FileInputStream(path)) {
            JSONArray array = new JSONArray(new JSONTokener(is));

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                Plane plane = new Plane(
                    obj.getString("id"),
                    obj.getString("brand"),
                    obj.getString("model"),
                    obj.getInt("maxCapacity"),
                    obj.getString("airline")
                );

                planes.add(plane);
            }

        } catch (Exception e) {
            System.out.println("Error leyendo planes: " + e.getMessage());
        }

        return planes;
    }
}

