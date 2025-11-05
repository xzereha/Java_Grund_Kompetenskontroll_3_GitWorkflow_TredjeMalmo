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

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortedByDate {

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
    void whenSortByDate_thenReturnSortedByNewestFirst() {
        SortByDate sortByDate = new SortByDate(true);
        List<Booking> sortedBookings = sortByDate.apply(bookings);

        assertTrue(sortedBookings.get(2).getDate().isBefore(sortedBookings.get(1).getDate()));
        assertTrue(sortedBookings.get(1).getDate().isBefore(sortedBookings.get(0).getDate()));
    }

    @Test
    void whenSortByDate_thenReturnSortedByOldestFirst() {
        SortByDate sortByDate = new SortByDate(false);
        List<Booking> sortedBookings = sortByDate.apply(bookings);

        assertTrue(sortedBookings.get(0).getDate().isBefore(sortedBookings.get(1).getDate()));
        assertTrue(sortedBookings.get(1).getDate().isBefore(sortedBookings.get(2).getDate()));
    }
}
