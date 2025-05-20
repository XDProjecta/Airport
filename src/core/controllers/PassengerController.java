
/*package core.controllers;

import core.models.Passenger;
import core.models.storage.PassengerStorage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class PassengerController {
    private PassengerStorage passengerStorage;

    public PassengerController() {
        this.passengerStorage = PassengerStorage.getInstance();
    }

    public Response registerPassenger(Passenger passenger) {
        Response validation = validatePassenger(passenger);
        if (validation.getCode() != 200) {
            return validation;
        }

        if (passengerStorage.getPassengerById(passenger.getId()) != null) {
            return new Response(400, "El ID del pasajero ya existe");
        }

        passengerStorage.addPassenger(passenger);
        return new Response(200, "Pasajero registrado exitosamente");
    }

    public Response updatePassengerInfo(Passenger passenger) {
        Response validation = validatePassenger(passenger);
        if (validation.getCode() != 200) {
            return validation;
        }

        Passenger existing = (Passenger) passengerStorage.getPassengerById(passenger.getId());
        if (existing == null) {
            return new Response(404, "Pasajero no encontrado");
        }

        passengerStorage.updatePassenger(passenger);
        return new Response(200, "Información del pasajero actualizada");
    }

    public Response getAllPassengers() {
        List<Passenger> passengers = passengerStorage.getAllPassengers().stream()
                .sorted((p1, p2) -> Long.compare(p1.getId(), p2.getId()))
                .collect(Collectors.toList());
        return new Response(200, "Lista de pasajeros obtenida", passengers);
    }

    private Response validatePassenger(Passenger passenger) {
        if (passenger.getId() < 0 || String.valueOf(passenger.getId()).length() > 15) {
            return new Response(422, "ID debe ser mayor o igual a 0 y tener máximo 15 dígitos");
        }

        if (passenger.getFirstname() == null || passenger.getFirstname().trim().isEmpty() ||
            passenger.getLastname() == null || passenger.getLastname().trim().isEmpty() ||
            passenger.getEmail() == null || passenger.getEmail().trim().isEmpty()) {
            return new Response(422, "Todos los campos obligatorios deben estar completos");
        }

        try {
            LocalDate.parse(passenger.getBirthDate(), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            return new Response(422, "Fecha de nacimiento inválida. Formato esperado: YYYY-MM-DD");
        }

        if (passenger.getPhoneCode() < 0 || passenger.getPhoneCode() > 999) {
            return new Response(422, "Código telefónico debe estar entre 0 y 999");
        }

        if (passenger.getPhoneNumber() < 0 || String.valueOf(passenger.getPhoneNumber()).length() > 11) {
            return new Response(422, "Número telefónico debe ser positivo y tener máximo 11 dígitos");
        }

        return new Response(200, "Validación exitosa");
    }
}
*/
