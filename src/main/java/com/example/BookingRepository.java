package com.example;

import com.example.Models.Booking;

import java.util.ArrayList;
import java.util.List;

public class BookingRepository {
    private final List<Booking> bookingList = new ArrayList<>();

    public boolean addBooking(Booking booking) {
        return bookingList.add(booking);
    }

    public boolean removeBooking(Booking booking) {
        return bookingList.remove(booking);
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public int size() {
        return bookingList.size();
    }

    public boolean isEmpty() {
        return bookingList.isEmpty();
    }
}
