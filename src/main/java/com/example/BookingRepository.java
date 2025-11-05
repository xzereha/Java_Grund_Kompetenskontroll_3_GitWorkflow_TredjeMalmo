package com.example;

import com.example.Models.Booking;

import java.util.ArrayList;
import java.util.List;

public class BookingRepository {
    private final List<Booking> bookingList = new ArrayList<>();

    public boolean addBooking(Booking booking) {
        return bookingList.add(booking);
    }


    //TODO Does it have to take in a copy of the booking object, then find the original in the bookingList and remove it?
    public boolean removeBooking(Booking booking) {
        return bookingList.remove(booking);
    }


    //TODO make copy of list to return
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
