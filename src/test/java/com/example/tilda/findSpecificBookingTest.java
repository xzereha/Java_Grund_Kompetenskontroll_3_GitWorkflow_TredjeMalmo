package com.example.tilda;

import com.example.BookingRepository;
import com.example.Models.Booking;
import com.example.Models.Email;
import com.example.Models.RegNr;
import com.example.Models.Vehicle;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class findSpecificBookingTest {
    //TODO: Förenkla hur String regNr returnas vi implementering i projekt
    @Test
    void returns_notNull_booking() {
        RegNr r1 = new RegNr("FCK543");
        RegNr r2 = new RegNr("FCK544");
        Vehicle v1 = new Vehicle(r1, "Volvo", 2036);
        Vehicle v2 = new Vehicle(r2, "Volvo", 2036);

        LocalDate now = LocalDate.of(2025, 11, 04);
        List<Booking> mockBookings = List.of(new Booking.Inspection(v1, new Email("tilda.niva@gmail.com"), now, 34),
                new Booking.Repair(v2, new Email("hej123@gmail.com"), now, "Lampa"));

        BookingRepository bookingRepository = mock(BookingRepository.class);
        when(bookingRepository.getBookingList()).thenReturn(mockBookings);

        String lookingFor = "FCK543";
        List<Booking> foundBooking = bookingRepository.getBookingList().stream()
                .filter(b -> b.getVehicle().getRegNr().getRegNr().equals(lookingFor))
                .collect(Collectors.toList());

        System.out.println(foundBooking);
        assertFalse(foundBooking.isEmpty());
    }

    @Test
    void returns_null_booking() {
        RegNr r1 = new RegNr("FCK543");
        RegNr r2 = new RegNr("FCK544");
        Vehicle v1 = new Vehicle(r1, "Volvo", 2036);
        Vehicle v2 = new Vehicle(r2, "Volvo", 2036);

        LocalDate now = LocalDate.of(2025, 11, 04);
        List<Booking> mockBookings = List.of(new Booking.Inspection(v1, new Email("tilda.niva@gmail.com"), now, 34),
                new Booking.Repair(v2, new Email("hej123@gmail.com"), now, "Lampa"));

        BookingRepository bookingRepository = mock(BookingRepository.class);
        when(bookingRepository.getBookingList()).thenReturn(mockBookings);

        String lookingFor = "FCK545";
        List<Booking> foundBooking = bookingRepository.getBookingList().stream()
                .filter(b -> b.getVehicle().getRegNr().getRegNr().equals(lookingFor))
                .collect(Collectors.toList());

        System.out.println(foundBooking);
        assertTrue(foundBooking.isEmpty());
    }
}
