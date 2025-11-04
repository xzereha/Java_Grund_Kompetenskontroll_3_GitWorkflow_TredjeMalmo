package com.example;

import com.example.Models.Booking;
import com.example.Models.Email;
import com.example.Models.RegNr;
import com.example.Models.Vehicle;

import java.time.LocalDate;

public class InitService {

    public static void init(BookingRepository bookingRepository) {

       // TODO: fix price
        bookingRepository.addBooking(new Booking.Service
                (new Vehicle(new RegNr("ABC123"), "FIAT PANDA", 2022), new Email("email@address.se"),
                        LocalDate.of(2025,11,28),2334));

        bookingRepository.addBooking(new Booking.Inspection
                (new Vehicle(new RegNr("ABC123"), "MERCEDES SMART", 2000), new Email("email@address.se"),
                        LocalDate.of(2025,12,2),4567));

        bookingRepository.addBooking(new Booking.Repair
                (new Vehicle(new RegNr("ABC123"), "TESLA", 2022), new Email("email@address.se"),
                        LocalDate.of(2025,12,3),"Doorhandel"));

        bookingRepository.addBooking(new Booking.Service
                (new Vehicle(new RegNr("ABC123"), "TOYOTA IQ", 2022), new Email("email@address.se"),
                        LocalDate.of(2025,12,12),4567));

        bookingRepository.addBooking(new Booking.Inspection
                (new Vehicle(new RegNr("ABC123"), "Model", 2022), new Email("email@address.se"),
                        LocalDate.of(2025,11,22),9856));

        bookingRepository.addBooking(new Booking.Repair
                (new Vehicle(new RegNr("ABC123"), "Model", 2022), new Email("email@address.se"),
                        LocalDate.of(2024,11,26),"carFarts"));

        bookingRepository.addBooking(new Booking.Service
                (new Vehicle(new RegNr("ABC123"), "Model", 2022), new Email("email@address.se"),
                        LocalDate.of(2025,11,28),6678));

        bookingRepository.addBooking(new Booking.Inspection
                (new Vehicle(new RegNr("ABC123"), "Model", 2022), new Email("email@address.se"),
                        LocalDate.of(2025,11,29),9889));

        bookingRepository.addBooking(new Booking.Repair
                (new Vehicle(new RegNr("ABC123"), "SCANIA", 2022), new Email("email@address.se"),
                        LocalDate.of(2024,11,26),"carFarts"));

        bookingRepository.addBooking(new Booking.Service
                (new Vehicle(new RegNr("ABC123"), "VOLVO V60", 2022), new Email("email@address.se"),
                        LocalDate.of(2025,6,26),6778));

        bookingRepository.addBooking(new Booking.Inspection
                (new Vehicle(new RegNr("ABC123"), "NISSAN MICRA", 2022), new Email("email@address.se"),
                        LocalDate.of(2025,11,26),9898));

        bookingRepository.addBooking(new Booking.Repair
                (new Vehicle(new RegNr("ABC123"), "MERCEDES", 2022), new Email("email@address.se"),
                        LocalDate.of(2026,3,26),"carFarts"));

        bookingRepository.addBooking(new Booking.Service
                (new Vehicle(new RegNr("ABC123"), "Model", 2022), new Email("email@address.se"),
                        LocalDate.of(2026,6,26),9234));

        bookingRepository.addBooking(new Booking.Repair
                (new Vehicle(new RegNr("ABC123"), "Model", 2022), new Email("email@address.se"),
                        LocalDate.of(2025,8,26),"carFarts"));

        bookingRepository.addBooking(new Booking.Inspection
                (new Vehicle(new RegNr("ABC123"), "Model", 2022), new Email("email@address.se"),
                        LocalDate.of(2026,5,3),9875));
    }
}
