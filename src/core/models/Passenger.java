package core.models;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

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

    public Passenger(long id, String firstname, String lastname, LocalDate birthDate, int countryPhoneCode, long phone, String country) {
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

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }
// EL ERROR ESTABA ACÁ OMBE, LA MALA PAL QUE HIZO EL PROTOTYPE MAL (yo)
    public Passenger copy() {
        Passenger copy = new Passenger(
                this.id, this.firstname, this.lastname,
                this.birthDate, this.countryPhoneCode,
                this.phone, this.country
        );
        copy.setEmail(this.email);
        copy.setPhoneCode(this.PhoneCode);

        for (Flight f : this.flights) {
            copy.addFlight(f);
        }

        return copy;
    }

}
