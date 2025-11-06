package com.example.sorting;

import com.example.Models.Booking;

import java.util.Comparator;
import java.util.List;

public class SortByStatus implements Sort {

    @Override
    public List<Booking> apply(List<Booking> bookings) {
        return bookings.stream()
                .sorted(Comparator.comparing(Booking::getStatus))
                .toList();
    }
}
