
package core.controllers.utils.combobox;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.JsonReaders.ReadJsonFlight;
import core.models.JsonReaders.ReadJsonLocation;
import core.models.JsonReaders.ReadJsonPassenger;
import core.models.JsonReaders.ReadJsonPlane;
import core.models.Location;
import core.models.Passenger;
import core.models.Plane;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONException;


public class JsonComboBox {
    
    public static Response addJson() {
        try {
            ReadJsonLocation jsonLocation = new ReadJsonLocation();
            ArrayList<Location> locations = jsonLocation.read("json/locations.json");

            ReadJsonFlight jsonFlights = new ReadJsonFlight();
            ArrayList<Flight> flights = jsonFlights.read("json/flights.json");

            ReadJsonPassenger jsonPassenger = new ReadJsonPassenger();
            ArrayList<Passenger> passengers = jsonPassenger.read("json/passengers.json");

            ReadJsonPlane jsonPlanes = new ReadJsonPlane();
            ArrayList<Plane> planes = jsonPlanes.read("json/planes.json");

            return new Response("File upload successfully", Status.OK);
        } catch (JSONException e) {
            return new Response("Problems with reading files", Status.INTERNAL_SERVER_ERROR);
        }catch(Exception e){
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }

    }
}
