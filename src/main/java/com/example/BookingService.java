package com.example;

import com.example.Models.Booking;
import com.example.Models.Email;
import com.example.Models.RegNr;
import com.example.Models.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class BookingService {
    private static final Logger logger = LoggerFactory.getLogger(BookingService.class.getName());
    private final BookingRepository bookingRepository;
    //private final EmailService emailService;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public boolean bookInspection(String regNr, String vehicleModel, int yearModel, String email, LocalDate date) {
        var regNr_ = new RegNr(regNr); // NOTE can throw
        var email_ = new Email(email); // NOTE can throw
        var vehicle = new Vehicle(regNr_, vehicleModel, yearModel);
        var price_ = 1000; // TODO fetch from price list
        var booking = new Booking.Inspection(vehicle, email_, date, price_);

        return registerBooking(booking);
    }

    public boolean bookService(String regNr, String vehicleModel, int yearModel, String email, LocalDate date) {
        var regNr_ = new RegNr(regNr); // NOTE can throw
        var email_ = new Email(email); // NOTE can throw
        var vehicle = new Vehicle(regNr_, vehicleModel, yearModel);
        var price_ = 1200; // TODO fetch from price list
        var booking = new Booking.Service(vehicle, email_, date, price_);

        return registerBooking(booking);
    }

    public boolean bookRepair(String regNr, String vehicleModel, int yearModel, String email, LocalDate date, String repairDescription) {
        var regNr_ = new RegNr(regNr); // NOTE can throw
        var email_ = new Email(email); // NOTE can throw
        var vehicle = new Vehicle(regNr_, vehicleModel, yearModel);
        var booking = new Booking.Repair(vehicle, email_, date, repairDescription);

        return registerBooking(booking);
    }

    private boolean registerBooking(Booking booking) {
        boolean wasAdded = bookingRepository.addBooking(booking);

        return wasAdded;
    }

}
