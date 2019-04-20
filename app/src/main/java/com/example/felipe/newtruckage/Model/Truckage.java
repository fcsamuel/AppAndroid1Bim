package com.example.felipe.newtruckage.Model;

public class Truckage {
    private static int idCount = 0;
    private int id;
    private City origin;
    private City destiny;
    private String originZip;
    private String destinyZip;
    private double weight;
    private double totalValue;

    public Truckage() {
        this.id = ++this.idCount;
    }

    public int getId() {
        return id;
    }

    public City getOrigin() {
        return origin;
    }

    public void setOrigin(City origin) {
        this.origin = origin;
    }

    public City getDestiny() {
        return destiny;
    }

    public void setDestiny(City destiny) {
        this.destiny = destiny;
    }

    public String getOriginZip() {
        return originZip;
    }

    public void setOriginZip(String originZip) {
        this.originZip = originZip;
    }

    public String getDestinyZip() {
        return destinyZip;
    }

    public void setDestinyZip(String destinyZip) {
        this.destinyZip = destinyZip;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    @Override
    public String toString() {
        return this.id + " - De " + this.getOriginZip() + " p/ " + this.getDestinyZip() + "| R$ " + this.totalValue;
    }
}
