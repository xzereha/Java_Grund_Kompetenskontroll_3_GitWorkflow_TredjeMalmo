package com.example.sorting;

import com.example.Models.Booking;

import java.util.Comparator;
import java.util.List;

public class SortByDate implements Sort {
    private final boolean newestFirst;

    public SortByDate(boolean newestFirst) {
        this.newestFirst = newestFirst;
    }

    @Override
    public List<Booking> apply(List<Booking> bookings) {
        Comparator<Booking> comparator = Comparator.comparing(Booking :: getDate);
        if (newestFirst) {
            comparator = comparator.reversed();
        }
        return bookings.stream()
                .sorted(comparator)
                .toList();
    }
}
