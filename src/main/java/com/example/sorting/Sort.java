package com.example.sorting;

import com.example.Models.Booking;
import java.util.List;

public interface Sort {
    List<Booking> apply(List<Booking> bookings);
}
