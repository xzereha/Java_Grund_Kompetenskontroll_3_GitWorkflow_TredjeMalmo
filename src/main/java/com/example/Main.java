package com.example;

import java.time.LocalDate;

import com.example.Models.Booking;
import com.example.Models.Email;
import com.example.Models.RegNr;
import com.example.Models.Vehicle;
import com.example.views.Menu;

public class Main {
    public static void main(String[] args) {

        BookingRepository repo = new BookingRepository();
        repo.addBooking(
                new Booking.Inspection(
                        new Vehicle(new RegNr("ABC12D"), "Ford", 2012),
                        new Email("email@domain.se"),
                        LocalDate.now(),
                        100));
        repo.addBooking(new Booking.Repair(new Vehicle(new RegNr("BCD123"), "Volvo", 2005),
                new Email("dave@davesshop.se"), LocalDate.now(), "Bromsar sönder"));
        repo.addBooking(new Booking.Repair(new Vehicle(new RegNr("BCD123"), "Volvo", 2005),
                new Email("dave@davesshop.se"), LocalDate.now(), "Bromsar sönder"));
        repo.addBooking(new Booking.Repair(new Vehicle(new RegNr("BCD123"), "Volvo", 2005),
                new Email("dave@davesshop.se"), LocalDate.now(), "Bromsar sönder"));
        repo.addBooking(new Booking.Repair(new Vehicle(new RegNr("BCD123"), "Volvo", 2005),
                new Email("dave@davesshop.se"), LocalDate.now(), "Bromsar sönder"));
        repo.addBooking(new Booking.Repair(new Vehicle(new RegNr("BCD123"), "Volvo", 2005),
                new Email("dave@davesshop.se"), LocalDate.now(), "Bromsar sönder"));
        repo.addBooking(new Booking.Repair(new Vehicle(new RegNr("BCD123"), "Volvo", 2005),
                new Email("dave@davesshop.se"), LocalDate.now(), "Bromsar sönder"));
        Menu menu = new Menu(repo);
    }
}