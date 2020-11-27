package com.example.springboot.Models;

public class User {

    private final String login;
    private final String email;
    private final String heslo;

    public User(final String login, 
                final String email,
                final String heslo) {
        this.login = login;
        this.email = email;
        this.heslo = heslo;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getHeslo() {
        return heslo;
    }
}