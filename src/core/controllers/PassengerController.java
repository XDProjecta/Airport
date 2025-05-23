package core.controllers;

import core.models.Passenger;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.JsonReaders.ReadJsonPassenger;
import core.models.storage.PassengerStorage;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PassengerController {
    
    public static void loadPassengersFromJson() {
        ReadJsonPassenger reader = new ReadJsonPassenger();
        ArrayList<Passenger> passengers = reader.read("json/passengers.json");

        PassengerStorage storage = PassengerStorage.getInstance();
        for (Passenger pas : passengers) {
            storage.add(pas);
        }
    }

    public static Response registerPassenger(String idStr, String firstname, String lastname, String yearStr,
                                             String monthStr, String dayStr, String phoneCodeStr,
                                             String phoneStr, String country) {
        try {
            // Validar campos vacíos
            if (idStr.isEmpty() || firstname.isEmpty() || lastname.isEmpty() ||
                yearStr.isEmpty() || monthStr.isEmpty() || dayStr.isEmpty() ||
                phoneCodeStr.isEmpty() || phoneStr.isEmpty() || country.isEmpty()) {
                return new Response("All fields must be filled", Status.BAD_REQUEST);
            }

            // Validar tipos numéricos
            long id = Long.parseLong(idStr);
            int year = Integer.parseInt(yearStr);
            int month = Integer.parseInt(monthStr);
            int day = Integer.parseInt(dayStr);
            int phoneCode = Integer.parseInt(phoneCodeStr);
            long phone = Long.parseLong(phoneStr);

            // Validar rangos
            if (id < 0 || id > 99999999) {
               return new Response("ID must be a positive number with up to 15 digits.", Status.BAD_REQUEST);
            }

            if (phoneCode < 0 || phoneCode > 999) {
               return new Response("Phone code must be a number between 0 and 999.", Status.BAD_REQUEST);
            }

            if (phone < 0 || phone > 999999999) {
               return new Response("Phone number must be a number between 0 and 999999999.", Status.BAD_REQUEST);
            }

            LocalDate birthDate;
            try {
                birthDate = LocalDate.of(year, month, day);
            } catch (DateTimeParseException e) {
                return new Response("Invalid birth date.", Status.BAD_REQUEST);
            }

            // Validar duplicados
            PassengerStorage storage = PassengerStorage.getInstance();
            for (Passenger p : storage.getAll()) {
                if (p.getId() == id) {
                    return new Response("A passenger with this ID already exists.", Status.BAD_REQUEST);
                }
            }

            // Crear y guardar pasajero
            Passenger passenger = new Passenger(id, firstname, lastname, birthDate, phoneCode, phone, country);
            storage.add(passenger);

            return new Response("Passenger registered successfully.", Status.OK);
            
            

        } catch (NumberFormatException e) {
            return new Response("ID, date, and phone values must be numeric.", Status.BAD_REQUEST);
        } catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response getAllPassengersSorted() {
    try {
        PassengerStorage storage = PassengerStorage.getInstance();

        List<Passenger> sorted = storage.getAll().stream()
            .map(Passenger::copy) // Patrón Prototype
            .sorted(Comparator.comparing(Passenger::getId)) // Ordena por ID; puedes cambiar a getLastname()
            .collect(Collectors.toList());

        return new Response("Passengers retrieved successfully.", Status.OK, sorted);
    } catch (Exception e) {
        return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
    }
}

}
    
