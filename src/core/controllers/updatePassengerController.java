/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Passenger;
import core.models.storage.PassengerStorage;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 *
 * @author jmanu
 */
public class updatePassengerController {


    public static Response passengerUpdateRegistration(String idStr, String firstNameStr, String lastNameStr, String yearStr, String monthStr, String dayStr, String codeStr, String phoneStr, String countryStr) {
        PassengerStorage passengerStorage = PassengerStorage.getInstance();
        ArrayList<Passenger> allPassengers = passengerStorage.getAll();
        Passenger passengerToUpdate = null;
        try {
            int parsedYear, parsedMonthCode;
            long parsedPhone, parsedId;
            LocalDate parsedBirthDate;

            if (allPassengers == null) {
                return new Response("No passengers registered", Status.BAD_REQUEST);
            }

            if (idStr.equals("")) {
                return new Response("Chose an ID on administration", Status.BAD_REQUEST);
            }

            try {
                parsedId = Integer.parseInt(idStr);
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            if (firstNameStr.equals("")) {
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            }

            if (lastNameStr.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }

            try {
                parsedYear = Integer.parseInt(yearStr);
                if (parsedYear < 1900) {
                    return new Response("Year must be greater than 1900", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Year must be numeric", Status.BAD_REQUEST);
            }

            if (monthStr.equals("Month")) {
                return new Response("Select a month", Status.BAD_REQUEST);
            }

            if (dayStr.equals("Day")) {
                return new Response("Select a day", Status.BAD_REQUEST);
            }

            try {
                String fullDate = String.format("%s-%02d-%02d", yearStr, Integer.parseInt(monthStr), Integer.parseInt(dayStr));
                parsedBirthDate = LocalDate.parse(fullDate);
            } catch (DateTimeParseException e) {
                return new Response("Date invalid", Status.BAD_REQUEST);
            }

            try {
                parsedMonthCode = Integer.parseInt(codeStr);
                if (parsedMonthCode < 0 || codeStr.length() > 3) {
                    return new Response("The code must be 1 to 3 digits long", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Code must be numeric", Status.BAD_REQUEST);
            }

            try {
                parsedPhone = Long.parseLong(codeStr); // ojo: usa codeStr, ¿error?
                if (parsedPhone < 0 || phoneStr.length() > 11) {
                    return new Response("Phone must be 0 to 11 digits long", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Phone must be numeric", Status.BAD_REQUEST);
            }

            if (countryStr.equals("")) {
                return new Response("Country must be not empty", Status.BAD_REQUEST);
            }

            for (Passenger passenger : allPassengers) {
                if (passenger.getId() == parsedId) {
                    passengerToUpdate = passenger;
                }
            }

            passengerToUpdate.setFirstname(firstNameStr);
            passengerToUpdate.setLastname(lastNameStr);
            passengerToUpdate.setBirthDate(parsedBirthDate);
            passengerToUpdate.setCountryPhoneCode(parsedMonthCode);
            passengerToUpdate.setPhone(parsedPhone);
            passengerToUpdate.setCountry(countryStr);

            System.out.println(passengerToUpdate);
            return new Response("Successfully updated information", Status.OK);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}

    

