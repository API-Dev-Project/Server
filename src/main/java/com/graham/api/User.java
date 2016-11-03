package com.graham.api;

/**
 * Created by graham on 21/10/16.
 */
public class User {

    private String firstname;
    private String surname;
    private String username;
    private String password;
    boolean isCreatedSuccessfully;

    public User(String firstname, String surname, String username, String password) {
        this.firstname = firstname;
        this.surname = surname;
        this.username = username;
        this.password = password;

        isCreatedSuccessfully = isCreatedSuccessfully();
    }

    private boolean isCreatedSuccessfully() {
        return firstname.isEmpty() || surname.isEmpty() ||
                username.isEmpty() || password.isEmpty() ? false : true;
    }
}
