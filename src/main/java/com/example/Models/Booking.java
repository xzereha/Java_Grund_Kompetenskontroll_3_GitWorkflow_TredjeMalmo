package com.example.Models;

import java.time.LocalDate;

public abstract class Booking {
    public enum Status {
        PENDING,
        COMPLETED
    }

    private final long id;
    private static long nextId = 0;
    private Vehicle vehicle;
    private Email email;
    private LocalDate date;
    private Status status;

    public Booking(Vehicle vehicle, Email email, LocalDate date) {
        this.id = nextId++;
        this.vehicle = vehicle;
        this.email = email;
        this.date = date;
        this.status = Status.PENDING;
    }

    public long getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public abstract float getPrice();

    public void setStatus(Status status) {
        this.status = status;
    }

    public static class Inspection extends Booking {
        private final float price;

        public Inspection(Vehicle vehicle, Email email, LocalDate date, float price) {
            super(vehicle, email, date);
            this.price = price;
        }

        @Override
        public float getPrice() {
            return price;
        }
    }

    public static class Service extends Booking {
        private final float price;

        public Service(Vehicle vehicle, Email email, LocalDate date, float price) {
            super(vehicle, email, date);
            this.price = price;
        }

        @Override
        public float getPrice() {
            return price;
        }
    }

    public static class Repair extends Booking {
        private float price;
        private String description;

        public Repair(Vehicle vehicle, Email email, LocalDate date, String description) {
            super(vehicle, email, date);
            this.price = 0;
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        @Override
        public float getPrice() {
            return price;
        }
    }
}
