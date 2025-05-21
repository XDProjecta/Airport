package core.models.JsonReaders;

import core.models.JsonReaders.JsonReader;
import core.models.Flight;
import core.models.Plane;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class ReadJsonFlight implements JsonReader<Flight> {

    @Override
    public ArrayList<Flight> read(String path) {
        ArrayList<Flight> flights = new ArrayList<>();

        try (InputStream is = new FileInputStream(path)) {
            JSONArray array = new JSONArray(new JSONTokener(is));

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String id = obj.getString("id");
                String planeId = obj.getString("plane");
                String departureLocationId = obj.getString("departureLocation");
                String arrivalLocationId = obj.getString("arrivalLocation");
                String scaleLocationId = obj.optString("scaleLocation", null);
                String departureDate = obj.getString("departureDate");
                int hoursArrival = obj.getInt("hoursDurationArrival");
                int minutesArrival = obj.getInt("minutesDurationArrival");
                int hoursScale = obj.getInt("hoursDurationScale");
                int minutesScale = obj.getInt("minutesDurationScale");

                // Crear vuelo con datos básicos (joa el plane es de tipo plane, qle vaina mala :((()
                Flight flight = new Flight(id,planeId,departureLocationId, arrivalLocationId,scaleLocationId,departureDate,
                        hoursArrival, minutesArrival, hoursScale, minutesScale);

                flights.add(flight);
            }

        } catch (Exception e) {
            System.out.println("Error leyendo flights.json: " + e.getMessage());
        }

        return flights;
    }
}
