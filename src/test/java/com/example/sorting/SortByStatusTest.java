package com.example.sorting;

import com.example.Models.Booking;
import org.junit.jupiter.api.Test;

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

        SortByStatus sortByStatus = new SortByStatus(Booking.Status.PENDING);

        List<Booking> sortedBookings = sortByStatus.apply(bookings);


        assertEquals(Booking.Status.PENDING, sortedBookings.get(0).getStatus());
        assertEquals(Booking.Status.PENDING, sortedBookings.get(1).getStatus());
        assertEquals(Booking.Status.PENDING, sortedBookings.get(2).getStatus());
    }
    // TODO test for completed bookings


}
