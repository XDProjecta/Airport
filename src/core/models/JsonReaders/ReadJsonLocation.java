
package core.models.JsonReaders;

import core.models.Location;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class ReadJsonLocation implements JsonReader<Location> {

    @Override
    public ArrayList<Location> read(String path) {
        ArrayList<Location> locations = new ArrayList<>();

        try (InputStream is = new FileInputStream(path)) {
            JSONArray array = new JSONArray(new JSONTokener(is));

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                Location loc = new Location(
                    obj.getString("airportId"),
                    obj.getString("airportName"),
                    obj.getString("airportCity"),
                    obj.getString("airportCountry"),
                    obj.getDouble("airportLatitude"),
                    obj.getDouble("airportLongitude")
                );

                locations.add(loc);
            }

        } catch (Exception e) {
            System.out.println("Error leyendo locations: " + e.getMessage());
        }

        return locations;
    }
}

