package com.example;

import com.example.Models.Booking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookingRepository {
    private final List<Booking> bookingList = new ArrayList<>();

    public boolean addBooking(Booking booking) {
        return bookingList.add(booking);
    }

    public boolean removeBooking(Booking booking) {
        return bookingList.remove(booking);
    }

    /**
     * .
     * Returns an unmodifiable view of the booking list.
     * Changes to the original booking list will be reflected in the returned list.
     * 
     * @return Unmodifiable list of bookings.
     */
    public List<Booking> getBookingList() {
        return Collections.unmodifiableList(bookingList);
    }

    public int size() {
        return bookingList.size();
    }

    public boolean isEmpty() {
        return bookingList.isEmpty();
    }
}
