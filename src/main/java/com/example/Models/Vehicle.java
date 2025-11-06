package com.example.Models;

public class Vehicle {
    private RegNr regNr;
    private String model;
    private int productionYear;

    public Vehicle(RegNr regNr, String model, int productionYear) {
        this.regNr = regNr;
        this.model = model;
        this.productionYear = productionYear;
    }

    public RegNr getRegNr() {
        return regNr;
    }

    public void setRegNr(RegNr regNr) {
        this.regNr = regNr;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }
}
