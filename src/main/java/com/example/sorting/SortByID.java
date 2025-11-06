package com.example.sorting;

import com.example.Models.Booking;

import java.util.List;

public class SortByID implements Sort {

    @Override
    public List<Booking> apply(List<Booking> bookings) {
        return bookings.stream()
                .sorted((b1, b2) -> Long.compare(b1.getId(), b2.getId()))
                .toList();
    }
}
