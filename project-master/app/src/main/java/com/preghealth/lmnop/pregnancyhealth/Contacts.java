package com.preghealth.lmnop.pregnancyhealth;

/**
 * Created by Chitkara Neetu on 5/15/2016.
 */
public class Contacts {

    String name;
    String email;

    public Contacts() {
    }

    public Contacts(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
