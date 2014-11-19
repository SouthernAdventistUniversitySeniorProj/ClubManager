package com.seniorproject.sauclubmanager.com.seniorproject.utilities;

/**
 * Created by User on 11/9/2014.
 */
public class User {

    private String _id, _name, _email, _pass;

    public User (String id, String name, String email, String pass) {
        _id = id;
        _name = name;
        _email = email;
        _pass = pass;
    }

    public String getID() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public String getEmail() {
        return _email;
    }

    public String getPass() {
        return _pass;
    }
}
