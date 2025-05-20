/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

/**
 *
 * @author edangulo
 */
public class Passenger {
    
    private final long id;
    private String firstname;
    private String lastname;
    private String email;
    private LocalDate birthDate;
    private int countryPhoneCode;
    private long phone;
    private int PhoneCode;
    private String country;
    private ArrayList<Flight> flights;

    public Passenger(long id, String firstname, String lastname, String country, String birthDate1, int countryPhoneCode, long phone) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.birthDate = birthDate;
        this.countryPhoneCode = countryPhoneCode;
        this.phone = phone;
        this.PhoneCode = PhoneCode;
        this.country = country;
        this.flights = new ArrayList<>();
    }
//APARTE, ABSTRACT CLASS OR INTERFACE
    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }
    
    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }
    
    public String getLastname() {
        return lastname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public int getCountryPhoneCode() {
        return countryPhoneCode;
    }

    public long getPhone() {
        return phone;
    }

    public int getPhoneCode() {
        return PhoneCode;
    }

    public String getCountry() {
        return country;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setbirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setCountryPhoneCode(int countryPhoneCode) {
        this.countryPhoneCode = countryPhoneCode;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setPhoneCode(int PhoneCode) {
        this.PhoneCode = PhoneCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getFullname() {
        return firstname + " " + lastname;
    }
    
    public String generateFullPhone() {
        return "+" + countryPhoneCode + " " + phone;
    }
    
    public int calculateAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
    
    public int getNumFlights() {
        return flights.size();
    }

    

    
}
