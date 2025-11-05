package com.example.sorting;

import org.junit.jupiter.api.Test;

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
    void whenSortByDate_thenReturnSortedBookingsByDate() {

        SortByDate sortByDate = new SortByDate(true);
        List<Booking> sortedBookings = sortByDate.apply(bookings);

        assertTrue(sorted.get(0).getDate().isBefore(sorted.get(1).getDate()));
        assertTrue(sorted.get(1).getDate().isBefore(sorted.get(2).getDate()));





    }

}
