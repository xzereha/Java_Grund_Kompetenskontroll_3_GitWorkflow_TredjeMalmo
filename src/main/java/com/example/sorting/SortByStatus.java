package com.example.sorting;

import com.example.Models.Booking;

import java.util.List;

public class SortByStatus implements Sort {
    private final Booking.Status status;

    public SortByStatus(Booking.Status status) {
        this.status = status;
    }

    @Override
    public List<Booking> apply(List<Booking> bookings) {
        return bookings.stream()
                .filter(b -> b.getStatus() == status)
                .toList();
    }
}
