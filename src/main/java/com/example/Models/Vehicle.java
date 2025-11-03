package com.example.Models;


public class Vehicle {
    private final RegNr regNr;
    private final String model;
    private final int productionYear;


    // TODO throw if RegNr is invalid.
    public Vehicle(RegNr regNr, String model, int productionYear) {
        this.regNr = regNr;
        this.model = model;
        this.productionYear = productionYear;
    }

    public RegNr getRegNr() {
        return regNr;
    }

    public String getModel() {
        return model;
    }

    public int getProductionYear() {
        return productionYear;
    }
}
