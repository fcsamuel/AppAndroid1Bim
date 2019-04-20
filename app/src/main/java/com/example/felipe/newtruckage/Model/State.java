package com.example.felipe.newtruckage.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class State implements Serializable {
    private int id;
    private String description;
    private String initials;
    private List<City> cities;

    public State(int id, String description, String initials) {
        this.id = id;
        this.description = description;
        this.initials = initials;
        cities = new ArrayList<>();
    }

    public List<City> getCities() {
        return cities;
    }

    public City getCity(int cityPosition) {
        return cities.get(cityPosition);
    }

    public void addCity(City city) {
        this.cities.add(city);
    }

    public String getInitials() {
        return initials;
    }

    @Override
    public String toString() {
        return this.description + " - " + this.initials;
    }
}
