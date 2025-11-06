package com.example.sorting;

import com.example.Models.Booking;
import com.example.Models.Email;
import com.example.Models.RegNr;
import com.example.Models.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortByStatusTest {

    private List<Booking> bookings;

    @BeforeEach
    void setUp() {
        bookings = new ArrayList<>();

        bookings.add(new Booking.Service
                (new Vehicle(new RegNr("ABC123"), "FIAT PANDA", 2022), new Email("email@address.se"),
                        LocalDate.of(2025, 11, 28), 2334));

        bookings.add(new Booking.Inspection
                (new Vehicle(new RegNr("ABC123"), "MERCEDES SMART", 2000), new Email("email@address.se"),
                        LocalDate.of(2025, 12, 2), 4567));

        bookings.add(new Booking.Repair
                (new Vehicle(new RegNr("ABC123"), "TESLA", 2022), new Email("email@address.se"),
                        LocalDate.of(2025, 12, 3), "Doorhandel"));
    }

    @Test
    void whenSortByStatus_thenReturnSortedBookingsByStatus() {
        SortByStatus sortByStatus = new SortByStatus();

        List<Booking> sortedBookings = sortByStatus.apply(bookings);

        assertEquals(Booking.Status.PENDING, sortedBookings.get(0).getStatus());
        assertEquals(Booking.Status.PENDING, sortedBookings.get(1).getStatus());
        assertEquals(Booking.Status.PENDING, sortedBookings.get(2).getStatus());
    }

    @Test
    void whenChangeStatus_thenReturnSortedBookingsByStatus() {

        System.out.println(bookings.get(0).getStatus());

        bookings.get(0).setStatus(Booking.Status.COMPLETED);
        System.out.println(bookings.get(0).getStatus());

    }
}
