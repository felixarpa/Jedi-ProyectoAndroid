package com.example.felixarpa.proyectojedi.Fragments;

public class Persona {

    String username;
    String name;
    int score;

    Persona() {

    }

    Persona(String u, String n, int s) {
        username = u;
        name = n;
        score = s;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }
}
