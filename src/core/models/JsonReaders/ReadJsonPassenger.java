package core.models.JsonReaders;

import core.models.Passenger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReadJsonPassenger implements JsonReader<Passenger> {

    @Override
    public ArrayList<Passenger> read(String path) {
        ArrayList<Passenger> passengers = new ArrayList<>();

        try (InputStream is = new FileInputStream(path)) {
            JSONArray array = new JSONArray(new JSONTokener(is));

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                Passenger p = new Passenger(
                    obj.getInt("id"),
                    obj.getString("firstname"),
                    obj.getString("lastname"),
                    (LocalDate)obj.get("birthDate"),
                    obj.getInt("countryPhoneCode"),
                    obj.getInt("phone"),
                    obj.getString("country")
                );

                passengers.add(p);
            }

        } catch (Exception e) {
            System.out.println("Error leyendo passengers: " + e.getMessage());
        }

        return passengers;
    }
}
