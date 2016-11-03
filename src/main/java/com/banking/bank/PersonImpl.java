package com.banking.bank;

/**
 * Created by graham on 03/11/16.
 */
public class PersonImpl implements Person{

    private String firstname;
    private String surname;
    private String email;
    private String address;
    private String country;

    public PersonImpl(String firstname,
                      String surname,
                      String email,
                      String address,
                      String country) {

        setFirstname(firstname);
        setSurname(surname);
        setEmail(email);
        setAddress(address);
        setCountry(country);
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
