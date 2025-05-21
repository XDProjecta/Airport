package core.models.JsonReaders;

import core.models.JsonReaders.JsonReader;
import core.models.Flight;
import core.models.Location;
import core.models.storage.PlaneStorage;
import core.models.Plane;
import core.models.storage.LocationStorage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
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
                LocalDateTime departureDate = (LocalDateTime)obj.get("departureDate");
                int hoursArrival = obj.getInt("hoursDurationArrival");
                int minutesArrival = obj.getInt("minutesDurationArrival");
                int hoursScale = obj.getInt("hoursDurationScale");
                int minutesScale = obj.getInt("minutesDurationScale");
                Plane plane=null;
                Location departureLocation = null;
                Location arrivalLocation = null;
                Location scaleLocation = null;
                
                     PlaneStorage storageP = PlaneStorage.getInstance();
                     LocationStorage storageL = LocationStorage.getInstance();
            for (Plane p : storageP.getAll()) {
                if (p.getId().equals(planeId)) {
                    plane = p;
                }
            }
            for (Location l : storageL.getAll()) {
                if (l.getAirportId().equals(departureLocationId)) {
                     departureLocation = l;
                }
            }
            for (Location l : storageL.getAll()) {
                if (l.getAirportId().equals(arrivalLocationId)) {
                     arrivalLocation = l;
                }
            }
            for (Location l : storageL.getAll()) {
                if (l.getAirportId().equals(scaleLocationId)) {
                     scaleLocation = l;
                }
            }

                /*
                instancia de planestorage
                sacar lista
                recorrerla
                if coinciden ambos id 
                */
                // recorrer los planes con la instancia del storage y si el id coincide, guardas y agregas dicho plane

                // Crear vuelo con datos básicos (joa el plane es de tipo plane, qle vaina mala :((()
                Flight flight = new Flight(id,plane,
                        departureLocation,
                        arrivalLocation,
                        scaleLocation,
                        departureDate,
                        hoursArrival,
                        minutesArrival,
                        hoursScale, 
                        minutesScale);

                flights.add(flight);
            }

        } catch (Exception e) {
            System.out.println("Error leyendo flights.json: " + e.getMessage());
        }

        return flights;
    }
}
