package com.example.felipe.newtruckage.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class City implements Serializable {
    private int id;
    private String description;
    private State state;
    private List<String> zipCodes;

    public City(int id, String description, State state) {
        this.id = id;
        this.description = description;
        this.state = state;
        zipCodes = new ArrayList<>();
    }

    public State getState() {
        return state;
    }

    public List<String> getZipCodes() {
        return zipCodes;
    }

    public void addCep(String cep) {
        this.zipCodes.add(cep);
    }

    @Override
    public String toString() {
        return this.description;
    }
}
