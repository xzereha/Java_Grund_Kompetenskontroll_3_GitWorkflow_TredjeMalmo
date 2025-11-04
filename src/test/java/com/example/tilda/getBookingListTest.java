package com.example.tilda;

import com.example.BookingRepository;
import com.example.Models.Booking;
import com.example.Models.Email;
import com.example.Models.RegNr;
import com.example.Models.Vehicle;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class getBookingListTest {
    @Test
    void returns_valid_list() {
        Vehicle v1 = new Vehicle(new RegNr("FCK543"), "Volvo", 2036);

        LocalDate now = LocalDate.of(2025, 11, 04);
        List<Booking> mockBookings = List.of(new Booking.Inspection(v1, new Email("tilda.niva@gmail.com"), now, 34),
                new Booking.Repair(v1, new Email("hej123@gmail.com"), now, "Lampa"));

        BookingRepository bookingRepository = mock(BookingRepository.class);
        when(bookingRepository.getBookingList()).thenReturn(mockBookings);

        System.out.println(bookingRepository.getBookingList());
    }
}
