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

public class SortByIdTest {
    private List<Booking> bookings;

    @BeforeEach
    void setUp() {
        bookings = new ArrayList<>();

        bookings.add(new Booking.Service
                (new Vehicle(new RegNr("ABC123"), "FIAT PANDA", 2022), new Email("email@address.se"),
                        LocalDate.of(2025,11,28),2334));

        bookings.add(new Booking.Inspection
                (new Vehicle(new RegNr("ABC123"), "MERCEDES SMART", 2000), new Email("email@address.se"),
                        LocalDate.of(2025,12,2),4567));

        bookings.add(new Booking.Repair
                (new Vehicle(new RegNr("ABC123"), "TESLA", 2022), new Email("email@address.se"),
                        LocalDate.of(2025,12,3),"Doorhandel"));
    }

    @Test
    void whenSortById_thenReturnSortedBookingsById() {
        SortByID sortByID = new SortByID(1);

        List<Booking> sortedBookings = sortByID.apply(bookings);

        // Hämta id:n i sorterad lista
        List<Long> actualOrder = sortedBookings.stream()
                .map(Booking::getId)
                .toList();

        assertEquals(0, actualOrder.get(0));
        assertEquals(1, actualOrder.get(1));
        assertEquals(2, actualOrder.get(2));
    }
}
